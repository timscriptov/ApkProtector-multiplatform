package com.mcal.dexprotect.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;
import com.mcal.dexprotect.R;
import com.mcal.dexprotect.utils.file.ScopedStorage;
import com.mcal.dexprotect.utils.widget.ToastUtils;

import org.jetbrains.annotations.Contract;

import java.io.File;

import kellinwood.security.zipsigner.optional.CertCreator;
import kellinwood.security.zipsigner.optional.DistinguishedNameValues;

public class CreateSignDialog {
    private Context context;
    private TextInputEditText keystoreAlias;
    private TextInputEditText keystorePassword;
    private TextInputEditText keystoreCommonName;
    private TextInputEditText keystoreOrganization;
    private TextInputEditText keystoreOrganizationUnit;
    private TextInputEditText keystoreCountry;
    private TextInputEditText keystoreLocality;

    @Contract(pure = true)
    public CreateSignDialog(Context context) {
        this.context = context;
    }

    public void show() {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.create_sign_dialog, null);

        keystoreAlias = view.findViewById(R.id.alias);
        keystorePassword = view.findViewById(R.id.password);
        keystoreCommonName = view.findViewById(R.id.common_name);
        keystoreOrganization = view.findViewById(R.id.organization);
        keystoreOrganizationUnit = view.findViewById(R.id.organization_unit);
        keystoreCountry = view.findViewById(R.id.country);
        keystoreLocality = view.findViewById(R.id.locality);

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(R.string.create_keystore);
        dialog.setView(view);
        dialog.setPositiveButton(R.string.create, (dialog1, which) -> {
            if (keystoreAlias.getText().toString().isEmpty()) {
                ToastUtils.toast(context, context.getString(R.string.invalid_or_empty_info));
            } else if (keystorePassword.getText().toString().isEmpty()) {
                ToastUtils.toast(context, context.getString(R.string.invalid_or_empty_info));
            } else if (keystoreCommonName.getText().toString().isEmpty()) {
                ToastUtils.toast(context, context.getString(R.string.invalid_or_empty_info));
            } else if (keystoreOrganization.getText().toString().isEmpty()) {
                ToastUtils.toast(context, context.getString(R.string.invalid_or_empty_info));
            } else if (keystoreOrganizationUnit.getText().toString().isEmpty()) {
                ToastUtils.toast(context, context.getString(R.string.invalid_or_empty_info));
            } else if (keystoreCountry.getText().toString().isEmpty()) {
                ToastUtils.toast(context, context.getString(R.string.invalid_or_empty_info));
            } else if (keystoreLocality.getText().toString().isEmpty()) {
                ToastUtils.toast(context, context.getString(R.string.invalid_or_empty_info));
            } else {
                save(keystoreAlias.getText().toString(),
                        keystorePassword.getText().toString(),
                        keystoreCommonName.getText().toString(),
                        keystoreOrganization.getText().toString(),
                        keystoreOrganizationUnit.getText().toString(),
                        keystoreCountry.getText().toString(),
                        keystoreLocality.getText().toString());
                dialog1.dismiss();
            }
            dialog1.cancel();
        });
        dialog.setNegativeButton(android.R.string.cancel, (dialog1, which) -> {
            dialog1.cancel();
        });
        dialog.show();
    }

    private void save(String keyAlias, String keyPass, String commonName, String organization, String organizationUnit, String country, String locality) {
        File folder = new File(ScopedStorage.getStorageDirectory() + "/key");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File keystoreFile = new File(folder + "/" + commonName.replace(" ", "_") + ".jks");
        if (keystoreFile.exists()) {
            ToastUtils.toast(context, context.getString(R.string.keystore_already_exist));
        } else {
            DistinguishedNameValues cd = new DistinguishedNameValues();
            cd.setCommonName(commonName);
            cd.setOrganization(organization);
            cd.setOrganizationalUnit(organizationUnit);
            cd.setCountry(country);
            cd.setLocality(locality);
            CertCreator.createKeystoreAndKey(keystoreFile.getAbsolutePath(), keyPass.toCharArray(), "RSA", 2048, keyAlias, keyPass.toCharArray(), "SHA256withRSA", 100, cd);
            ToastUtils.toast(context, context.getString(R.string.creating_keystore_complete));
        }
    }
}