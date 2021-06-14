package com.mcal.dexprotect.fragment;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.developer.filepicker.model.DialogConfigs;
import com.developer.filepicker.model.DialogProperties;
import com.developer.filepicker.view.FilePickerDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.mcal.dexprotect.App;
import com.mcal.dexprotect.BuildConfig;
import com.mcal.dexprotect.R;
import com.mcal.dexprotect.activities.HomeActivity;
import com.mcal.dexprotect.async.ProtectAsyncListener;
import com.mcal.dexprotect.async.presentation.ProtectAsync;
import com.mcal.dexprotect.data.Preferences;
import com.mcal.dexprotect.module.Dialogs;
import com.mcal.dexprotect.utils.MyAppInfo;
import com.mcal.dexprotect.utils.Utils;
import com.mcal.dexprotect.utils.file.ScopedStorage;
import com.mcal.dexprotect.view.AppListDialog;
import com.mcal.dexprotect.view.CustomSignDialog;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import ru.svolf.melissa.sheet.SweetContentDialog;

public class HomeFragment extends Fragment {
    View.OnClickListener radioButtonClickListener = v -> {
        AppCompatRadioButton rb = (AppCompatRadioButton) v;
        switch (rb.getId()) {
            case R.id.dex_protect:
                Preferences.setDexProtectBoolean(true);
                Preferences.setEncryptResourcesBoolean(false);
                Preferences.setSignApkBoolean(false);
                break;
            case R.id.encrypt_resources:
                Preferences.setDexProtectBoolean(false);
                Preferences.setEncryptResourcesBoolean(true);
                Preferences.setSignApkBoolean(false);
                break;
            case R.id.sign_apk:
                Preferences.setDexProtectBoolean(false);
                Preferences.setEncryptResourcesBoolean(false);
                Preferences.setSignApkBoolean(true);
                break;

            default:
                break;
        }
    };
    private View mView;
    private AppCompatEditText apkPath;
    private AppCompatImageView apkIcon;
    private AppCompatTextView apkName;
    private AppCompatButton protect;
    private AppCompatTextView apkPack;
    private MaterialCardView dexProtectFeatures;
    private AppCompatRadioButton dexProtect;
    private AppCompatRadioButton shrinkResources;
    private AppCompatRadioButton signApk;
    private AppCompatCheckBox checkluckyPatcher;
    private AppCompatCheckBox checkSignature;
    private AppCompatCheckBox checkRoot;
    private AppCompatCheckBox checkMagisk;
    private AppCompatCheckBox checkXposed;
    private AppCompatCheckBox checkVending;
    private AppCompatCheckBox checkEmulator;
    private AppCompatCheckBox checkClone;
    private AppCompatCheckBox checkHook;
    private AppCompatCheckBox checkIllegalCode;
    private AppCompatCheckBox checkVpnProxy;
    private AppCompatCheckBox checkDebug;
    private ProtectAsyncListener listener = new ProtectAsyncListener() {

        @Override
        public void onProtected() {
            showDialogComplete(getContext().getString(R.string.apk_is_already_protected));
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
        }
    };

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_main, container, false);
        writeFolder();

        apkIcon = mView.findViewById(R.id.apkIcon);
        apkName = mView.findViewById(R.id.apkName);
        apkPack = mView.findViewById(R.id.apkPackage);
        apkPath = mView.findViewById(R.id.apkpath);

        dexProtectFeatures = mView.findViewById(R.id.dex_protect_features);
        dexProtectFeatures.setVisibility(!Preferences.getDexProtectBoolean() ? View.GONE : View.VISIBLE);

        AppCompatRadioButton redRadioButton = mView.findViewById(R.id.dex_protect);
        redRadioButton.setOnClickListener(radioButtonClickListener);

        AppCompatRadioButton greenRadioButton = mView.findViewById(R.id.encrypt_resources);
        greenRadioButton.setOnClickListener(radioButtonClickListener);

        AppCompatRadioButton blueRadioButton = mView.findViewById(R.id.sign_apk);
        blueRadioButton.setOnClickListener(radioButtonClickListener);

        apkPath.setText(Preferences.isApkPath());
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
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q && !Environment.isExternalStorageManager()) {
                showScopedStorageDialog();
            } else {
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
            }
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

        checkluckyPatcher = mView.findViewById(R.id.lucky_patcher_check);
        checkluckyPatcher.setChecked(Preferences.isLuckyPatcherCheckBoolean());
        checkluckyPatcher.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.isLuckyPatcherCheckBoolean(p2);
        });

        checkSignature = mView.findViewById(R.id.signature_check);
        checkSignature.setChecked(Preferences.isSignatureCheckBoolean());
        checkSignature.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.isSignatureCheckBoolean(p2);
        });

        checkRoot = mView.findViewById(R.id.root_check);
        checkRoot.setChecked(Preferences.isRootCheckBoolean());
        checkRoot.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.isRootCheckBoolean(p2);
        });

        checkMagisk = mView.findViewById(R.id.magisk_check);
        checkMagisk.setChecked(Preferences.isMagiskCheckBoolean());
        checkMagisk.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.isMagiskCheckBoolean(p2);
        });

        checkXposed = mView.findViewById(R.id.xposedCheck);
        checkXposed.setChecked(Preferences.isXposedCheckBoolean());
        checkXposed.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.isXposedCheckBoolean(p2);
        });

        checkVending = mView.findViewById(R.id.playstore_check);
        checkVending.setChecked(Preferences.isPlaystoreCheckBoolean());
        checkVending.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.isPlaystoreCheckBoolean(p2);
        });

        checkEmulator = mView.findViewById(R.id.emulator_check);
        checkEmulator.setChecked(Preferences.isEmulatorCheckBoolean());
        checkEmulator.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.isEmulatorCheckBoolean(p2);
        });

        checkClone = mView.findViewById(R.id.clone_check);
        checkClone.setChecked(Preferences.isCloneCheckBoolean());
        checkClone.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.isCloneCheckBoolean(p2);
        });

        checkHook = mView.findViewById(R.id.hook_check);
        checkHook.setChecked(Preferences.isHookCheckBoolean());
        checkHook.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.isHookCheckBoolean(p2);
            if (checkHook.isChecked()) {
                checkHook();
            }
        });

        checkIllegalCode = mView.findViewById(R.id.illegal_code_check);
        checkIllegalCode.setChecked(Preferences.isIllegalCodeCheckBoolean());
        checkIllegalCode.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.isIllegalCodeCheckBoolean(p2);
            if (checkIllegalCode.isChecked()) {
                checkIllegalCode();
            }
        });

        checkVpnProxy = mView.findViewById(R.id.check_vpn);
        checkVpnProxy.setChecked(Preferences.isCheckVPNBoolean());
        checkVpnProxy.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.isCheckVPNBoolean(p2);
        });

        checkDebug = mView.findViewById(R.id.debug_check);
        checkDebug.setChecked(Preferences.isDebugCheckBoolean());
        checkDebug.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.isDebugCheckBoolean(p2);
        });

        dexProtect = mView.findViewById(R.id.dex_protect);
        dexProtect.setChecked(Preferences.getDexProtectBoolean());
        dexProtect.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.setDexProtectBoolean(p2);
            updateView();
        });

        shrinkResources = mView.findViewById(R.id.encrypt_resources);
        shrinkResources.setChecked(Preferences.getEncryptResourcesBoolean());
        shrinkResources.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.setEncryptResourcesBoolean(p2);
            updateView();
        });

        signApk = mView.findViewById(R.id.sign_apk);
        signApk.setChecked(Preferences.getSignApkBoolean());
        signApk.setOnCheckedChangeListener((p1, p2) -> {
            Preferences.setSignApkBoolean(p2);
            updateView();
        });

        return mView;
    }

    private void checkIllegalCode() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(40, 0, 40, 0);
        ll.setLayoutParams(layoutParams);
        final AppCompatEditText enterClassname = new AppCompatEditText(getActivity());
        enterClassname.setHint("Enter class name");
        enterClassname.setText(Preferences.isIllegalCodeCheckString());
        ll.addView(enterClassname);
        new AlertDialog.Builder(getActivity())
                .setTitle("Add class name")
                .setView(ll)
                .setPositiveButton(R.string.save, (p1, p2) -> {
                    Preferences.isIllegalCodeCheckString(enterClassname.getText().toString());
                })
                .setNegativeButton(R.string.cancel, (p1, p2) -> {
                    Preferences.isIllegalCodeCheckBoolean(false);
                    checkIllegalCode.setChecked(false);
                })
                .create().show();
    }

    private void checkHook() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(40, 0, 40, 0);
        ll.setLayoutParams(layoutParams);
        final AppCompatEditText enterPackageName = new AppCompatEditText(getActivity());
        enterPackageName.setHint("Enter Package Name");
        enterPackageName.setText(Preferences.isHookCheckString());
        ll.addView(enterPackageName);
        new AlertDialog.Builder(getActivity())
                .setTitle("Hook")
                .setView(ll)
                .setPositiveButton(R.string.save, (p1, p2) -> {
                    Preferences.isHookCheckString(enterPackageName.getText().toString());
                })
                .setNegativeButton(R.string.cancel, (p1, p2) -> {
                    Preferences.isHookCheckBoolean(false);
                    checkHook.setChecked(false);
                })
                .create().show();
    }

    private void runProcess(final File apk) {
        if (Preferences.isCustomSignature()) {
            new CustomSignDialog((p1, p2) -> start(apk)).show(getContext());
        } else {
            start(apk);
        }
    }

    private void start(File apk) {
        final File sourceDir = new File(ScopedStorage.getStorageDirectory() + "/ApkProtect/output/" + MyAppInfo.getPackage() + "");
        if (sourceDir.exists()) {
            showAlreadyExistsDialog(apk.getAbsolutePath(), sourceDir);
        } else {
            ProtectAsync async = new ProtectAsync(listener, getActivity());
            async.execute(apk.getAbsolutePath());
        }
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

    public void updateView() {
        if (!Preferences.getDexProtectBoolean()) {
            LayoutTransition lt = (dexProtectFeatures).getLayoutTransition();
            lt.disableTransitionType(LayoutTransition.DISAPPEARING);
            dexProtectFeatures.setVisibility(View.GONE);
            lt.enableTransitionType(LayoutTransition.DISAPPEARING);
        }

        if (Preferences.getDexProtectBoolean()) {
            LayoutTransition lt = (dexProtectFeatures).getLayoutTransition();
            lt.disableTransitionType(LayoutTransition.APPEARING);
            dexProtectFeatures.setVisibility(View.VISIBLE);
            lt.enableTransitionType(LayoutTransition.APPEARING);
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