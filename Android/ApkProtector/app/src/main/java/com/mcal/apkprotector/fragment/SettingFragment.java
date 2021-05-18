package com.mcal.apkprotector.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.mcal.apkprotector.R;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.SecurePreferences;
import com.mcal.apkprotector.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class SettingFragment extends PreferenceFragmentCompat implements SecurePreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences sp;
    private EditTextPreference protectKeyString;
    private SwitchPreference optimizeDex;
    private EditTextPreference ignoredClass;

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        /*switch (key) {
            case "optimize_dex":
                /*if (optimize_dex.isChecked()) {
                    ignored_class.setEnabled(false);
                } else {
                    ignored_class.setEnabled(true);
                }
                break;
        }*/
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
        sp = Preferences.getDefSharedPreferences();

        protectKeyString = findPreference("protectKeyString");
        protectKeyString.setText(Preferences.isProtectKeyString(Utils.sealing(Utils.buildID())));

        optimizeDex = findPreference("optimizeDexBoolean");
        ignoredClass = findPreference("userIgnoredClasses");
        ignoredClass.setText(Preferences.isUserIgnoredClasses(ignoredLibs()));
    }

    @Override
    public void onPause() {
        super.onPause();
        sp.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sp.registerOnSharedPreferenceChangeListener(this);
    }

    @Nullable
    private String ignoredLibs() {
        try {
            InputStream open = getResources().openRawResource(R.raw.ignored_class);
            ByteArrayOutputStream xf = new ByteArrayOutputStream();
            byte[] bArr = new byte[5242880];
            while (true) {
                int read = open.read(bArr);
                if (read == -1) {
                    break;
                }
                xf.write(bArr, 0, read);
            }
            return new String(xf.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }
}