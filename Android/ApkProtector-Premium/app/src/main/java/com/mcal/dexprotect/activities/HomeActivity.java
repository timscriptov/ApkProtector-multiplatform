package com.mcal.dexprotect.activities;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mcal.dexprotect.R;
import com.mcal.dexprotect.async.presentation.HistoryPresenter;
import com.mcal.dexprotect.data.dto.home.HomeListAdapter;
import com.mcal.dexprotect.utils.ExceptionHandler;
import com.mcal.dexprotect.utils.SourceInfo;
import com.mcal.dexprotect.view.CenteredToolBar;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ListView listView;
    private LinearLayout welcomeLayout;
    private File apk;
    private SwipeRefreshLayout swipeRefresh;
    private String xpath;
    private DataSetObserver setObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        xpath = Environment.getExternalStorageDirectory() + "/ApkProtect";

        setContentView(R.layout.activity_landing);
        setupToolbar(getString(R.string.protected_apps));

        attachObserver();

        swipeRefresh = findViewById(R.id.swipeRefresh);
        listView = findViewById(R.id.history_list);
        welcomeLayout = findViewById(R.id.welcome_layout);
    }

    private void attachObserver() {
        setObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                rerunHistoryLoader(xpath);
            }
        };
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    InstallProvider.install(this, apk);
                    //Log.d("TAG", "onActivityResult: user accepted the (un)install");
                } else if (resultCode == RESULT_CANCELED) {
                    //Log.d("TAG", "onActivityResult: user canceled the (un)install");
                } else if (resultCode == RESULT_FIRST_USER) {
                    //Log.d("TAG", "onActivityResult: failed to (un)install");
                }
                break;
            case 2:
                recreate();
                break;
        }
    }*/

    @SuppressWarnings("ConstantConditions")
    private void setupToolbar(String title) {
        CenteredToolBar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (title != null) {
            getSupportActionBar().setTitle(title);
        } else {
            ActivityInfo activityInfo;
            try {
                activityInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
                String currentTitle = activityInfo.loadLabel(getPackageManager()).toString();
                getSupportActionBar().setTitle(currentTitle);

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void setupList(@NotNull List<SourceInfo> allPackages) {
        if (allPackages.size() < 1) {
            swipeRefresh.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
            welcomeLayout.setVisibility(View.VISIBLE);
        } else {
            welcomeLayout.setVisibility(View.INVISIBLE);

            setupAdapter(allPackages);

            swipeRefresh.setOnRefreshListener(() -> {
                rerunHistoryLoader(xpath);
                swipeRefresh.setRefreshing(false);
            });
            swipeRefresh.setVisibility(View.VISIBLE);

            listView.setVisibility(View.VISIBLE);
        }
    }

    public void rerunHistoryLoader(String xpath) {
//        HistoryLoader historyLoaderTwo = new HistoryLoader(this);
//        historyLoaderTwo.execute(xpath);
        HistoryPresenter historyPresenter = new HistoryPresenter();
        historyPresenter.attach(this);
        historyPresenter.execute(xpath);
    }

    private void setupAdapter(List<SourceInfo> data) {
        HomeListAdapter adapter = new HomeListAdapter(apk, HomeActivity.this, R.layout.history_list_item, data);
        adapter.registerDataSetObserver(setObserver);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        rerunHistoryLoader(xpath);
    }

    @Override
    protected void onDestroy() {
        if (listView.getAdapter() != null) {
            listView.getAdapter().unregisterDataSetObserver(setObserver);
        }
        super.onDestroy();
    }
}