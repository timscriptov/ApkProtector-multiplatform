package com.mcal.apkprotector.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.mcal.apkprotector.BuildConfig;
import com.mcal.apkprotector.R;
import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.data.dto.main.ViewPagerAdapter;
import com.mcal.apkprotector.fragment.HomeFragment;
import com.mcal.apkprotector.module.Dialogs;
import com.mcal.apkprotector.utils.AdmobHelper;
import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.ExceptionHandler;
import com.mcal.apkprotector.utils.SecurityUtils;
import com.mcal.apkprotector.utils.SignatureCheck;
import com.mcal.apkprotector.utils.Utils;
import com.mcal.apkprotector.view.CenteredToolBar;
import com.mcal.apkprotector.view.CreateSignDialog;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class MainActivity extends AppCompatActivity {
    //private static final String QQ = "http://qm.qq.com/cgi-bin/qm/qr?k=EFPj3_Anijd8CSPQmJD3LvE8JyNXam1t&authKey=0hwm0svzbbowsbNExQsbT%2F%2BundQZ3BW%2F7rQP58Ztq9ukjWBSqnKrlQ%3D%3D&group_code=770267696";//mqqwpa://im/chat?chat_type=wpa&uin=2723299592";//770267696
    private CenteredToolBar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private AdmobHelper admobHelper;
    private ProgressDialog admobProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.main);
        admobProgress = new ProgressDialog(this);
        admobProgress.setMessage("Preparing video ads...");
        admobHelper = new AdmobHelper(this);
        admobHelper.setMobileAdsId(Constants.mobileAdsId);
        admobHelper.setRewardedId(Constants.rewardedId);
        admobHelper.buildAdsRequest();

        // Проверка хеша сертификата
        SignatureCheck.start(this);
        // Проверка встроенного кода
        try {
            if (SecurityUtils.illegalCodeCheck() || BuildConfig.DEBUG) {
                Utils.showDialogWarn(this, "ApkProtector Security", getString(R.string.vending_message));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        setupToolbar(getString(R.string.app_name));
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        navigationView = findViewById(R.id.shitstuff);
        View header = navigationView.getHeaderView(0);
        ((TextView) header.findViewById(R.id.version)).setText(CommonUtils.versionName(this));

        try {
            if (SecurityUtils.armCheck(this) || BuildConfig.DEBUG) {
                Utils.showDialogWarn(this, "ApkProtector Security", getString(R.string.vending_message));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            drawerLayout.closeDrawers();
            switch (menuItem.getItemId()) {
                case R.id.item:
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    return true;
                case R.id.keystore:
                    new CreateSignDialog(MainActivity.this).show();
                    return true;
                case R.id.setting:
                    startActivityForResult(new Intent(this, SettingActivity.class), 0);
                    return true;
                case R.id.about:
                    Dialogs.about(this);
                    return true;
                case R.id.community:
                    Dialogs.showCommunitySheet(this);
                    return true;
                default:
                    return true;
            }
        });
        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        actionBarDrawerToggle.syncState();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Settings.ACTION_MANAGE_OVERLAY_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Settings.ACTION_MANAGE_OVERLAY_PERMISSION}, 1);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            if (Preferences.isNightModeEnabled()) {
                Preferences.setNightModeEnabled(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                getDelegate().applyDayNight();
            } else {
                Preferences.setNightModeEnabled(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                getDelegate().applyDayNight();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("ConstantConditions")
    private void setupToolbar(String title) {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupViewPager(@NotNull ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "MAIN");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            exit();
        }
    }

    @SuppressLint("WrongConstant")
    public void exit() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle(R.string.exit);
        dialog.setMessage(R.string.do_you_want_to_minimize_or_exit_message);
        dialog.setPositiveButton(R.string.exit, (p1, p2) -> {
            new Semaphore(0, true).release();
            android.os.Process.killProcess(android.os.Process.myPid());
            CommonUtils.exit();
        });
        dialog.setNegativeButton(R.string.minimize, (p1, p2) -> {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        dialog.setNeutralButton(R.string.cancel, null);
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
