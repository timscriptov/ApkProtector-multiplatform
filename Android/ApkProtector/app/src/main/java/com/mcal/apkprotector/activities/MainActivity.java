package com.mcal.apkprotector.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
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
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.data.dto.main.ViewPagerAdapter;
import com.mcal.apkprotector.fragment.HomeFragment;
import com.mcal.apkprotector.utils.CommonUtils;
import com.mcal.apkprotector.utils.Dialogs;
import com.mcal.apkprotector.utils.ExceptionHandler;
import com.mcal.apkprotector.utils.Utils;
import com.mcal.apkprotector.utils.security.SecurityUtils;
import com.mcal.apkprotector.utils.security.SignatureCheck;
import com.mcal.apkprotector.view.CenteredToolBar;
import com.mcal.apkprotector.view.CreateSignDialog;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class MainActivity extends AppCompatActivity {
    private CenteredToolBar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.main);
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
            String[] files = new String[]{"Hook_apk", "libjiagu.so", "libjiagu_a64.so", "libjiagu_x64.so",
                    "libjiagu_x86.so", "arm", "hook.apk", "Arm_Epic", "App_dex/classes.dex",
                    "App_dex/Modex.txt", "Hook_so/arm64-v8a/libIOHook.so",
                    "Hook_so/arm64-v8a/libmocls.so", "Hook_so/arm64-v8a/libsandhook.so",
                    "Hook_so/armeabi-v7a/libmocls.so", "Hook_so/armeabi-v7a/libsandhook.so",
                    "Hook_so/armeabi-v7a/libIOHook.so", "package$Info"};
            for (String s : files) {
                if (SecurityUtils.assetsCheck(this, s)/* || BuildConfig.DEBUG*/) {
                    Utils.showDialogWarn(this, "ApkProtector Security", "Detected Modex 3.0 or ARM or Kill++1");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File xpath = this.getDir("libs", Context.MODE_PRIVATE);

            String[] files = new String[]{xpath + "/App_dex/classes.dex",
                    xpath + "/App_dex/Modex.txt", xpath + "/arm64-v8a/libIOHook.so",
                    xpath + "/arm64-v8a/libmock.so", xpath + "/arm64-v8a/libsandhook.so",
                    xpath + "/armeabi-v7a/libIOHook.so", xpath + "/armeabi-v7a/libmock.so",
                    xpath + "/armeabi-v7a/libsandhook.so"};
            for (String s : files) {
                if (new File(s).exists()) {
                    Utils.showDialogWarn(this, "ApkProtector Security", "Detected Modex 3.0");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File xpath = new File(this.getApplicationInfo().nativeLibraryDir);

            String[] files = new String[]{xpath + "/libfuck.so", xpath + "/libarm.so",
                    xpath + "/libarm_signer.so", xpath + "/libsandhook.so",
                    xpath + "/libsandhook-native.so", xpath + "/libarm_classes.so",
                    xpath + "/libArmEpic.so", xpath + "/libEpic.so"};
            for (String s : files) {
                if (new File(s).exists()) {
                    Utils.showDialogWarn(this, "ApkProtector Security", "Detected kill++2 or ARM");
                }
            }
        } catch (Exception e) {
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
