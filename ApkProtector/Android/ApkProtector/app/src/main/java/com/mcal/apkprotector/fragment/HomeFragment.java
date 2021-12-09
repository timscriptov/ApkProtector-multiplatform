package com.mcal.apkprotector.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.developer.filepicker.model.DialogConfigs;
import com.developer.filepicker.model.DialogProperties;
import com.developer.filepicker.view.FilePickerDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.material.snackbar.Snackbar;
import com.mcal.apkprotector.App;
import com.mcal.apkprotector.BuildConfig;
import com.mcal.apkprotector.R;
import com.mcal.apkprotector.activities.HomeActivity;
import com.mcal.apkprotector.async.ProtectAsyncListener;
import com.mcal.apkprotector.async.presentation.ProtectAsync;
import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.AdmobHelper;
import com.mcal.apkprotector.utils.MyAppInfo;
import com.mcal.apkprotector.utils.Utils;
import com.mcal.apkprotector.utils.file.ScopedStorage;
import com.mcal.apkprotector.view.AppListDialog;
import com.mcal.apkprotector.view.CustomSignDialog;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import ru.svolf.melissa.sheet.SweetContentDialog;

public class HomeFragment extends Fragment implements RewardedVideoAdListener {
    private static final String TAG = "";
    private AppCompatEditText apkPath;
    private AppCompatImageView apkIcon;
    private AppCompatTextView apkName;
    private AppCompatButton protect;
    private AppCompatTextView apkPack;
    private AppCompatRadioButton dexProtect;
    private AppCompatRadioButton shrinkResources;
    private AppCompatRadioButton signApk;
    private AdmobHelper admobHelper;
    View.OnClickListener radioButtonClickListener = v -> {
        AppCompatRadioButton rb = (AppCompatRadioButton) v;
        switch (rb.getId()) {
            case R.id.dex_protect:
                Preferences.setDexProtectBoolean(true);
                Preferences.setEncryptResourcesBoolean(false);
                Preferences.setSignApkBoolean(false);
                admobHelper.showIntertitialAds();
                break;
            case R.id.encrypt_resources:
                Preferences.setDexProtectBoolean(false);
                Preferences.setEncryptResourcesBoolean(true);
                Preferences.setSignApkBoolean(false);
                admobHelper.showIntertitialAds();
                break;
            case R.id.sign_apk:
                Preferences.setDexProtectBoolean(false);
                Preferences.setEncryptResourcesBoolean(false);
                Preferences.setSignApkBoolean(true);
                admobHelper.showIntertitialAds();
                break;

            default:
                break;
        }
    };
    private RewardedVideoAd mAd;
    private final ProtectAsyncListener listener = new ProtectAsyncListener() {

        @Override
        public void onProtected() {
            showDialogComplete(getContext().getString(R.string.apk_is_already_protected));
            admobHelper.showIntertitialAds();
        }

        @Override
        public void onCompleted() {
            showDialogComplete(getContext().getString(R.string.obfuscate_complete));
        }

        @Override
        public void onFailed() {
            showDialogFailed(getContext().getString(R.string.obfuscate_fail));
        }

        @Override
        public void onProcess() {
            if (mAd.isLoaded()) {
                mAd.show();
            }
        }
    };

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.activity_main, container, false);
        writeFolder();

        apkIcon = mView.findViewById(R.id.apkIcon);
        apkName = mView.findViewById(R.id.apkName);
        apkPack = mView.findViewById(R.id.apkPackage);
        apkPath = mView.findViewById(R.id.apkpath);

        admobHelper = new AdmobHelper(getActivity());
        admobHelper.setMobileAdsId(Constants.mobileAdsId);
        admobHelper.setBannerView(mView.findViewById(R.id.ad_view));
        admobHelper.setBannerId(Constants.bannerId);
        admobHelper.setBannerSize(AdSize.SMART_BANNER);
        admobHelper.setIntertitialId(Constants.intertialId);
        admobHelper.buildAdsRequest();
        admobHelper.loadAdsRequest();

        AppCompatRadioButton redRadioButton = mView.findViewById(R.id.dex_protect);
        redRadioButton.setOnClickListener(radioButtonClickListener);

        AppCompatRadioButton greenRadioButton = mView.findViewById(R.id.encrypt_resources);
        greenRadioButton.setOnClickListener(radioButtonClickListener);

        AppCompatRadioButton blueRadioButton = mView.findViewById(R.id.sign_apk);
        blueRadioButton.setOnClickListener(radioButtonClickListener);

        apkPath.setText(Preferences.getApkPath());
        apkPath.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4) {
            }

            @Override
            public void onTextChanged(CharSequence p1, int p2, int p3, int p4) {
            }

            @Override
            public void afterTextChanged(Editable p1) {
                if (!p1.toString().isEmpty()) {
                    File apk = new File(p1.toString());
                    if (apk.exists()) {
                        apkIcon.setImageDrawable(new MyAppInfo(getContext(), apk.getAbsolutePath()).getIcon());
                        apkName.setText(MyAppInfo.getAppName());
                        apkPack.setText(MyAppInfo.getPackage());
                    } else {
                        apkIcon.setImageResource(R.drawable.ic_launcher);
                        apkName.setText(R.string.select_apk);
                        apkPack.setText("none");
                    }
                }
            }
        });

        (mView.findViewById(R.id.browseapk)).setOnClickListener(p1 -> {
            // Since android R, we need to ask the user to grant special scoped-storage permission first,
            // instead of showing files fragment directly
            /*if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q && !Environment.isExternalStorageManager()) {
                showScopedStorageDialog();
            } else {*/
            AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
            adb.setTitle(R.string.choose_method_title);
            adb.setItems(new String[]{getString(R.string.pick_from_sdcard), getString(R.string.pick_from_installed)}, (p112, p2) -> {
                switch (p2) {
                    case 0:
                        selectApkFromSdcard();
                        break;
                    case 1:
                        new AppListDialog(getActivity(), apkPath);
                        break;
                }
            });
            adb.create().show();
            //}
        });

        protect = mView.findViewById(R.id.protect);
        protect.setOnClickListener(p1 -> {
            final File apk = new File(apkPath.getText().toString());
            if (apk.exists() && apk.isFile()) {
                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.read_carefully_title)
                        .setMessage(R.string.warning_message)
                        .setPositiveButton("Protect", (p11, p2) -> {
                            runProcess(apk);
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .create().show();
            } else {
                Snackbar.make(protect, R.string.invalid_apk_file, Snackbar.LENGTH_SHORT).show();
            }
        });

        dexProtect = mView.findViewById(R.id.dex_protect);
        dexProtect.setChecked(Preferences.isDexProtectBoolean());
        dexProtect.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.setDexProtectBoolean(p2);
        });

        shrinkResources = mView.findViewById(R.id.encrypt_resources);
        shrinkResources.setChecked(Preferences.isEncryptResourcesBoolean());
        shrinkResources.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.setEncryptResourcesBoolean(p2);
            if (shrinkResources.isChecked()) {
                resGuardView();
            }
        });

        signApk = mView.findViewById(R.id.sign_apk);
        signApk.setChecked(Preferences.isSignApkBoolean());
        signApk.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.setSignApkBoolean(p2);
        });

        initVideoAds();
        return mView;
    }

    private void runProcess(final File apk) {
        if (Preferences.isCustomSignature()) {
            new CustomSignDialog((p1, p2) -> {
                start(apk);
            }).show(getContext());
        } else {
            start(apk);
        }
    }

    private void resGuardView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.dialog_resguard, null);
        AppCompatEditText resNameArsc = view.findViewById(R.id.fixedResName);
        resNameArsc.setText(Preferences.getResNameArscString());
        //AppCompatCheckBox rootDir = view.findViewById(R.id.rootDir);
        //rootDir.setChecked(Preferences.getResNameArscBoolean());
        new AlertDialog.Builder(getActivity())
                .setTitle("ResGuard")
                .setView(view)
                .setPositiveButton(R.string.save, (p1, p2) -> {
                    //Preferences.setResNameArscBoolean(rootDir.isChecked());
                    Preferences.setResNameArscString(resNameArsc.getText().toString());
                })
                .create().show();
    }

    @Nullable
    private Function0<Unit> start(File apk) {
        final File sourceDir = new File(ScopedStorage.getStorageDirectory() + "/ApkProtect/output/" + MyAppInfo.getPackage() + "");
        if (sourceDir.exists()) {
            showAlreadyExistsDialog(apk.getAbsolutePath(), sourceDir);
        } else {
            ProtectAsync async = new ProtectAsync(listener, getActivity());
            async.execute(apk.getAbsolutePath());
        }
        return null;
    }

    private void selectApkFromSdcard() {
        DialogProperties properties = new DialogProperties();
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File(ScopedStorage.getRootDirectory().getAbsolutePath());
        properties.extensions = new String[]{".apk", ".APK"};
        //Instantiate FilePickerDialog with Context and DialogProperties.
        FilePickerDialog dialog = new FilePickerDialog(getActivity(), properties, R.style.AlertDialogTheme);
        dialog.setTitle(R.string.select_apk);
        dialog.setPositiveBtnName(getString(R.string.select));
        dialog.setNegativeBtnName(getString(R.string.cancel));
        dialog.setDialogSelectionListener(files -> {
            for (String path : files) {
                File file = new File(path);
                if (file.getName().endsWith(".apk") || file.getName().endsWith(".APK")) {
                    apkPath.setText(file.getAbsolutePath());
                } else {
                    Snackbar.make(protect, R.string.invalid_file, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    public void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if (request == 1) {
            String uri = data.getStringExtra("apkPath");
            apkPath.setText(uri);
        }
    }

    private void writeFolder() {
        String fold = "ApkProtect/";
        String[] folder = {fold + "key", fold + "output"};
        for (String s : folder) {
            File f = new File(ScopedStorage.getStorageDirectory() + "/" + s);
            if (!f.exists()) {
                f.mkdirs();
            }
        }
    }

    private void showAlreadyExistsDialog(final String apkx, final File source) {
        final File apk = new File(apkx);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.this_package_has_already_been_obfuscated_title);
        dialog.setMessage(R.string.this_application_has_already_been_obfuscated_message);
        dialog.setPositiveButton(R.string.show, (dialog1, which) -> {
            getContext().startActivity(new Intent(App.getContext(), HomeActivity.class));
        });
        dialog.setNegativeButton("Protect", (dialog12, which) -> {
            try {
                System.gc();
                Utils.deleteFolder(source);
            } catch (Exception e) {
                e.printStackTrace();
            }
            PackageManager pm = getContext().getPackageManager();
            ProtectAsync async = new ProtectAsync(listener, getActivity());
            async.execute(apk.getAbsolutePath());
        });

        dialog.setNeutralButton(R.string.cancel, null);
        dialog.show();
    }

    private void showDialogFailed(String str) {
        SweetContentDialog failedDialog = new SweetContentDialog(getContext());
        failedDialog.setTitle(str);
        //failedDialog.setMessage();
        failedDialog.setCancelable(false);
        failedDialog.setPositive(R.drawable.ic_close, getContext().getString(android.R.string.ok), view1 -> {
            failedDialog.cancel();
        });
        failedDialog.show();
    }

    private void showDialogComplete(String str) {
        SweetContentDialog completeDialog = new SweetContentDialog(getContext());
        completeDialog.setTitle(str);
        //completeDialog.setMessage();
        //completeDialog.setCancelable(false);
        completeDialog.setPositive(R.drawable.ic_complete, getContext().getString(R.string.show), view1 -> {
            getActivity().startActivity(new Intent(App.getContext(), HomeActivity.class));
        });
        completeDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void showScopedStorageDialog() {
        SweetContentDialog permissionDialog = new SweetContentDialog(getContext());
        permissionDialog.setTitle(getString(R.string.scoped_storage_title));
        permissionDialog.setMessage(getString(R.string.scoped_storage_msg));
        permissionDialog.setPositive(R.drawable.ic_settings, getString(R.string.settings), view1 -> {
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
            startActivity(intent);
            permissionDialog.dismiss();
        });
        permissionDialog.show();
    }

    private void initVideoAds() {
        mAd = MobileAds.getRewardedVideoAdInstance(getContext());
        mAd.setRewardedVideoAdListener(this);
        loadAd();
    }

    private void loadAd() {
        if (!mAd.isLoaded()) {
            mAd.loadAd(Constants.rewardedId, new AdRequest.Builder().build());
        }
    }

    @Override
    public void onRewarded(@NotNull RewardItem reward) {
        Log.e(TAG, "onRewarded");
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.e(TAG, "onRewardedVideoAdLeftApplication");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.e(TAG, "onRewardedVideoAdClosed");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Log.e(TAG, "onRewardedVideoAdFailedToLoad");
    }

    @Override
    public void onRewardedVideoCompleted() {
        Log.e(TAG, "onRewardedVideoCompleted");
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.e(TAG, "onRewardedVideoAdLoaded");
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.e(TAG, "onRewardedVideoAdOpened");
    }

    @Override
    public void onRewardedVideoStarted() {
        Log.e(TAG, "onRewardedVideoStarted");
    }

    @Override
    public void onResume() {
        super.onResume();
        admobHelper.reloadAds();
    }

    @Override
    public void onPause() {
        super.onPause();
        mAd.resume(getContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAd.resume(getContext());
    }
}