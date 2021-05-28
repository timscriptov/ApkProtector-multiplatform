package com.mcal.dexprotect.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SecurePreferences implements SharedPreferences {
    //private static HashMap<OnSharedPreferenceChangeListener, OnSharedPreferenceChangeListener> OnSharedPreferenceChangeListeners;
    private static boolean sLoggingEnabled = false;
    public SharedPreferences sharedPreferences;

    public SecurePreferences(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public SecurePreferences(@NotNull Context context, String fileN) {
        sharedPreferences = context.getSharedPreferences(fileN, Context.MODE_PRIVATE);
    }

    @Contract(pure = true)
    public static boolean isLoggingEnabled() {
        return sLoggingEnabled;
    }

    public static void setLoggingEnabled(boolean loggingEnabled) {
        sLoggingEnabled = loggingEnabled;
    }

    @Nullable
    private static String encrypt(String str) {
        try {
            return new String(XxTea.encryptToBase64String(str, "UTF-16LE"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    private static String decrypt(String str) {
        try {
            return new String(XxTea.decryptBase64StringToString(str, "UTF-16LE"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return map of with decrypted values (excluding the key if present)
     */
    @Override
    public Map<String, String> getAll() {
        //wont be null as per http://androidxref.com/5.1.0_r1/xref/frameworks/base/core/java/android/app/SharedPreferencesImpl.java
        final Map<String, ?> encryptedMap = sharedPreferences.getAll();
        final Map<String, String> decryptedMap = new HashMap<String, String>(
                encryptedMap.size());
        for (Entry<String, ?> entry : encryptedMap.entrySet()) {
            try {
                Object cipherText = entry.getValue();
                //don't include the key
                //if (cipherText != null && !cipherText.equals(keys.toString())) {
                //the prefs should all be strings
                decryptedMap.put(entry.getKey(),
                        decrypt(cipherText.toString()));
                //  }
            } catch (Exception e) {
                /*if (sLoggingEnabled) {
				 Log.w(TAG, "error during getAll", e);
				 }*/
                // Ignore issues that unencrypted values and use instead raw cipher text string
                decryptedMap.put(entry.getKey(),
                        entry.getValue().toString());
            }
        }
        return decryptedMap;
    }

    @Override
    public String getString(String key, String defaultValue) {
        final String encryptedValue = sharedPreferences.getString(
                encrypt(key), null);

        String decryptedValue = decrypt(encryptedValue);
        if (encryptedValue != null && decryptedValue != null) {
            return decryptedValue;
        } else {
            return defaultValue;
        }
    }

    /**
     * Added to get a values as as it can be useful to store values that are
     * already encrypted and encoded
     *
     * @param key          pref key
     * @param defaultValue
     * @return Encrypted value of the key or the defaultValue if no value exists
     */
    public String getEncryptedString(String key, String defaultValue) {
        final String encryptedValue = sharedPreferences.getString(
                encrypt(key), null);
        return (encryptedValue != null) ? encryptedValue : defaultValue;
    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key, Set<String> defaultValues) {
        final Set<String> encryptedSet = sharedPreferences.getStringSet(
                encrypt(key), null);
        if (encryptedSet == null) {
            return defaultValues;
        }
        final Set<String> decryptedSet = new HashSet<String>(
                encryptedSet.size());
        for (String encryptedValue : encryptedSet) {
            decryptedSet.add(decrypt(encryptedValue));
        }
        return decryptedSet;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        final String encryptedValue = sharedPreferences.getString(
                encrypt(key), null);
        if (encryptedValue == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(decrypt(encryptedValue));
        } catch (NumberFormatException e) {
            throw new ClassCastException(e.getMessage());
        }
    }

    @Override
    public long getLong(String key, long defaultValue) {
        final String encryptedValue = sharedPreferences.getString(
                encrypt(key), null);
        if (encryptedValue == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(decrypt(encryptedValue));
        } catch (NumberFormatException e) {
            throw new ClassCastException(e.getMessage());
        }
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        final String encryptedValue = sharedPreferences.getString(
                encrypt(key), null);
        if (encryptedValue == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(decrypt(encryptedValue));
        } catch (NumberFormatException e) {
            throw new ClassCastException(e.getMessage());
        }
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        final String encryptedValue = sharedPreferences.getString(
                encrypt(key), null);
        if (encryptedValue == null) {
            return defaultValue;
        }
        try {
            return Boolean.parseBoolean(decrypt(encryptedValue));
        } catch (NumberFormatException e) {
            throw new ClassCastException(e.getMessage());
        }
    }

    @Override
    public boolean contains(String key) {
        return sharedPreferences.contains(encrypt(key));
    }

    @Override
    public SharedPreferences.Editor edit() {
        return new Editor();
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        sharedPreferences
                .registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * @param listener    OnSharedPreferenceChangeListener
     * @param decryptKeys Callbacks receive the "key" parameter decrypted
     */
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener, boolean decryptKeys) {

        if (!decryptKeys) {
            registerOnSharedPreferenceChangeListener(listener);
        }
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(
            OnSharedPreferenceChangeListener listener) {
        sharedPreferences
                .unregisterOnSharedPreferenceChangeListener(listener);
    }

    public final class Editor implements SharedPreferences.Editor {
        private SharedPreferences.Editor mEditor;

        /**
         * Constructor.
         */
        private Editor() {
            mEditor = sharedPreferences.edit();
        }

        @Contract("_, _ -> this")
        @Override
        public SharedPreferences.Editor putString(String key, String value) {
            mEditor.putString(encrypt(key),
                    encrypt(value));
            return this;
        }

        /**
         * This is useful for storing values that have be encrypted by something
         * else or for testing
         *
         * @param key   - encrypted as usual
         * @param value will not be encrypted
         * @return
         */
        @Contract("_, _ -> this")
        public SharedPreferences.Editor putUnencryptedString(String key,
                                                             String value) {
            mEditor.putString(encrypt(key), value);
            return this;
        }

        @Contract("_, _ -> this")
        @Override
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public SharedPreferences.Editor putStringSet(String key,
                                                     @NotNull Set<String> values) {
            final Set<String> encryptedValues = new HashSet<String>(
                    values.size());
            for (String value : values) {
                encryptedValues.add(encrypt(value));
            }
            mEditor.putStringSet(encrypt(key),
                    encryptedValues);
            return this;
        }

        @Contract("_, _ -> this")
        @Override
        public SharedPreferences.Editor putInt(String key, int value) {
            mEditor.putString(encrypt(key),
                    encrypt(Integer.toString(value)));
            return this;
        }

        @Contract("_, _ -> this")
        @Override
        public SharedPreferences.Editor putLong(String key, long value) {
            mEditor.putString(encrypt(key),
                    encrypt(Long.toString(value)));
            return this;
        }

        @Contract("_, _ -> this")
        @Override
        public SharedPreferences.Editor putFloat(String key, float value) {
            mEditor.putString(encrypt(key),
                    encrypt(Float.toString(value)));
            return this;
        }

        @Contract("_, _ -> this")
        @Override
        public SharedPreferences.Editor putBoolean(String key, boolean value) {
            mEditor.putString(encrypt(key),
                    encrypt(Boolean.toString(value)));
            return this;
        }

        @Contract("_ -> this")
        @Override
        public SharedPreferences.Editor remove(String key) {
            mEditor.remove(encrypt(key));
            return this;
        }

        @Contract(" -> this")
        @Override
        public SharedPreferences.Editor clear() {
            mEditor.clear();
            return this;
        }

        @Override
        public boolean commit() {
            return mEditor.commit();
        }

        @Override
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        public void apply() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                mEditor.apply();
            } else {
                commit();
            }
        }
    }
}