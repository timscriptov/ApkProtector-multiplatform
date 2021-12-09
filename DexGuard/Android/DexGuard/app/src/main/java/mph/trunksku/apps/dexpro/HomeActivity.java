package mph.trunksku.apps.dexpro;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mph.dexprotect.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Semaphore;
import mph.trunksku.apps.dexpro.HomeActivity;
import mph.trunksku.apps.dexpro.admob.AppLovinHelper;
import mph.trunksku.apps.dexpro.bottomsheet.DrawerBottomDialog;
import mph.trunksku.apps.dexpro.utils.MyAppInfo;
import mph.trunksku.apps.dexpro.utils.SignCheck;
import mph.trunksku.apps.dexpro.utils.SourceInfo;
import mph.trunksku.apps.dexpro.utils.StringUtils;
import mph.trunksku.apps.dexpro.utils.ThemeManager;
import mph.trunksku.apps.dexpro.utils.Utils;
import mph.trunksku.apps.dexpro.view.CenteredToolBar;
import mph.trunksku.apps.dexpro.view.DebugDialog;
import org.apache.commons.io.FileUtils;

public class HomeActivity extends AppCompatActivity {

	private ListView listView;

	private LinearLayout welcomeLayout;

	private CenteredToolBar toolbar;


    private TextView pickApp;

    private SwipeRefreshLayout swipeRefresh;


    private NavigationView navigationView;

    private DrawerLayout drawerLayout;

    private MenuItem nav_item2;

    private MenuItem nav_item3;

    //private SharedPreferences sp;
    private File apk;

    private String xpath;


    private FloatingActionButton fab;

	private AppLovinHelper adhelp;



	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//new MyApp().init(this);
        xpath = Environment.getExternalStorageDirectory() + "/DexProtect";
		//sp = MyApp.getSharedPreferences();
        new ThemeManager(this, MyApp.getDefSharedPreferences()).init();

        setContentView(R.layout.activity_landing);
		

		setupToolbar(null);


        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        listView = (ListView) findViewById(R.id.history_list);
		welcomeLayout = (LinearLayout) findViewById(R.id.welcome_layout);
        pickApp = (TextView) findViewById(R.id.pickAppText);


        navigationView = (NavigationView) findViewById(R.id.shitstuff);
        View header=navigationView.getHeaderView(0);
        ((TextView) header.findViewById(R.id.version)).setText(vb());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                // This method will trigger on item Click of navigation menu
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    drawerLayout.closeDrawers();
					//adhelp.showIntertitial();
                    switch (menuItem.getItemId()) {
                        case R.id.about:
                            //LogFragment.addLog(getUniqueIMEIId());
                            // Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                            /*new AlertDialog.Builder(HomeActivity.this)
                                .setTitle("About")
                                .setMessage(Html.fromHtml(about()))
                                .setPositiveButton("OK", null)
                                .create().show();*/
                            startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                            // openMenu();
                            return true;
                        case R.id.setting:

                            startActivityForResult(new Intent(HomeActivity.this, SettingActivity.class), 2);
                            //startActivity();
                            //finish();
                            return true;
                        case R.id.exit:
							new DebugDialog().show(HomeActivity.this);
                            // pexit();
                            return true;
                            /* case R.id.donate_us:
                             // Linkify the message
                             String msg = "Make a donation if you really like DexProtector and want to support the development or if you use DexProtector commercially for your business.";
                             final SpannableString s = new SpannableString(msg); // msg should have url to enable clicking
                             Linkify.addLinks(s, Linkify.ALL);

                             final AlertDialog d = new AlertDialog.Builder(HomeActivity.this)
                             .setPositiveButton("PAYPAL", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface p1, int p2)
                             {
                             String du = "https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=SPYZ49ANSV6MQ&source=url";
                             Uri parse = Uri.parse(du);
                             Intent cintent = new Intent("android.intent.action.VIEW", parse);
                             startActivity(cintent);
                             }
                             })
                             .setNeutralButton("GCASH", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface p1, int p2)
                             {
                             Toast.makeText(HomeActivity.this, "Drop a Message to Developer!", 0).show();
                             String str = "https://m.facebook.com/dexprotector";
                             try
                             {
                             if (getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode >= 3002850)
                             {
                             Uri parse = Uri.parse(new StringBuffer().append("fb://facewebmodal/f?href=").append(str).toString());
                             Intent cintent = new Intent("android.intent.action.VIEW", parse);
                             startActivity(cintent);
                             }
                             else
                             {
                             Intent cintent = new Intent("android.intent.action.VIEW", Uri.parse("fb://page/dexprotector"));
                             startActivity(cintent);
                             }
                             }
                             catch (Exception e)
                             {
                             Intent cintent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                             startActivity(cintent);
                             }
                             }
                             })
                             .setNegativeButton("LATER", null)
                             .setTitle("Donate Us")
                             .setMessage(s)
                             .create();

                             d.show();

                             // Make the textview clickable. Must be called after show()
                             ((TextView)d.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());


                             return true;*/
                        case R.id.share:
                            String data = new StringBuffer().append("Hey, Get our ").append(getString(R.string.app_name)).append(" app with the ability to obfuscate apk @ ").append("https://play.google.com/store/apps/details?id=").append(getPackageName()).toString();
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, data);
                            startActivity(shareIntent); 
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            return true;
                        case R.id.rate:
                            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName())));
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            return true;
                        default:
                            //Snackbar.make(, "Coming Soon! 😂", 0).show();
                            return true;

                    }
                }
            });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View p1) {
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                }
            });
			
		adhelp = new AppLovinHelper(this);
		adhelp.setBannerId("52adea43e0c3fad2");
		adhelp.setBannerView((RelativeLayout) findViewById(R.id.ad_view));
		adhelp.setIntertitialId("a2724846654b5280");
		adhelp.setRewardedId("7a5710ab4a75cbdf");
		adhelp.build();
        /*if (!sp.getBoolean("welcome_warn", false))
         {
         final CustomCKDialog ccd = new CustomCKDialog(this);
         ccd.setTitle("Welcome")
         .setMessage(Html.fromHtml("<b>DexProtector for Android</b><br>DexProtector is the protector and obfuscator for Android platforms. It helps you secure your applications and libraries against unauthorized or illegal use, reverse engineering, and cracking."))
         .setCBMessage("Don't show again", null)
         .setPositive("OK", new DialogInterface.OnClickListener(){
         @Override
         public void onClick(DialogInterface p1, int p2)
         {
         // TODO: Implement this method
         sp.edit().putBoolean("welcome_warn", ccd.isChecked()).commit();

         }
         })
         //.setNegative("CANCEL", null)
         .create().show();
         }*/
        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.INSTALL_PACKAGES, Manifest.permission.REQUEST_INSTALL_PACKAGES}, 123);
		}

	}



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 123: {
                    //premission to read storage
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Application permissiom granted", 0).show();
                    } else {
                        Toast.makeText(this, "Please grant Permission to Access more features.", 0).show();
                    }
                    return;
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    install(apk);
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

    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "SDK Manager")
            .setIcon(R.drawable.ic_cog);
		menu.add(0, 1, 0, "Clear History")
            .setIcon(R.drawable.ic_whatshot);
		return super.onCreateOptionsMenu(menu);
	}





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case 0:
				//openMenu();
                startActivity(new Intent(HomeActivity.this, SDKManager.class));
				// showSnack("test");
                // Do Fragment menu item stuff here
                return true;
			case 1:
                new AlertDialog.Builder(HomeActivity.this)
                    .setTitle(getString(R.string.deleteSourceHistory))
                    .setMessage(getString(R.string.deleteSourceHistoryConfirm))
                    //.setIcon(R.drawable.ic_error_outline_black)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface p1, int p2) {
                            Utils.deleteFolder(new File(Environment.getExternalStorageDirectory() + "/DexProtect/output"));
                            rerunHistoryLoader();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
                break;
            default:
                break;
        }

        return false;
    }

    /*void openMenu(){
     DrawerBottomDialog bdialog = new DrawerBottomDialog("", null);
     // bdialog.on
     bdialog.show(getSupportFragmentManager(), null);
     }*/

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

	public void initHistoryLoader() {
        HistoryLoader historyLoader = new HistoryLoader();
        historyLoader.execute();
    }
    public void SetupList(List<SourceInfo> AllPackages) {

        if (AllPackages.size() < 1) {
            swipeRefresh.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
            pickApp.setVisibility(View.GONE);
            welcomeLayout.setVisibility(View.VISIBLE);
        } else {
            pickApp.setVisibility(View.VISIBLE);
            welcomeLayout.setVisibility(View.INVISIBLE);

            ArrayAdapter<SourceInfo> decompileHistoryItemArrayAdapter = new ArrayAdapter<SourceInfo>(getBaseContext(), R.layout.history_list_item, AllPackages) {
                @SuppressLint("InflateParams")
                @Override
                public View getView(final int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = getLayoutInflater().inflate(R.layout.history_list_item, null);
                    }
                    /*Animation animation =  AnimationUtils.loadAnimation(HomeActivity.this, R.anim.slide_on_bottom);
                     convertView.startAnimation(animation);*/


                    final SourceInfo pkg = getItem(position);
                    String iconPath = pkg.getPackagePath()/*Environment.getExternalStorageDirectory() + "/DexProtect/output/" + pkg.getPackageName()*/ + "/" + pkg.getPackageLabel() + ".apk";

                    final ViewHolder holder = new ViewHolder();
                    holder.cardlayout = (CardView) convertView.findViewById(R.id.historylistitemCardView1);
                    holder.packageLabel = (TextView) convertView.findViewById(R.id.history_item_label);
                    holder.packageName = (TextView) convertView.findViewById(R.id.history_item_package);
                    holder.packageDetail = (TextView) convertView.findViewById(R.id.history_item_detail);
                    holder.packageIcon = (ImageView) convertView.findViewById(R.id.history_item_icon);

                    convertView.setTag(holder);

                    holder.packageLabel.setText(pkg.getPackageLabel());
                    holder.packageName.setText(pkg.getPackageName());
                    holder.packageDetail.setText(StringUtils.humanReadableByteCount(new File(iconPath).length(), false));

                    if (pkg.getPackageLabel().equalsIgnoreCase(pkg.getPackageName())) {
                        holder.packageName.setVisibility(View.INVISIBLE);
                    }

                    try {
                        if (new File(iconPath).exists()) {
                            //Bitmap iconBitmap = BitmapFactory.decodeFile(iconPath);
                            holder.packageIcon.setImageDrawable(new MyAppInfo(HomeActivity.this, iconPath).getIcon());
                        } else {
                            holder.packageIcon.setImageResource(R.drawable.ic_launcher);
                        }
					} catch (Exception e) {}
                    holder.cardlayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
								apk = new File(pkg.getPackagePath() + "/" + pkg.getPackageLabel() + ".apk");
								ArrayList<DrawerBottomDialog.Items> list = new ArrayList<DrawerBottomDialog.Items>();
								list.add(new DrawerBottomDialog.Items(R.drawable.ic_key, "Install"));
                                list.add(new DrawerBottomDialog.Items(R.drawable.ic_key, "Delete"));
                                DrawerBottomDialog bdialog = new DrawerBottomDialog("", new DrawerBottomDialog.Listener(){

                                        @Override
                                        public void onItemClick(int pos) {
                                            switch (pos) {
                                                case 0:
                                                    try {
                                                        if (apk.exists()) {
                                                            if (isSameSign(apk.getAbsolutePath(), pkg.getPackageName())) {
                                                                install(apk);
                                                            } else {
                                                                new AlertDialog.Builder(HomeActivity.this)
                                                                    .setCancelable(false)
                                                                    .setTitle("Warn")
                                                                    .setMessage(getString(R.string.warn_sign_match))
                                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                                                                        @Override
                                                                        public void onClick(DialogInterface p1, int p2) {
                                                                            uninstall(pkg.getPackageName());
                                                                        }
                                                                    })
                                                                    .setNegativeButton("CANCEL", null)
                                                                    .create().show();
                                                            }
                                                        } else {
                                                            Utils.deleteFolder(new File(pkg.getPackagePath()));
                                                            rerunHistoryLoader();
                                                            

                                                        }
                                                    } catch (Exception e) {
                                                        Toast.makeText(HomeActivity.this, "Install intent failed." + e.getMessage(), 0).show();
                                                    }
                                                    break;
                                                case 1:
                                                    try {
                                                        Utils.deleteFolder(new File(pkg.getPackagePath()));


                                                        rerunHistoryLoader();
                                                        Toast.makeText(getApplicationContext(), pkg.getPackageLabel() + " deleted!", Toast.LENGTH_LONG).show();
                                                    } catch (Exception e) {
                                                        Toast.makeText(getApplicationContext(), "Fail to delete!", Toast.LENGTH_LONG).show();
                                                    }
                                                    break;
                                            }
                                        }


									}, list);
								// bdialog.on
								bdialog.show(getSupportFragmentManager(), null);
                                /* PopupMenu popup = new PopupMenu(HomeActivity.this, holder.cardlayout);
                                 //popup.getMenuInflater().inflate(R.menu.menu_history, popup.getMenu());
                                 popup.getMenu().add("Install");
                                 //popup.getMenu().add("Uninstall");
                                 popup.getMenu().add("Share");
                                 popup.getMenu().add("Delete");
                                 popup.show();
                                 popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                                 @Override
                                 public boolean onMenuItemClick(MenuItem item) {
                                 apk = new File(pkg.getPackagePath() + "/" + pkg.getPackageLabel() + ".apk");
                                 //LogFragment.addLog(sourceDir.getAbsolutePath());
                                 //final MyAppInfo mi = new MyAppInfo(HomeActivity.this, apk.getAbsolutePath());
                                 //adhelp.showIntertitial();
                                 switch (item.getTitle().toString()) {
                                 case "Install":
                                 //  adhelper.AdsShow();
                                 try {
                                 if (apk.exists()) {
                                 if (isSameSign(apk.getAbsolutePath(), pkg.getPackageName())) {
                                 install(apk);
                                 } else {
                                 new AlertDialog.Builder(HomeActivity.this)
                                 .setCancelable(false)
                                 .setTitle("Warn")
                                 .setMessage(getString(R.string.warn_sign_match))
                                 .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                                 @Override
                                 public void onClick(DialogInterface p1, int p2) {
                                 uninstall(pkg.getPackageName());
                                 }
                                 })
                                 .setNegativeButton("CANCEL", null)
                                 .create().show();
                                 }
                                 } else {
                                 Utils.deleteFolder(new File(Environment.getExternalStorageDirectory() + "/DexProtect/output/" + pkg.getPackageName()));
                                 rerunHistoryLoader();
                                 }
                                 } catch (Exception e) {
                                 Toast.makeText(HomeActivity.this, "Install intent failed." + e.getMessage(), 0).show();
                                 }
                                 return true;
                                 case "Share":
                                 //adhelper.AdsShow();
                                 try {
                                 if (apk.exists()) {
                                 //Uri fileUri = FileProvider.getUriForFile(HomeActivity.this,  "mph.trunksku.apps.dexpro.fileprovider", apk);
                                 Uri fileUri = FileProvider.getUriForFile(HomeActivity.this, getApplicationContext().getPackageName() + ".fileprovider", apk);
                                 Intent intent = ShareCompat.IntentBuilder.from(HomeActivity.this)
                                 .setStream(fileUri) // uri from FileProvider
                                 //.setType("text/html")
                                 .getIntent()
                                 .setAction(Intent.ACTION_SEND) //Change if needed
                                 .setDataAndType(fileUri, "image/*")
                                 .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                 startActivity(intent);

                                 }
                                 //Toast.makeText(getApplicationContext(), "Coming Soon.", Toast.LENGTH_LONG).show();
                                 } catch (Exception e) {
                                 Toast.makeText(getApplicationContext(), "Fail to share!", Toast.LENGTH_LONG).show();
                                 }
                                 return true;
                                 case "Delete":
                                 //  adhelper.AdsShow();
                                 try {
                                 Utils.deleteFolder(new File(pkg.getPackagePath()));
                                 rerunHistoryLoader();
                                 Toast.makeText(getApplicationContext(), pkg.getPackageLabel() + " deleted!", Toast.LENGTH_LONG).show();
                                 } catch (Exception e) {
                                 Toast.makeText(getApplicationContext(), "Fail to delete!", Toast.LENGTH_LONG).show();
                                 }
                                 return true;

                                 default:
                                 return true;
                                 }
                                 }
                                 });*/

                            }
                        });
                    return convertView;
                }
            };
            listView.setAdapter(decompileHistoryItemArrayAdapter);

            /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             public void onItemClick(AdapterView<?> parent, View view, int position, long id)
             {
             final ViewHolder holder = (ViewHolder) view.getTag();
             final File apk = new File(Environment.getExternalStorageDirectory() + "/DexProtect/output/" + holder.packageName.getText().toString() + "/" + holder.packageLabel.getText().toString() + ".apk");
             //LogFragment.addLog(sourceDir.getAbsolutePath());
             final MyAppInfo mi = new MyAppInfo(HomeActivity.this, apk.getAbsolutePath());



             }
             });*/
            swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        rerunHistoryLoader();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            swipeRefresh.setVisibility(View.VISIBLE);

            listView.setVisibility(View.VISIBLE);
        }

    }

    public void pexit() {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(HomeActivity.this);
        builder3.setMessage("Do you want to minimize or exit?");
        builder3.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    // TODO: Implement this method
                    new Semaphore(0, true).release();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                }
            });
        builder3.setNegativeButton("MINIMIZE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    // TODO: Implement this method
                    Intent intent = new Intent("android.intent.action.MAIN");
                    intent.addCategory("android.intent.category.HOME");
                    intent.setFlags(268435456);
                    startActivity(intent);
                }
            });
        builder3.setNeutralButton("CANCEL", null);
        builder3.show();
	}

    public String vb() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 128);
            return "Version " + packageInfo.versionName ;
        } catch (Exception e) {
            return "null";
        }
    }

    public void showSnack(String str) {
        Snackbar.make(toolbar, str, Snackbar.LENGTH_SHORT).show();
    }




    public void OpenFilePicker(View v) {
        /* Intent i = new Intent(this, FilePicker.class);

         i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
         i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
         i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);

         i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());

         startActivityForResult(i, FILE_PICKER);*/
    }

    private boolean isSameSign(String str, String pkg) {
        try {

            if (appInstalledOrNot(pkg)) {
                ApplicationInfo appInfo = getPackageManager().getApplicationInfo(pkg, 0);
                String sign = new SignCheck(HomeActivity.this, str).getCurrentSignature();
                String sign2 = new SignCheck(HomeActivity.this, appInfo.publicSourceDir).getCurrentSignature();
                if (sign.equals(sign2)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

	private void install(File file) {

		if (file.exists()) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				Uri apkUri = FileProvider.getUriForFile(HomeActivity.this,  getPackageName() + ".fileprovider", file);
				Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
				intent.setData(apkUri);
				intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
				startActivity(intent);
			} else {
				Uri apkUri = Uri.fromFile(file);
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		} else {
			Toast.makeText(this, "ّFile not found!", Toast.LENGTH_SHORT).show();
		}
	}

    public void uninstall(String app_pkg_name) {
        Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);  
        intent.setData(Uri.parse("package:" + app_pkg_name));  
        intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
        startActivityForResult(intent, 1);

    }

	private void cleanOldSources() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/ShowJava");
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (!file.getName().equalsIgnoreCase("sources")) {
                    try {
                        if (file.exists()) {
                            if (file.isDirectory()) {
                                FileUtils.deleteDirectory(file);
                            } else {
                                file.delete();
                            }
                        }
                    } catch (Exception e) {
                        // Ln.d(e);
                    }
                }
            }
        } else {
            dir.mkdirs();
        }
    }

    public String about() {
        try {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

            int currentYear = calendar.get(Calendar.YEAR);

            InputStream open = getResources().openRawResource(R.raw.about);
            ByteArrayOutputStream xf = new ByteArrayOutputStream();
            byte[] bArr = new byte[5242880];
            while (true) {
                int read = open.read(bArr);
                if (read == -1) {
                    break;
                }
                xf.write(bArr, 0, read);
            }
            return new String(xf.toByteArray()).replace("dateko", String.valueOf(currentYear)) ;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            pexit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // adhelper.AdsShow();

        rerunHistoryLoader();
    }

    private void rerunHistoryLoader() {
        HistoryLoader historyLoaderTwo = new HistoryLoader();
        historyLoaderTwo.execute();
        // adhelper.loadAdsRequest();


    }

    private static class ViewHolder {
        CardView cardlayout;
        TextView packageLabel;
        TextView packageDetail;
        TextView packageName;
        ImageView packageIcon;
        int position;
    }

    private class HistoryLoader extends AsyncTask<String, String, List<SourceInfo>> {

        private int mode = 0;

        /* public HistoryLoader(int mode){
         this.mode = mode;
         }*/ 

        @Override
        protected List<SourceInfo> doInBackground(String... params) {

            if (mode == 1) {

            } else if (mode == 2) {

            }

            List<SourceInfo> historyItems = new ArrayList<>();
            File showJavaDir = new File(xpath);
            showJavaDir.mkdirs();

            File nomedia = new File(showJavaDir, ".nomedia");

            if (!nomedia.exists() || !nomedia.isFile()) {
                try {
                    nomedia.createNewFile();
                } catch (IOException e) {
                    // Ln.d(e);
                }
            }

            File dir = new File(xpath + "/output");

            if (dir.exists()) {
                File[] files = dir.listFiles();
                if (files != null && files.length > 0)
                    for (File file : files) {
                        if (Utils.sourceExists(file)) {
                            historyItems.add(0, Utils.getSourceInfoFromSourcePath(file));
                        } else {
                            try {
                                if (file.exists()) {
                                    if (file.isDirectory()) {
                                        FileUtils.deleteDirectory(file);
                                    } else {
                                        file.delete();
                                    }
                                }

                            } catch (Exception e) {
                                //Ln.d(e);
                            }
                            if (file.exists() && !file.isDirectory()) {
                                file.delete();
                            }
                        }
                    }
            }
            Comparator<SourceInfo> AppNameComparator = new Comparator<SourceInfo>() {
                public int compare(SourceInfo o1, SourceInfo o2) {
                    return o1.getPackageLabel().toLowerCase().compareTo(o2.getPackageLabel().toLowerCase());
                }
            };
            Collections.sort(historyItems, AppNameComparator);
            return historyItems;
        }

        @Override
        protected void onPostExecute(List<SourceInfo> AllPackages) {
            SetupList(AllPackages);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(String... text) {
        }
    }


}
