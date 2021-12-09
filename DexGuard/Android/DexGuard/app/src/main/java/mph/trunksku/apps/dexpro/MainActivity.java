package mph.trunksku.apps.dexpro;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.LinearLayout;
import com.mph.dexprotect.R;
import java.util.ArrayList;
import java.util.List;
import mph.trunksku.apps.dexpro.fragment.AppListFragment;
import mph.trunksku.apps.dexpro.fragment.DebugFragment;
import mph.trunksku.apps.dexpro.fragment.FilesFragment;
import mph.trunksku.apps.dexpro.fragment.HomeFragment;
import mph.trunksku.apps.dexpro.logger.LogFragment;
import mph.trunksku.apps.dexpro.utils.ExceptionHandler;
import mph.trunksku.apps.dexpro.utils.ThemeManager;
import mph.trunksku.apps.dexpro.view.CenteredToolBar;

public class MainActivity extends AppCompatActivity {


	private CenteredToolBar toolbar;

	
	//private AdmobHelper adhelper;

	private static SharedPreferences sp;


    
    private ProgressDialog adprogress;
    public static DrawerLayout drawerLayout;

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		new ThemeManager(this, MyApp.getDefSharedPreferences()).init();


		setContentView(R.layout.main);
		sp = MyApp.getSharedPreferences();

		setupToolbar(null);
        adprogress = new ProgressDialog(this);
        adprogress.setMessage("Preparing video ads...");


		//adhelper.initTimerAds(20000);

		//  MyAdapter adapter = new 
		//	MyAdapter(getSupportFragmentManager()); 
		// viewPager.setAdapter(adapter); 

		String android_id = Settings.Secure.getString(getContentResolver(),
													  Settings.Secure.ANDROID_ID);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.mainView, new HomeFragment());
        //transaction.addToBackStack(tag);
		transaction.commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
		
        
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

	}

	@Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder3 = new AlertDialog.Builder(MainActivity.this);
        builder3.setMessage("Do you want to exit current session?");
        builder3.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    // TODO: Implement this method
                    HomeFragment.apkpath = "bugs";
                    HomeFragment.item.clear();
                    finish();
                }
            });
        builder3.setNeutralButton("CANCEL", null);
        builder3.show();
    }

    public void activateLicense() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(1);
        ll.setPadding(40, 0, 40, 0);
        ll.setLayoutParams(layoutParams);
        final TextInputLayout til0 = new TextInputLayout(this);
        final AppCompatEditText acet0 = new AppCompatEditText(this);
        acet0.setHint("Activation code (Enter valid CODE)");
        //acet0.setText("");
        til0.addView(acet0);
        ll.addView(til0);
        new AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle("Activate License")
            .setView(ll)
            .setPositiveButton("ACTIVATE", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    // TODO: Implement this method
                    sp.edit().putString("DeviceID", acet0.getText().toString()).commit();
                    sp.edit().putBoolean("DeviceLock", true).commit();
                }
            })
            .setNeutralButton("REQUEST", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    // TODO: Implement this method

                }
            })
            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    // TODO: Implement this method

                }
            })
            .create().show();
	}

	/*public static void makePro() {
     nav_item3.setVisible(false);
     nav_item2.setEnabled(true);

     HomeFragment.signList.clear();
     HomeFragment.signList.add("DexProtect");
     HomeFragment.signList.add("Shared");
     HomeFragment.signList.add("Test");
     HomeFragment.signList.add("Custom");
     HomeFragment.signAdapt.notifyDataSetChanged();
     sp.edit().putInt("sign", 3).commit();
     HomeFragment.signature.setSelection(3);

     }

     public static void makeFree() {
     nav_item3.setVisible(true);
     nav_item2.setEnabled(false);

     HomeFragment.signList.clear();
     HomeFragment.signList.add("DexProtect");
     HomeFragment.signList.add("Shared");
     HomeFragment.signList.add("Test");
     HomeFragment.signAdapt.notifyDataSetChanged();
     sp.edit().putInt("sign", 0).commit();
     HomeFragment.signature.setSelection(0);
     }*/


	/*public void replaceFragmentWithAnimation(android.support.v4.app.Fragment fragment, String tag){
     FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
     //transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.enter_from_right, R.anim.exit_to_left);
     transaction.replace(R.id.fragment_container, fragment);
     transaction.addToBackStack(tag);
     transaction.commit();
     }*/

	public String vb() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 128);
            return "version " + packageInfo.versionName + " - " + packageInfo.versionCode;
        } catch (Exception e) {
            return "null";
        }
    }

	public void showSnack(String str) {
		Snackbar.make(toolbar, str, Snackbar.LENGTH_SHORT).show();
	}

	@SuppressWarnings("ConstantConditions")
    private void setupToolbar(String title) {
        toolbar = (CenteredToolBar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (title != null) {
            getSupportActionBar().setTitle(title);
        } else {
            ActivityInfo activityInfo;
            try {
                activityInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
                String currentTitle = activityInfo.loadLabel(getPackageManager()).toString();
                getSupportActionBar().setTitle(currentTitle);

            } catch (PackageManager.NameNotFoundException ignored) {

            }

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    /* private void setupToolbar(String title)
     {
     toolbar = (CenteredToolBar) findViewById(R.id.toolbar);
     TextView tv = (TextView) findViewById(R.id.xtitle);
     //tv.setText(title);
     setSupportActionBar(toolbar);
     getSupportActionBar().setTitle(title);
     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     getSupportActionBar().setDisplayShowHomeEnabled(true);
     }*/
    private void setupViewPager(ViewPager viewPager) {
        AppListFragment appList = new AppListFragment();
        FilesFragment filesList = new FilesFragment();
        LogFragment logList = new LogFragment();
		
        DebugFragment debugList = new DebugFragment();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(appList, "MAIN");
        adapter.addFragment(filesList, "MAIN");
        //adapter.addFragment(logList, "MAIN");
        //adapter.addFragment(new StatisticFragment(), "STATS");
        //adapter.addFragment(new ToolFragment(), "TOOL");
        adapter.addFragment(debugList, "DEBUGS");
        viewPager.setAdapter(adapter);
        //viewPager.set
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
