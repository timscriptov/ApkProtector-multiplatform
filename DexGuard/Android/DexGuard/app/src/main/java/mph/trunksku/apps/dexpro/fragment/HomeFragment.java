package mph.trunksku.apps.dexpro.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.mph.dexprotect.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import mph.trunksku.apps.dexpro.MyApp;
import mph.trunksku.apps.dexpro.SettingActivity;
import mph.trunksku.apps.dexpro.async.ProtectAsync;
import mph.trunksku.apps.dexpro.async.Worker;
import mph.trunksku.apps.dexpro.logger.DebugLog;
import mph.trunksku.apps.dexpro.logger.Log;
import mph.trunksku.apps.dexpro.model.IgnoredClass;
import mph.trunksku.apps.dexpro.model.iProject;
import mph.trunksku.apps.dexpro.utils.MyAppInfo;
import mph.trunksku.apps.dexpro.utils.Utils;
import mph.trunksku.apps.dexpro.utils.ZipUtils;
import mph.trunksku.apps.dexpro.view.CustomCKDialog;
import mph.trunksku.apps.dexpro.view.DebugDialog;
import mph.trunksku.apps.dexpro.view.IgnoredClassDialog;
import mph.trunksku.apps.dexpro.view.LogDialog;
import org.jf.dexlib2.DexFileFactory;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.iface.ClassDef;
import mph.trunksku.apps.dexpro.admob.AppLovinHelper;
import android.widget.RelativeLayout;
//import com.google.android.gms.ads.reward.*;

public class HomeFragment extends Fragment {

	private View mView;




	private SharedPreferences sp;

	private static ImageView apkIcon;

	private static TextView apkName;

	private static Button protect;

	private static TextView apkPack;

    private static CoordinatorLayout clayout;

    public static String apkpath = "bleh";

    private List<IgnoredClass> list = new ArrayList<IgnoredClass>();
    public static List<IgnoredClass> item = new ArrayList<IgnoredClass>();
	private List<IgnoredClass> reslist = new ArrayList<IgnoredClass>();
    public static List<IgnoredClass> resitem = new ArrayList<IgnoredClass>();

    private static FragmentActivity context;


	private iProject project;

	private static LinearLayout featureList;

	private static AppLovinHelper adhelp;



	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.activity_main, container, false);
		sp = MyApp.getSharedPreferences();
		project = new iProject();
        /*if(sp.getBoolean("firstStart", true)){
         addTime(10);
         sp.edit().putBoolean("firstStart", false).commit();
         }*/
		context = getActivity();
		writeFolder();

        clayout = (CoordinatorLayout) mView.findViewById(R.id.clayout);
		apkIcon = (ImageView) mView.findViewById(R.id.apkIcon);
		apkName = (TextView) mView.findViewById(R.id.apkName);
		apkPack = (TextView) mView.findViewById(R.id.apkPackage);
		//((TextView) mView.findViewById(R.id.token)).setText(sp.getInt("xTimerx", 0)+ "");

        featureList = (LinearLayout) mView.findViewById(R.id.featureList);
        addCategory(featureList, "Main Features");
        /*addCheck(featureList, "Custom Application Class", false, new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
                    //sp.edit().putBoolean("ChangePackage", isChecked).commit();
                }
            });*/
       /* addCheck(featureList, "Obfuscate Class", false, new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
                    project.setObfuscate(isChecked);
                    //sp.edit().putBoolean("ChangePackage", isChecked).commit();
                }
            });*/
		addCheck(featureList, "Class Filter", false, new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
					project.setClassFilter(isChecked);
                    //sp.edit().putBoolean("ClassFilter", isChecked).commit();
                    if (isChecked) {
                        new IgnoredClassDialog().show(getActivity(), view, item, list, project.getIgnored(), new IgnoredClassDialog.Listener(){
                                @Override
                                public void onAdd(List<IgnoredClass> selected, boolean mode) {
                                    list.clear();
                                    list = selected;
                                    ArrayList<String> ignoredLibs = new ArrayList<>();
                                    for (IgnoredClass ignored : list) {
                                        ignoredLibs.add(ignored.getLabel());
                                    }
                                    String ignored = ignoredLibs.toString().replace("[", "").replace("]", "").replace(" ", "");
                                    //sp.edit().putBoolean("isIgnored", mode).commit();
                                    String title = mode ? "Ignored: ": "Unignored: ";
                                    Log.i("", title + ignored);
									project.setIgnored(mode);
									project.setIgnoredClass(ignored);
                                    //MyApp.getDefSharedPreferences().edit().putString("ignoredLibs", ignored).commit();

                                }
                            });
                    }
                }
            });
		addCheck(featureList, "Encrypt Dex String", false, new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
					project.setStrDexGuard(isChecked);
                    //sp.edit().putBoolean("ResShrink", isChecked).commit();
                }
            });
		addCheck(featureList, "Shrink Resources", false, new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
					project.setResGuard(isChecked);
					if (isChecked) {
                        new IgnoredClassDialog().show(getActivity(), view, resitem, reslist, false, new IgnoredClassDialog.Listener(){
                                @Override
                                public void onAdd(List<IgnoredClass> selected, boolean mode) {
                                    reslist.clear();
                                    reslist = selected;
                                    ArrayList<String> ignoredLibs = new ArrayList<>();
                                    for (IgnoredClass ignored : reslist) {
                                        ignoredLibs.add(ignored.getLabel());
                                    }
                                    String ignored = ignoredLibs.toString().replace("[", "").replace("]", "").replace(" ", "");
                                    //sp.edit().putBoolean("isIgnored", mode).commit();
                                    String title = mode ? "Ignored: ": "Unignored: ";
                                    //Log.i("", title +ignored);
									//project.setIgnored(mode);
									//project.setIgnoredClass(ignored);
                                    //MyApp.getDefSharedPreferences().edit().putString("ignoredLibs", ignored).commit();

                                }
                            });
                    }
                    //sp.edit().putBoolean("ResShrink", isChecked).commit();
                }
            });
		/*addCheck(featureList, "DexProtect Dictionary", false, new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
                    if (isChecked) {
						dialogSetup("DexPro Dictionary", "Dictionary", FileUtils.readFile(Environment.getExternalStorageDirectory() + "/DexProtect/tmp/dictionary.txt"), view, new diagListener(){
								@Override
								public void onSave(String a) {
									try {
										FileUtils.writeFile(Environment.getExternalStorageDirectory() + "/DexProtect/tmp/dictionary.txt" , a);
									} catch (IOException e) {}
								}
							});
					}
					//sp.edit().putBoolean("ChangePackage", isChecked).commit();
                }
            });*/
        /* addCheck(featureList, "Change Package", sp.getBoolean("ChangePackage", false), new CheckListener(){
         @Override
         public void onChange(CheckBox view, boolean isChecked) {
         sp.edit().putBoolean("ChangePackage", isChecked).commit();
         }
         });*/
        addCategory(featureList, "APK Security");
        addCheck(featureList, "Anti Modified", sp.getBoolean("AntiMod", false), new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
                    project.setAntiMod(isChecked);
					// sp.edit().putBoolean("AntiMod", isChecked).commit();
                }
            });
        addCheck(featureList, "Sign Checker", sp.getBoolean("SignCheck", false), new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
                    project.setSignCheck(isChecked);
					//sp.edit().putBoolean("SignCheck", isChecked).commit();
                }
            });
        addCheck(featureList, "Hooks Detection", sp.getBoolean("AntiPirate", false), new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
                    if (isChecked) {
                        hooksSetup(view);
                    } else {
						project.setHookDetection(false);
                        //sp.edit().putBoolean("AntiPirate", false).commit();
                    }

                }
            });
        addCheck(featureList, "Device Lock", sp.getBoolean("DeviceLock", false), new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
                    if (isChecked) {
                        deviceidSetup(view);
                    } else {
						project.setDeviceLock(false);
                        //sp.edit().putBoolean("DeviceLock", false).commit();
                    }
                }
            });
        addCategory(featureList, "Test");
        addCheck(featureList, "Crash Catcher", sp.getBoolean("CrashCatcher", false), new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
                    project.setCrashCatch(isChecked);
					// sp.edit().putBoolean("CrashCatcher", isChecked).commit();
                }
            });
        addCheck(featureList, "Welcome Message", sp.getBoolean("Welcome", false), new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
                    if (isChecked) {
                        toastSetup(view);
                    } else {
						project.setWelcome(false);
                        //sp.edit().putBoolean("Welcome", false).commit();
                    }
                }
            });
		addCheck(featureList, "Splash Screen", sp.getBoolean("ShowSplash", false), new CheckListener(){
                @Override
                public void onChange(CheckBox view, boolean isChecked) {
                    project.setSplash(isChecked);
					//sp.edit().putBoolean("ShowSplash", isChecked).commit();

                }
            });
        /*   antimod = (CheckBox) mView.findViewById(R.id.antimod);
         antimod.setChecked(sp.getBoolean("AntiMod", false));
         antimod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton p1, boolean p2) {
         sp.edit().putBoolean("AntiMod", p2).commit();
         }
         });
         signcheck = (CheckBox) mView.findViewById(R.id.signcheck);
         signcheck.setChecked(sp.getBoolean("SignCheck", false));
         signcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton p1, boolean p2) {
         sp.edit().putBoolean("SignCheck", p2).commit();
         }
         });
         rootcheck = (CheckBox) mView.findViewById(R.id.rootcheck);
         rootcheck.setChecked(sp.getBoolean("RootCheck", false));
         rootcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton p1, boolean p2) {
         sp.edit().putBoolean("RootCheck", p2).commit();
         }
         });
         emucheck = (CheckBox) mView.findViewById(R.id.emucheck);
         emucheck.setChecked(sp.getBoolean("EmuCheck", false));
         emucheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton p1, boolean p2) {
         sp.edit().putBoolean("EmuCheck", p2).commit();
         }
         });
         playcheck = (CheckBox) mView.findViewById(R.id.installercheck);
         playcheck.setChecked(sp.getBoolean("PlayCheck", false));
         playcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton p1, boolean p2) {
         sp.edit().putBoolean("PlayCheck", p2).commit();
         }
         });
         crashcatcher = (CheckBox) mView.findViewById(R.id.crashcatcher);
         crashcatcher.setChecked(sp.getBoolean("CrashCatcher", false));
         crashcatcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton p1, boolean p2) {
         sp.edit().putBoolean("CrashCatcher", p2).commit();
         }
         });
         hooks = (CheckBox) mView.findViewById(R.id.hookscheck);
         hooks.setChecked(sp.getBoolean("AntiPirate", false));
         hooks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton p1, boolean p2) {
         if (p2) {
         hooksSetup();
         } else {
         sp.edit().putBoolean("AntiPirate", false).commit();
         }

         }
         });
         devicetest = (CheckBox) mView.findViewById(R.id.devicetest);
         devicetest.setChecked(sp.getBoolean("DeviceLock", false));
         devicetest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton p1, boolean p2) {
         if (p2) {
         deviceidSetup();
         } else {
         sp.edit().putBoolean("DeviceLock", false).commit();
         }
         }
         });
         stoast = (CheckBox) mView.findViewById(R.id.showtoast);
         stoast.setChecked(sp.getBoolean("Welcome", true));
         stoast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton p1, boolean p2) {
         if (p2) {
         toastSetup();
         } else {
         sp.edit().putBoolean("Welcome", false).commit();
         }
         }
         });
         showsplash = (CheckBox) mView.findViewById(R.id.showsplash);
         showsplash.setChecked(sp.getBoolean("ShowSplash", false));
         showsplash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton p1, boolean p2) {
         sp.edit().putBoolean("ShowSplash", p2).commit();
         }
         });*/


		protect = (Button) mView.findViewById(R.id.protect);


        protect.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1) {
					// TODO: Implement this method
					//Toast.makeText(getActivity(), "Token Left: " + sp.getInt("token", 0), 0).show();
					//if (MyApp.isNetworkAvailable())
                    //{
					//if(isAfford(1)){
			        if(!new File(getActivity().getFilesDir().getAbsolutePath() + "/android.jar").exists()){
						showSnack("Required to download SDK!");
					}else if(!new File(getActivity().getFilesDir().getAbsolutePath() + "/d8.jar").exists()){
						showSnack("Required to download SDK!");
					}else if(!new File(getActivity().getFilesDir().getAbsolutePath() + "/dexpro.jar").exists()){
						showSnack("Required to download SDK!");
					}else{
						final File apk = new File(apkpath);
						if (apk.exists() && apk.isFile()) {
							StringBuffer sb = new StringBuffer();
							sb.append("This will convert your app to a secure cloned version.")
								.append("<br><br><b>WARNING: The cloned app may crash, show errors or work incorrectly.</b>")
								.append("<br><br>The app's certificate will change. The app may not work if it verifies its certificate.")
								.append("<br><br>Please be fair and don't downrate DexProtector because your app doesn't work :)")
								//.append("<br><br>App components that depend on the certificate such as <b>Google account logins</b>, Google Play Services, Google Maps <b>maybe will not work</b>.")
								//.append(" In-app purchases maybe don't work and existing purchases and licenses can't be transferred to cloned apps.")
								;

							if (!sp.getBoolean("obf_warn", false)) {
								final CustomCKDialog ccd = new CustomCKDialog(getActivity());
								ccd.setTitle("Read this carefully")
									.setMessage(Html.fromHtml(sb.toString()))
									.setCBMessage("Don't show again", null)
									.setPositive("OBFUSCATE", new DialogInterface.OnClickListener(){
										@Override
										public void onClick(DialogInterface p1, int p2) {
											// TODO: Implement this method
											sp.edit().putBoolean("obf_warn", ccd.isChecked()).commit();
											runProcess(apk);
										}
									})
									.setNegative("CANCEL", null)
									.create().show();
							} else {

								runProcess(apk);
							}
						} else {
							showSnack("Invalid Apk File!");
						}
						
					}
                    
					//}else{
                    //Toast.makeText(getActivity(), "Not Enough Token!", 1).show();
					//}

                    /*}
                     else
                     {
                     showSnack("No Internet Connection!");
                     }*/

					//Toast.makeText(MainActivity.this, new MyAppInfo(MainActivity.this,apkpath.getText().toString()).getSignature(), 0).show();
				}
			});
		//LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout1);
		for (int i = 0; i < featureList.getChildCount();  i++) {
			View view = featureList.getChildAt(i);
			view.setEnabled(false); // Or whatever you want to do with the view.
		}
		
		adhelp = new AppLovinHelper(getActivity());
		adhelp.setBannerId("52adea43e0c3fad2");
		adhelp.setBannerView((RelativeLayout) mView.findViewById(R.id.ad_view));
		adhelp.setIntertitialId("a2724846654b5280");
		adhelp.setRewardedId("7a5710ab4a75cbdf");
		adhelp.build();
		return mView;
	}

	/*public void addTime(int t){
     sp.edit().putInt("xTimerx", sp.getInt("xTimerx", 0) + t).commit();
     ((TextView) mView.findViewById(R.id.token)).setText(sp.getInt("xTimerx", 0)+ "");
     }

     public void minusTime(int t){
     sp.edit().putInt("xTimerx", sp.getInt("xTimerx", 0) - t).commit();
     ((TextView) mView.findViewById(R.id.token)).setText(sp.getInt("xTimerx", 0)+ "");
     }


     public void saveRemainTime(int i){
     sp.edit().putInt("xTimerx", i).commit();
     ((TextView) mView.findViewById(R.id.token)).setText(sp.getInt("xTimerx", 0)+ "");
     }

     public boolean isAfford(int i){
     return sp.getInt("xTimerx", 0) > i;
     }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    

    public final void addCategory(LinearLayout view, String text) {
        TextView textView = new TextView(getActivity());
        textView.setBackgroundColor(R.color.colorAccent);
        textView.setText(text);
        textView.setGravity(17);
        textView.setTextSize(14.0f);
        textView.setTextColor(Color.WHITE);
        textView.setTypeface(null, 1);
        textView.setPadding(10, 5, 0, 5);
        view.addView(textView);
    }

    private CheckBox addCheck(LinearLayout view, String name, boolean isChecked, final CheckListener listener) {
        final CheckBox cb = new CheckBox(getActivity());
        cb.setText(name);
        //cb.setChecked(isChecked);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton p1, boolean p2) {
                    listener.onChange(cb, p2);
                }
            });
        view.addView(cb);
        return cb;
    }

    public interface CheckListener {
        void onChange(CheckBox view, boolean isChecked);
    }

    private static String useSplitToRemoveLastPart(String str) {
        try {
            String[] arr = str.split("/");
            String result = "";
            if (arr.length > 0) {
                result = str.substring(0, str.lastIndexOf("/" + arr[arr.length - 1]));
            }
            return result;
        } catch (Exception e) {
            return str;
        }
    }

    private static String useSplit(String str) {
        //str.split("/").length
        if (str.contains("/")) {
            if (str.split("/").length == 0) {
                return str.split("/")[0];
            }
            return str.split("/")[0] + "/" + str.split("/")[1];
        } else {
            return str;
        }
    }

    private static boolean isExist(String className, List<IgnoredClass> list) {
        for (IgnoredClass ignoredClass : list) {
            if (className.startsWith(ignoredClass.getName())) {
                return true;
            }
        }
        return false;
    }

    public static void change(final Context context, final String path) {
        final File apk = new File(path);

        final ProgressDialog pd = new ProgressDialog(context);
        //pd.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        pd.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);
		pd.setCancelable(false);
        // adhelper.loadAdsRequest();
        new Worker(new Worker.WorkerAction(){
                private boolean loaded = false;
                private boolean isprotected = false;
                private boolean notexist = false;

                @Override
                public void run() {
                    pd.setMessage("Loading apk...");
                    pd.show();
                }

                @Override
                public void runFirst() {
                    if (apk.exists()) {
                        if (ZipUtils.isExist("classes.dex.dat", new File(path))) {
                            isprotected = true;
                            //protect.setText("Already Protected");
                            //protect.setEnabled(false);
                        } else {
                            try {
								resitem.clear();
								List<String> res = ZipUtils.getRes(new File(path));
								for (int i = 0; i < (int)(res.size()); i++) {
									if (isExist(res.get(i).toString(), resitem)) {

									} else {
                                        resitem.add(new IgnoredClass(res.get(i).toString()));
									}
								}
                                item.clear();
                                File test = ZipUtils.readFile(context, new File(path), "classes.dex");
                                DexBackedDexFile dexFile = DexFileFactory.loadDexFile(test, Opcodes.getDefault());
                                for (ClassDef classDef : dexFile.getClasses()) {

                                    String name = useSplit(useSplitToRemoveLastPart(classDef.getType())).replace("/", ".").replace("L", "").replace(";", "");
                                    // String name = classDef.getType().substring(0, classDef.getType().lastIndexOf("/")).replace("/", ".").replace("L", "");
                                    /*if(name.endsWith(";")){

                                     }else */if (isExist(name, item)) {

                                    } else {
                                        item.add(new IgnoredClass(name));
                                    }
                                }
                                test.delete();
                            } catch (Exception e) {
                                Log.i("", e.getMessage());
                            }
                            loaded = true;
                            //protect.setText("Protect");
                            //protect.setEnabled(true);
                        }

                    } else {
                        notexist = true;
                    }
                    //showSnack(apk.getAbsolutePath());
                    //  Log.i("", apk.getAbsolutePath());


                }

                @Override
                public void runLast() {
                    pd.dismiss();
                    if (loaded) {
                        apkpath = path;
                        MyAppInfo mai = new MyAppInfo(context, apk.getAbsolutePath());
                        apkIcon.setImageDrawable(mai.getIcon());
                        apkName.setText(mai.getAppName());
                        apkPack.setText(mai.getPackage());
                        showSnack("Done.");
						for (int i = 0; i < featureList.getChildCount();  i++) {
							View view = featureList.getChildAt(i);
							view.setEnabled(true); // Or whatever you want to do with the view.
						}
						
                    } else {
                        if (isprotected) {
                            new AlertDialog.Builder(context)
                                .setMessage("Apk is already protected!")
                                .setPositiveButton("OK", null)
                                .create()
                                .show();
                        } else if (notexist) {
                            new AlertDialog.Builder(context)
                                .setMessage("Apk not exist!")
                                .setPositiveButton("OK", null)
                                .create()
                                .show();
                        } else {
                            new AlertDialog.Builder(context)
                                .setMessage("Apk failed to load!")
                                .setPositiveButton("OK", null)
                                .create()
                                .show();
                        }
                        apkIcon.setImageResource(R.drawable.ic_launcher);
                        apkName.setText("Select APK");
                        apkPack.setText("none");
                        apkpath = "asa";
                    }
					//adhelper.loadIntertitialAds();
					adhelp.showIntertitial();
                    // adhelper.AdsShow();
					
                }
            }).execute();

    }


	public static void showSnack(String str) {
        Snackbar.make(clayout, str, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnack2(String str) {
        Snackbar.make(clayout, str, Snackbar.LENGTH_INDEFINITE).setAction("Show", new View.OnClickListener() {
                @Override
                public void onClick(View p1) {
                    // TODO: Implement this method
                    //getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
                    apkpath = "wala";
                    item.clear();
                    getActivity().finish();
                }
            }).show();
    }


	void runProcess(final File apk) {

		//minusTime(1);
        start(apk);


	}

	public void onActivityResult(int request, int result, Intent data) {
		switch (request) {
			case 1:
				String uri = data.getStringExtra("apkpath");

				return;
			default:
                super.onActivityResult(request, result, data);
                return;
		}
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);

        menu.add("Setting")
            .setIcon(R.drawable.ic_cog);
		menu.add("Get Token")
            .setIcon(R.drawable.ic_whatshot);
        //.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        /* menu.addSubMenu("Menu item 2")
         .setIcon(R.drawable.ic_check_o);*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {


            case "Setting":
                startActivity(new Intent(getActivity(), SettingActivity.class));
                // showSnack("test");
                // Do Fragment menu item stuff here
                return true;
			case "Get Token":
				adhelp.showAds();
                /*adhelper.loadRewardedAds(new AdmobHelper.RewardedListener(){

                 private AlertDialog adprog;

                 @Override
                 public void onLoad()
                 {
                 // TODO: Implement this method
                 adprog = new AlertDialog.Builder(getActivity())
                 .setMessage("Loading video ads...")
                 .create();
                 adprog.show();
                 }

                 @Override
                 public void onLoaded()
                 {
                 // TODO: Implement this method
                 adprog.dismiss();
                 adhelper.showRewardedAds();
                 }

                 @Override
                 public void onReward(RewardItem rewarditem)
                 {
                 // TODO: Implement this method
                 addTime(2);
                 }

                 @Override
                 public void onClose()
                 {
                 // TODO: Implement this method
                 }

                 @Override
                 public void onFaild()
                 {
                 // TODO: Implement this method
                 adprog.dismiss();
                 new AlertDialog.Builder(getActivity())
                 .setTitle("Video currently not available")
                 .setMessage("Why?\n\nThe video are provided by third parties and are limited supply.\n\nMake sure your internet connection is stable. Then try again. If the video is still not available, please try again Later.")
                 .setPositiveButton("OK", null)
                 .create().show();
                 }

                 });	*/
                // Do Fragment menu item stuff here
                return true;
            default:
                break;
        }

        return false;
    }

	public void hooksSetup(final CheckBox cb) {
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
		LinearLayout ll = new LinearLayout(getActivity());
		ll.setOrientation(1);
		ll.setPadding(40, 0, 40, 0);
		ll.setLayoutParams(layoutParams);
		final TextInputLayout til0 = new TextInputLayout(getActivity());
		final AppCompatEditText acet0 = new AppCompatEditText(getActivity());
		acet0.setHint("Hooks App (Enter as Delimiter)");
		acet0.setText(sp.getString("hooks", ""));
		til0.addView(acet0);
		ll.addView(til0);
		new AlertDialog.Builder(getActivity())
            .setCancelable(false)
			.setTitle("Add Hooks App")
			.setView(ll)
			.setPositiveButton("SAVE", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2) {
					// TODO: Implement this method
					//sp.edit().putString("hooks", acet0.getText().toString()).commit();
                    //sp.edit().putBoolean("AntiPirate", true).commit();
					project.setHookedApps(acet0.getText().toString());
					project.setHookDetection(true);
				}
			})
			.setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    // TODO: Implement this method
                    cb.setChecked(false);
					project.setHookDetection(false);
                    //sp.edit().putBoolean("AntiPirate", false).commit();
                }
			})
			.create().show();
	}

	public interface diagListener {
		public void onSave(String a);
	}

	public void dialogSetup(String title, String hint, String text, final CheckBox cb, final diagListener l) {
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
		LinearLayout ll = new LinearLayout(getActivity());
		ll.setOrientation(1);
		ll.setPadding(40, 0, 40, 0);
		ll.setLayoutParams(layoutParams);
		final TextInputLayout til0 = new TextInputLayout(getActivity());
		final AppCompatEditText acet0 = new AppCompatEditText(getActivity());
		acet0.setHint(hint);
		acet0.setText(text);
		til0.addView(acet0);
		ll.addView(til0);
		new AlertDialog.Builder(getActivity())
            .setCancelable(false)
			.setTitle(title)
			.setView(ll)
			.setPositiveButton("SAVE", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2) {
					l.onSave(acet0.getText().toString());
				}
			})
            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    // TODO: Implement this method
                    cb.setChecked(false);

                    //sp.edit().putBoolean("DeviceLock", false).commit();
                }
			})
			.create().show();
	}

	public void deviceidSetup(final CheckBox cb) {
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
		LinearLayout ll = new LinearLayout(getActivity());
		ll.setOrientation(1);
		ll.setPadding(40, 0, 40, 0);
		ll.setLayoutParams(layoutParams);
		final TextInputLayout til0 = new TextInputLayout(getActivity());
		final AppCompatEditText acet0 = new AppCompatEditText(getActivity());
		acet0.setHint("Device ID (Enter valid ID)");
		acet0.setText(sp.getString("DeviceID", ""));
		til0.addView(acet0);
		ll.addView(til0);
		new AlertDialog.Builder(getActivity())
            .setCancelable(false)
			.setTitle("Add Device ID")
			.setView(ll)
			.setPositiveButton("SAVE", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    // TODO: Implement this method
					project.setDeviceIds(acet0.getText().toString());
					project.setDeviceLock(true);
                    //sp.edit().putString("DeviceID", acet0.getText().toString()).commit();
                    //sp.edit().putBoolean("DeviceLock", true).commit();
                }
            })
            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    // TODO: Implement this method
                    cb.setChecked(false);
					project.setDeviceLock(false);
                    //sp.edit().putBoolean("DeviceLock", false).commit();
                }
			})
			.create().show();
	}

	public void toastSetup(final CheckBox cb) {
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
		LinearLayout ll = new LinearLayout(getActivity());
		ll.setOrientation(1);
		ll.setPadding(40, 0, 40, 0);
		ll.setLayoutParams(layoutParams);
        final RadioGroup rg = new RadioGroup(getActivity());
        RadioButton rb = new RadioButton(getActivity());
        rb.setText("TOAST");
        rb.setId(123);
        rg.addView(rb);
        RadioButton rb2 = new RadioButton(getActivity());
        rb2.setText("DIALOG (Beta)");
        rb2.setId(125);
        rg.addView(rb2);
        RadioButton rb3 = new RadioButton(getActivity());
        rb3.setText("NOTIFICATION");
        rb3.setId(124);
        rg.addView(rb3);

        rg.check(sp.getInt("WelcomeMode", 123));
        final TextInputLayout til0 = new TextInputLayout(getActivity());
		final AppCompatEditText acet0 = new AppCompatEditText(getActivity());
		acet0.setHint("Message (Enter Message)");
		acet0.setText(sp.getString("WelcomeMsg", "Powered by DexProtector"));
		til0.addView(rg);
        til0.addView(acet0);
		ll.addView(til0);
		new AlertDialog.Builder(getActivity())
            .setCancelable(false)
			.setTitle("Add Welcome Message")
			.setView(ll)
			.setPositiveButton("SAVE", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    // TODO: Implement this method
					project.setWelcomeMsg(acet0.getText().toString());
					project.setWelcomeMode(rg.getCheckedRadioButtonId());
					project.setWelcome(true);
                    //sp.edit().putString("WelcomeMsg", acet0.getText().toString()).commit();
                    //sp.edit().putInt("WelcomeMode", rg.getCheckedRadioButtonId()).commit();
                    //sp.edit().putBoolean("Welcome", true).commit();
                }
            })
            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2) {
                    // TODO: Implement this method
                    cb.setChecked(false);
					project.setWelcome(false);
                    //sp.edit().putBoolean("Welcome", false).commit();
                }
			})
			.create().show();
	}

	protected void writeFolder() {
		String fold = "DexProtect/";
		String[] folder = {/*fold,fold+"gen",*/ fold + "key", fold + "output"};
		for (int i = 0; i < folder.length; i++) {
			File f =new File(Environment.getExternalStorageDirectory() + "/" + folder[i]);
			if (!f.exists()) {
				f.mkdirs();
			}
		}
	}

	public void start(File apk) {
        MyAppInfo mi = new MyAppInfo(getActivity(), apk.getAbsolutePath());
		final File sourceDir = new File(Environment.getExternalStorageDirectory() + "/DexProtect/output/" + mi.getPackage() + "/" + mi.getAppName() + ".apk");
		if (sourceDir.exists()) {
			showAlreadyExistsDialog(apk.getAbsolutePath(), sourceDir);
		} else {
			new ProtectAsync(listener, project, getActivity()).execute(apk.getAbsolutePath());
		}
	}

	private void showAlreadyExistsDialog(final String apkx, final File source) {
        final File apk = new File(apkx);
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Already been obfuscated");
        alertDialog.setMessage("This application package has already been obfuscated once and the output app exists on your sdcard. What would you like to do ?");
        alertDialog.setPositiveButton("Install", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					install(source);
				}
			});

        alertDialog.setNegativeButton("Protect", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					 try {
                                System.gc();
                                Utils.deleteFolder(source);
                                new ProtectAsync(listener, project, getActivity()).execute(apk.getAbsolutePath());
                                
                            } catch (Exception e) {
                                //Crashlytics.logException(e);
                            }
                        
                    
					//apk.setText(pkg.packageFilePath);
					//setResult(1, new Intent().putExtra("apkpath", holder.packageFilePath.getText().toString()));
				}
			});

		alertDialog.setNeutralButton("Cancel", null);
        alertDialog.show();
    }

    private void install(File file) {

        if (file.exists()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri apkUri = FileProvider.getUriForFile(getActivity(),  "mph.trunksku.apps.dexpro.fileprovider", file);
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
            Toast.makeText(getActivity(), "ّFile not found!", Toast.LENGTH_SHORT).show();
        }
	}

    ProtectAsync.Listener listener = new ProtectAsync.Listener(){

        @Override
        public void onProtected() {
            Toast.makeText(getActivity(), "APK is already protected!", 1).show();
			//adhelper.loadIntertitialAds();
            //  adhelper.AdsShow();
        }


        @Override
        public void onAds() {
            // adhelper.showIntertitialAds();

        }

        @Override
        public void onCompleted() {
            //protect.hideProgress();
			DebugDialog.show(getActivity());
			//LogDialog.show(getActivity(), "DexPro Debug", DebugLog.dump(), "DONE", null);
            showSnack2("Obfuscate Complete!");
            //MainActivity.makeFree();
			//adhelper.loadIntertitialAds();
			adhelp.showAds();


        }

        @Override
        public void onFailed() {
            //protect.hideProgress();
			LogDialog.show(getActivity(), "DexPro Debug", DebugLog.dump(), "DONE", null);

            showSnack("Obfuscate Fail!");
            // adhelper.loadIntertitialAds();
			adhelp.showAds();

        }

        @Override
        public void onProcess() {
            /* protect.setMax(100);
             protect.setIndeterminate(true);
             protect.setShowProgressBackground(false);*/
        }
    };

	@Override
	public void onResume() {
        //adhelper.reloadAds();
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
