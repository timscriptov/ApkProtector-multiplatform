package com.mcal.apkprotector.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.mcal.apkprotector.R;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.SecurePreferences;
import com.mcal.apkprotector.utils.Utils;

public class SettingFragment extends PreferenceFragmentCompat implements SecurePreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences sp;

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
        sp = Preferences.getDefSharedPreferences();

        EditTextPreference protectKeyString = findPreference("protectKeyString");
        protectKeyString.setText(Preferences.isProtectKeyString(Utils.sealing(Utils.buildID())));
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
}