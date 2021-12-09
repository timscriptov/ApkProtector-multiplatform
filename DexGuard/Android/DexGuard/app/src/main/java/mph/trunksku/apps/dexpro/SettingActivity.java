package mph.trunksku.apps.dexpro;


import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.mph.dexprotect.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import mph.trunksku.apps.dexpro.utils.SecurePreferences;
import mph.trunksku.apps.dexpro.utils.ThemeManager;
import mph.trunksku.apps.dexpro.utils.Utils;
import mph.trunksku.apps.dexpro.view.CenteredToolBar;
import mph.trunksku.apps.dexpro.view.CreateSignDialog;
import mph.trunksku.apps.dexpro.admob.AppLovinHelper;

public class SettingActivity extends AppCompatActivity {

	private AppLovinHelper adhelp;

    public static class SettingFragment extends PreferenceFragment implements SecurePreferences.OnSharedPreferenceChangeListener {

		

        public static final String KEY_PREF_LANGUAGE = "pref_key_language";

        private SharedPreferences mSecurePrefs;

        private EditTextPreference sdk_repo;

        private Preference create_keystore;

        private SwitchPreference encrypt_all_dex;

       // private SwitchPreference optimize_dex;

       // private EditTextPreference ignored_class;

        private SharedPreferences mDefSharedPref;

        private SwitchPreference darkMod;

        private EditTextPreference dexclass;

        private SwitchPreference resources_shrink;

        private SwitchPreference user_keystore;
        
        
        
        public String ignored_libs()
        {
            try
            {
                
                InputStream open = getResources().openRawResource(R.raw.ignored_class);
                ByteArrayOutputStream xf = new ByteArrayOutputStream();
                byte[] bArr = new byte[5242880];
                while (true)
                {
                    int read = open.read(bArr);
                    if (read == -1)
                    {
                        break;
                    }
                    xf.write(bArr, 0, read);
                }
                return new String(xf.toByteArray()) ;
            }
            catch (Exception e)
            {
                return null;
            }
        }
        
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            mDefSharedPref = MyApp.getDefSharedPreferences();
            mSecurePrefs = MyApp.getSharedPreferences();
            darkMod = (SwitchPreference) findPreference("darkMode");
            sdk_repo = (EditTextPreference) findPreference("user_dexprotectjar");
            dexclass = (EditTextPreference) findPreference("user_dexclass");
            EditTextPreference dexkey = (EditTextPreference) findPreference("dexpro_key");
            dexkey.setText(mDefSharedPref.getString("dexpro_key", "dexprotect"));
            user_keystore = (SwitchPreference) findPreference("use_user_keystore");
            
            create_keystore = (Preference) findPreference("generate_keystore");
            create_keystore.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference p1)
                    {
                        new CreateSignDialog(getActivity()).show();
                        return true;
                    }
                });
            encrypt_all_dex = (SwitchPreference) findPreference("encrypt_all_dex");
            //optimize_dex = (SwitchPreference) findPreference("optimize_dex");
            //ignored_class = (EditTextPreference) findPreference("user_ignored_classes");
          //  ignored_class.setText(mDefSharedPref.getString("user_ignored_classes", ignored_libs()));
            resources_shrink = (SwitchPreference) findPreference("use_modaapt");
            
            ((Preference) findPreference("clearSourceHistory")).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference p1)
                    {
                        new AlertDialog.Builder(getActivity())
                        .setTitle(getString(R.string.deleteSourceHistory))
                            .setMessage(getString(R.string.deleteSourceHistoryConfirm))
                            //.setIcon(R.drawable.ic_error_outline_black)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface p1, int p2)
                                {
                                    new Thread() {
                                        public void run() {
                                            Utils.deleteFolder(new File(Environment.getExternalStorageDirectory() + "/DexProtect/output"));
                                        }
                                    }.start(); 
                                }
                            })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
                        return true;
                    }
                });
            ((Preference) findPreference("rate_us")).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference p1)
                    {
                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getActivity().getPackageName())));
                        return true;
                    }
                });
            ((Preference) findPreference("report_bug")).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference p1)
                    {
                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://m.facebook.com/dexprotector")));
                        return true;
                    }
                });
            /*if(MyApp.isProVersion()){
                //darkMod.setEnabled(true);
                sdk_repo.setEnabled(true);
                dexclass.setEnabled(true);
                //user_keystore.setEnabled(true);
                resources_shrink.setEnabled(true);
                if(encrypt_all_dex.isChecked()){
                    optimize_dex.setEnabled(false);
                    ignored_class.setEnabled(false);
                }else{
                    optimize_dex.setEnabled(true);
                    ignored_class.setEnabled(true);
                }
            }else{
                //darkMod.setEnabled(false);
                sdk_repo.setEnabled(false);
                dexclass.setEnabled(false);
                //user_keystore.setEnabled(false);
                resources_shrink.setEnabled(false);
                encrypt_all_dex.setEnabled(false);
                optimize_dex.setEnabled(false);
                ignored_class.setEnabled(false);
            }*/
        }

        
        
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            switch (key) {
                case "encrypt_all_dex":
                    if(encrypt_all_dex.isChecked()){
                       // optimize_dex.setEnabled(false);
                       // ignored_class.setEnabled(false);
                    }else{
                      //  optimize_dex.setEnabled(true);
                       // ignored_class.setEnabled(true);
                    }
                    break;
                case "darkMode":
                    //AppCompatDelegate.setDefaultNightMode(sharedPreferences.getBoolean(key, false) ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                    /*if (newValue as Boolean)
                    
                    )*/
                    Toast.makeText(getActivity(), getString(R.string.themeChangeCloseInfo), 1).show();
                    getActivity().recreate();
                    break;
                case "sdk_path":
                    mSecurePrefs.edit().putString("sdk_path", sdk_repo.getText().toString()).commit();
                    break;
                case "use_user_keystore":
                    /*if(mDnsForwardPref.isChecked()){
                     advanceCat.addPreference(mEnableDnsPref);
                     if(mEnableDnsPref.isChecked()){
                     advanceCat.addPreference(mPrimaryDnsPref);
                     advanceCat.addPreference(mSecondaryDnsPref);
                     }else{
                     advanceCat.removePreference(mPrimaryDnsPref);
                     advanceCat.removePreference(mSecondaryDnsPref);
                     }
                     }else{
                     advanceCat.removePreference(mEnableDnsPref);
                     advanceCat.removePreference(mPrimaryDnsPref);
                     advanceCat.removePreference(mSecondaryDnsPref);
                     }*/
                    break;
                case "enable_custom_dns_key":
                    /*if(mEnableDnsPref.isChecked()){
                     advanceCat.addPreference(mPrimaryDnsPref);
                     advanceCat.addPreference(mSecondaryDnsPref);
                     }else{
                     advanceCat.removePreference(mPrimaryDnsPref);
                     advanceCat.removePreference(mSecondaryDnsPref);
                     }*/
                    break;/*
                     case "show_notification":
                     if(mShowNotif.isChecked()){
                     systemCat.addPreference(mSoundNotif);
                     systemCat.addPreference(mVibrateNotif);
                     }else{
                     systemCat.removePreference(mSoundNotif);
                     systemCat.removePreference(mVibrateNotif);
                     }
                     break;
                     //case "use_dark_theme":
                     //getActivity().startActivity(new Intent(getActivity(), MainActivity.class))/*.putExtra(MainActivity.LAUNCH_FRAGMENT, MainActivity.FRAGMENT_SETTINGS).putExtra(MainActivity.LAUNCH_NEED_RECREATE, true))*/
                    //break;
            }
            //getActivity().recreate(); // necessary here because this Activity is currently running and thus a recreate() in onResume() would be too late
        }

        @Override
        public void onResume() {
            super.onResume();
            // documentation requires that a reference to the listener is kept as long as it may be called, which is the case as it can only be called from this Fragment
            //getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
            mSecurePrefs.registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            //getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            mSecurePrefs.unregisterOnSharedPreferenceChangeListener(this);
        }
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //setTheme(R.style.AppTheme_Dark);
        new ThemeManager(this, MyApp.getDefSharedPreferences()).init();
        
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(1);
        
        //ll.setPadding(40,0,40,0);
        ll.setLayoutParams(layoutParams);
        //AppBarLayout abl = new AppBarLayout(this);
        CenteredToolBar tb = new CenteredToolBar(this);
       // abl.addView(tb);
        ll.addView(tb);
        RelativeLayout rl = new RelativeLayout(this);
        ll.addView(rl);
        FrameLayout fl = new FrameLayout(this);
        fl.setId(1);
        rl.addView(fl);
        
        RelativeLayout adview = new RelativeLayout(this);
        
        rl.addView(adview);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -2);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        //params.addRule(RelativeLayout.LEFT_OF, R.id.id_to_be_left_of);

        adview.setLayoutParams(params); 
        setContentView(ll);
        
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(fl.getId(), new SettingFragment());
        beginTransaction.commit();
        adhelp = new AppLovinHelper(this);
		adhelp.setBannerId("52adea43e0c3fad2");
		adhelp.setBannerView(adview);
		
		adhelp.build();
        
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        //startActivity(new Intent(this, HomeActivity.class));
        //finish();
    }

    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}



