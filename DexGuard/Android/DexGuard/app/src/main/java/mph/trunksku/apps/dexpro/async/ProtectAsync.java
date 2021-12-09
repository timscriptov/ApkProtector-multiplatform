package mph.trunksku.apps.dexpro.async;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.view.WindowManager;
import com.android.apksig.ApkSigner;
import com.google.common.collect.ImmutableList;
import com.mph.dexprotect.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DeflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kellinwood.security.zipsigner.ProgressEvent;
import kellinwood.security.zipsigner.ProgressListener;
import kellinwood.security.zipsigner.optional.DistinguishedNameValues;
import kellinwood.security.zipsigner.optional.JksKeyStore;
import mph.trunksku.apps.dexpro.MyApp;
import mph.trunksku.apps.dexpro.core.DexOptimizer;
import mph.trunksku.apps.dexpro.core.DexStringEncryptor;
import mph.trunksku.apps.dexpro.core.EncryptResources;
import mph.trunksku.apps.dexpro.dexmerger.ApkDexMerger;
import mph.trunksku.apps.dexpro.dexmerger.DxContext;
import mph.trunksku.apps.dexpro.logger.DebugLog;
import mph.trunksku.apps.dexpro.model.ReplaceItem;
import mph.trunksku.apps.dexpro.model.iProject;
import mph.trunksku.apps.dexpro.utils.DexProMod;
import mph.trunksku.apps.dexpro.utils.ManifestModify;
import mph.trunksku.apps.dexpro.utils.MyAppInfo;
import mph.trunksku.apps.dexpro.utils.SignCheck;
import mph.trunksku.apps.dexpro.utils.SourceInfo;
import mph.trunksku.apps.dexpro.utils.StringUtils;
import mph.trunksku.apps.dexpro.utils.Utils;
import mph.trunksku.apps.dexpro.utils.ZipUtils;
import mph.trunksku.apps.dexpro.view.iProgressDialog;
import org.apache.commons.io.FileUtils;
import org.jf.baksmali.BaksmaliOptions;
import org.jf.dexlib2.DexFileFactory;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.DexFile;
import org.jf.dexlib2.immutable.ImmutableDexFile;
import org.jf.dexlib2.rewriter.DexRewriter;
import org.jf.dexlib2.rewriter.Rewriter;
import org.jf.dexlib2.rewriter.RewriterModule;
import org.jf.dexlib2.rewriter.Rewriters;
import org.jf.dexlib2.writer.pool.DexPool;
import org.json.JSONArray;
import org.json.JSONObject;
import sun1.security.pkcs.PKCS8Key;
import mph.trunksku.apps.dexpro.core.ProguardDex;

public class ProtectAsync extends AsyncTask<String, String, Boolean> {

	private Context context;
	String path = Environment.getExternalStorageDirectory() + "/DexProtect";
	String rootpath;
	String xpath;
	private iProgressDialog pd;
    private final ArrayList<String> ignoredLibs = new ArrayList<>();
    private final ArrayList<String> dexName = new ArrayList<>();
	private ArrayList fileList;
	private MyAppInfo mi;
	long startTime, endTime;
	private SharedPreferences sp;

    private DeflaterInputStream isx;

    private ProtectAsync.Listener listener;

    private SharedPreferences dsp;

	private iProject project;

    public interface Listener {
        void onCompleted();
        void onAds();
        void onProtected();
        void onFailed();
        void onProcess();
    }

	public ProtectAsync(Listener listener, iProject project, Context c) {
        this.listener = listener;
		this.project = project;
		context = c;
	    sp = MyApp.getSharedPreferences();
        dsp = MyApp.getDefSharedPreferences();

		rootpath = c.getFilesDir().getAbsolutePath();
		xpath = rootpath + "/dexpro";
		fileList = new ArrayList();
		pd = new iProgressDialog(c);
		pd.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		//pd.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);

		pd.setCancelable(false);
		//pd.setTitle(R.string.app_name);
		//pd.setIcon(R.drawable.ic_launcher);
	}

	@Override
	protected void onPreExecute() {
		// TODO: Implement this method
        listener.onProcess();
		startTime = System.currentTimeMillis();

		//mBuilder.setSmallIcon(R.drawable.ic_lock_rotate);
		pd.show();
		try {
			DebugLog.clear();
		} catch (Exception e) {
			addLog(e.getMessage());
		}
		new File(rootpath + "/dexpro").mkdirs();
		Utils.deleteFolderContent(new File(xpath + "/gen"));
		Utils.deleteFolderContent(new File(xpath + "/output"));
        File folder = new File(xpath + "/gen");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File folder2 = new File(xpath + "/key");
        if (!folder2.exists()) {
            folder2.mkdir();
        }
        File folder3 = new File(xpath + "/output");
        if (!folder3.exists()) {
            folder3.mkdir();
		}
        File folder4 = new File(xpath + "/export");
        if (!folder4.exists()) {
            folder4.mkdir();
		}
	}

	@Override
	protected Boolean doInBackground(String[] p1) {
		// TODO: Implement this method
		boolean t = false;
		mi = new MyAppInfo(context, p1[0]);
		//pd.setIcon(mi.getIcon());
		publishProgress(mi.getAppName(), "Initializing...");
		/*if (addSign())
         {*/
        publishProgress(mi.getAppName(), "Compiling...");
		addLog2("Compiler Started");
        if (unZipIt(p1[0])) {
            publishProgress(mi.getAppName(), "Modifying...");
            if (manifestchange(mi.getPackage())) {
                publishProgress(mi.getAppName(), "eProtect Adding...");
                if (addProtect()) {
                    publishProgress(mi.getAppName(), "DEX - Optimising...");
					addLog2("DEX - OPTIMIZE");
                    if (enDex(p1[0])) {
                        //publishProgress(mi.getAppName(), "DEX - Merging...");
						addLog2("DEX - MERGE");
                        if (addMyDex()) {
                            //zlib();
                            publishProgress(mi.getAppName(), "Building APK...");
							addLog2("APK BUILDER");
                            if (z()) {
								File apkp = new File(xpath + "/output/unsigned.tmp");
								try {
									int size = ApkDexMerger.getClassesDexPathsFormApk(apkp).size();
									addLog("DEX Count: " + size);
									boolean multdex = true;
									if (multdex) {
                                        if (size > 1) {
                                            publishProgress(mi.getAppName(), "Merge Dex");
											addLog("### dexMerger Start  ###");

                                            //updateBuildDialog(obj, Utils.isCN() ? "Merge Dex" : "Mergeing Dex");
                                            try {

                                                dexMerger(apkp);
                                            } catch (Throwable th) {
                                                addLog("dexMerger Fail: " + th.getMessage());
                                                //updateBuildDialog(obj, Utils.isCN() ? "Failed to merge DEX. Please see. / file / log to see the reason." : "Failed to merge Dex");
                                                try {
                                                    Thread.sleep((long) 2300);
                                                } catch (InterruptedException e) {
                                                }
                                            }
											addLog("### dexMerger End  ###");
                                        }
									}
								} catch (IOException e) {
									addLog(e.getMessage());
								}

                                if (project.getResGuard()) {

                                    publishProgress(mi.getAppName(), "Shrink Resources...");
									addLog2("APK ResGuard");
                                    try {
										EncryptResources er = new EncryptResources(xpath + "/output/unsigned.tmp", xpath + "/output/unsigned1.tmp");
                                        //ro(xpath + "/output/unsigned.tmp", xpath + "/output/unsigned1.tmp");
                                        new File(xpath + "/output/unsigned.tmp").delete();
                                    }  catch (Exception e) {
                                        addLog(e.getMessage());
                                    }
                                }
								/*List<String> keeps = new ArrayList<String>();
                                 try {
                                 publishProgress(mi.getAppName(), "Encrypt String APK...");
                                 new ApkStringEncryptor(new StringBuffer().append(xpath).append(project.getResGuard() ? "/output/unsigned1.tmp" : "/output/unsigned.tmp").toString(), new StringBuffer().append(xpath).append("/output/unsigned1ent.tmp").toString(), keeps, new DexStringEncryptor.EncryptCallBack(){

                                 @Override
                                 public void onProgress(int progress, int total) {
                                 }

                                 @Override
                                 public void onClassDefName(String Name) {
                                 }
                                 }, new ApkStringEncryptor.UICallBack(){

                                 @Override
                                 public void onStep(String Name) {
                                 addLog(Name);
                                 }

                                 @Override
                                 public void onSaveProgress(int progress, int total) {
                                 }
                                 }).start();
                                 List<String> pkgs=new ArrayList<>();
                                 pkgs.add("xyz");
                                 //EncryptStringTask task = new EncryptStringTask(context, new StringBuffer().append(xpath).append(project.getResGuard() ? "/output/unsigned1.tmp" : "/output/unsigned.tmp").toString(), new StringBuffer().append(xpath).append("/output/unsigned1ent.tmp").toString());
                                 //task.start(6,pkgs);
                                 } catch (Exception e) {
                                 addLog("kk:"+e.getMessage());
                                 }*/

                                publishProgress(mi.getAppName(), "Signing APK...");
								addLog2("APK SIGNER");
                                SourceInfo.initialise(path + "/output", mi);
                                if (sign(new StringBuffer().append(xpath).append(project.getResGuard() ? "/output/unsigned1.tmp" : "/output/unsigned.tmp").toString(), path + "/output/" + mi.getPackage() + "/" + mi.getAppName().replace("/", "-") + ".apk")) {
                                    addLog("Signing apk complete");
                                    publishProgress(mi.getAppName(), "Done.");
                                    t = true;
                                }
                            }
                        }
                    }
                }
                //}

            }
			//}
		}
		return t;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// TODO: Implement this method
		endTime = System.currentTimeMillis();

		pd.dismiss();
		System.gc();
		Utils.deleteFolderContent(new File(xpath + "/gen"));
        Utils.deleteFolderContent(new File(xpath + "/output"));

		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");    
		Date resultdate = new Date(endTime - startTime);

		if (result) {
            snotify(R.drawable.ic_check_blue_a700_48dp, "Obfuscate Complete!", "Finished time: " + sdf.format(resultdate));

            listener.onCompleted();

		} else {
			final File sourceDir = new File(Environment.getExternalStorageDirectory() + "/DexProtect/output/" + mi.getPackage() + "");
			if (sourceDir.exists()) {
				Utils.deleteFolderContent(sourceDir);
			}
            snotify(R.drawable.ic_close_blue_a700_48dp, "Obfuscate Fail!", "Finished time: " + sdf.format(resultdate));

            listener.onFailed();

		}

	}

	@Override
	protected void onProgressUpdate(String[] values) {
		// TODO: Implement this method
		//pd.setTitle("Recompiling - " + values[0]);

		pd.setMessage(values[1]);
        //snotify(R.drawable.ic_lock_rotate, values[1], "Project " + values[0]);

	}

	public void addLog2(String str) {
		//Log.d("|", str.toString());
		DebugLog.i("kk", "> " + str);
	}

	public void addLog(String str) {
		//Log.d("|", str.toString());
		DebugLog.i("kk", str);
	}

    public void snotify(int icon, String title, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context)
            .setSmallIcon(icon)
		    .setContentTitle(title.toString())
            .setContentText(message)
            .setWhen(System.currentTimeMillis());
        Utils.setSmallNotificationIcon(builder, false);
        if (Build.VERSION.SDK_INT >= 16) {
            builder.setStyle(new Notification.BigTextStyle().bigText(message));
        }

        notificationManager.notify(1621373246, builder.getNotification());
    }

    /*public static void doFog(File file) throws Throwable {
     File bin = ProjectUtils.getBin(file);
     File binClassesRelease = ProjectUtils.getBinClassesRelease(file);
     String[] strArr = new String[0];
     String str = "com.github.megatronking.stringfog.xor.StringFogImpl";
     String str2 = "UTF-8";
     StringFogMappingPrinter stringFogMappingPrinter = new StringFogMappingPrinter(new File(bin.getAbsolutePath(), "stringFogMapping.txt"));
     StringFogClassInjector stringFogClassInjector = new StringFogClassInjector(strArr, str2, str, str, stringFogMappingPrinter);
     stringFogMappingPrinter.startMappingOutput();
     stringFogMappingPrinter.ouputInfo(str2, str);
     stringFogClassInjector.doFog2ClassInDir(binClassesRelease);
     //if (!ResourceUtils.copyFileFromAssets(Utils.getAssetsDataFile("stringfog"), binClassesRelease.getAbsolutePath())) {
     //throw new IOException();
     //}
     }*/
    private void dexMerger(File file) throws IOException {
        new ApkDexMerger(new DxContext(), file, new File(xpath)).merger(file);
    }


    public boolean addStringFog() {
		try {
			//StringFogClassInjector.doFog2Class(fileInput, fileOutput, mKey);
			//StringFogClassInjector.doFog2Jar(jarInputFile, jarOutputFile, mKey)
			return true;
		} catch (Exception e) {
			return false;
		}
	}



    /* protected boolean zlib() {
     try
     {
     String[] abi = new String[]{"armeabi", "armeabi-v7a", "arm64-v8a", "x86", "x86_64", "mips", "mips64"};
     for (int i = 0; i < abi.length; i++)
     {
     if(new File(xpath + "/gen/lib/"+abi[i]).exists()){
     ZipUtils zu = new ZipUtils();
     zu.zipIt(new File(xpath + "/gen/lib/"+abi[i]), new File(xpath + "/gen/assets/dp."+abi[i]+".dat"));
     }
     }
     Utils.deleteFolderContent(new File(xpath + "/gen/lib"));
     //addLog("Building apk complete");
     return true;
     }
     catch (Exception e)
     {
     addLog(e.getMessage());
     return false;
     }
     }*/

	protected boolean z() {
		try {
			File folder = new File(xpath + "/output");
			if (!folder.exists()) {
				folder.mkdir();
			}
			ZipUtils zu = new ZipUtils();
			zu.zipIt(new File(xpath + "/gen"), new File(xpath + "/output/unsigned.tmp"));
			//deleteFolderContent(new File(xpath + "/gen"));
			addLog("Building apk complete");
			return true;
		} catch (Exception e) {
			addLog(e.getMessage());
			return false;
		}
	}

	public static File dexToSmali(String dexFile, String outputDir) {
        try {
            Opcodes opCodes = Opcodes.getDefault();
            DexBackedDexFile dexBackedDexFile = DexFileFactory.loadDexFile(dexFile, opCodes);
            BaksmaliOptions options = new BaksmaliOptions();
            options.apiLevel = opCodes.api;
            //Baksmali.disassembleClass(dexBackedDexFile, new File(outputDir), 6, options);

            return new File(outputDir);
        } catch (Exception e) {
            //Log.error(e.toString() + ", trying to convert .dex to .smali");
            return null;
        }
    }

	/*public static void changeDex2Smali(File dexFile, File outputFile)
     {
     BaksmaliCmd.main(new String[]{"--force","--output",outputFile.getAbsolutePath(),dexFile.getAbsolutePath()});
     }

     public static void changeSmali2Dex(File dexFile, File outputFile)
     {
     SmaliCmd.main(new String[]{"--output",outputFile.getAbsolutePath(),dexFile.getAbsolutePath()});
     }*/

	public boolean unZipIt(String zipFileO) {
		byte[] buffer = new byte[2048];
		try {
			//create output directory is not exists
			float prev = -1; // to check if the percent changed and its worth updating the UI
    		int finalSize = 0;
    		float current = 0;
			String zipFile = null;
			File folder = new File(xpath + "/gen/assets");
			if (!folder.exists()) {
				folder.mkdir();
			}
			if (sign(zipFileO, xpath + "/gen/temp.apk")) {
				zipFile = xpath + "/gen/temp.apk";
			}
			//get the zip file content
            long totalRead = 0;
            long totalSize = new File(zipFile).length();

			finalSize = (int) new File(zipFile).length();
			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
			//get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				current += ze.getCompressedSize();
				FileOutputStream fos = null;
				String fileName = ze.getName();
				if (fileName.contains("lua.mph")) {
					addLog("APK is already protected!");
                    listener.onProtected();
					return false;
				} else if (!fileName.contains("META-INF")) {
					File newFile = new File(xpath + File.separator + "gen" + File.separator + fileName);
					new File(newFile.getParent()).mkdirs();
					fos = new FileOutputStream(newFile);
					//addLog("Recompiling - " + fileName);
					while (true) {
						int numread = zis.read(buffer);
                        totalRead += numread;
                      //  int perc = (int) (totalRead * 100 / totalSize);
                       /* if (perc > 100) {
                            perc = 100;
                        }*/
                        int perc = (int) ((totalRead / (float) totalSize) * 100);

						if (numread <= 0) {
							break;
						}
						fos.write(buffer, 0, numread);
                        pd.setProgress(perc);
                        pd.setMax(100);
                        pd.setSecondaryProgress(100);
                        publishProgress(mi.getAppName(), "Compiling... ("+ perc + "%)", "40");


						/*if (prev != current / finalSize * 100) {
                         prev = current / finalSize * 100;
                         publishProgress(mi.getAppName(), "Compiling... " + ((int) prev) + "%", "40");
                         pd.setProgress(((int) prev));
                         pd.setMax(100);
                         pd.setSecondaryProgress(100);

                         }*/
					} 
					fos.close();
				}
				ze = zis.getNextEntry();
			}
			new File(zipFile).delete();
			//Utils.deleteFolder(new File(xpath + File.separator + "gen" + File.separator + "META-INF"));
			addLog("Extracting apk complete");
			return true;
		} catch (Exception ex) {
			addLog(ex.getMessage());
			return false;
		}
	}

	boolean manifestchange(String pkg) {
		try {
            addTestApp();
            if (!sign(xpath + "/gen/test.mz", xpath + "/gen/test.app")) {
                return false;
			}
            JSONObject obj = new JSONObject();
            String[] tag = {"Name", "Package", "VName"};
            String[] value = {mi.getAppName(), mi.getPackage(), mi.getVName()};
            for (int i = 0; i < tag.length; i++) {
                obj.put(tag[i], value[i]);
            }
            obj.put("SN", new SignCheck(context, xpath + "/gen/test.app").getCurrentSignature());
            obj.put("VCode", mi.getVCode());
            obj.put("AntiMod", project.getAntiMod());
            obj.put("HookChecker", project.getHookDetection());
            obj.put("SChecker", project.getSignCheck());
            obj.put("Welcome", project.getWelcome());
            obj.put("WelcomeMode", project.getWelcomeMode());
            obj.put("WelcomeMsg", project.getWelcomeMsg());
            obj.put("DeviceLock", project.getDeviceLock());
            //obj.put("DeviceID", project.getDeviceIds());
            obj.put("CrashCatcher", project.getCrashCatch());
            obj.put("InstallerChecker", sp.getBoolean("PlayCheck", false));
            obj.put("EmuChecker", sp.getBoolean("EmuCheck", false));
            obj.put("RootChecker", sp.getBoolean("RootCheck", false));
            if (project.getHookDetection()) {
                JSONArray objarr = new JSONArray();
                String[] hooks = project.getHookedApps().split("\n");
                for (int i = 0; i < hooks.length; i++) {
                    objarr.put(hooks[i]);
                }
                obj.put("Hooks", objarr);
            }
			if (project.getDeviceLock()) {
                JSONArray objarr = new JSONArray();
                String[] hooks = project.getDeviceIds().split("\n");
                for (int i = 0; i < hooks.length; i++) {
                    objarr.put(hooks[i]);
                }
                obj.put("DeviceID", objarr);
            }
            //addLog(obj.toString());
            File c = new File(xpath + "/gen/AndroidManifest.xml");
			c.renameTo(new File(xpath + "/gen/AndroidManifest2.xml"));
			byte[] data = FileUtils.readFileToByteArray(new File(xpath + "/gen/AndroidManifest2.xml"));
            String app_class = dsp.getString("user_dexclass", "");
            if (app_class.isEmpty()) {
                app_class = "xyz.dexpro.engine.a";
                //app_class = mi.getPackage() + ".iApplication";
            }
            ManifestModify mm = new ManifestModify(context, pkg, app_class, obj.toString(), project.getSplash());
			FileUtils.writeByteArrayToFile(new File(xpath + "/gen/AndroidManifest.xml"), mm.modifyAxml(data));
			//if(sp.getBoolean("ChangePackage
            //PackageNameChange.change(new File(xpath + "/gen/AndroidManifest.xml"), "com.test.zed");
            c = new File(xpath + "/gen/AndroidManifest2.xml");
			c.delete();
            new File(xpath + "/gen/test.mz").delete();
			return true;
		} catch (Exception e) {
			addLog(e.getMessage());
			return false;
		}
	}

	protected boolean addProtect() {
		try {
			FileOutputStream dexpro = new FileOutputStream(xpath + "/gen/dexpro-build.properties");
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            DexProMod dpm = new DexProMod(new StringBuffer());
			dpm.setHeaderMsg("#The top-notch android application shielding solution\n#Cutting-edge obfuscator and protector for android applications and libraries");
            dpm.addBuildMessage("version_name=" + mi.getVName());
            dpm.addBuildMessage("version_code=" + mi.getVCode());
            dpm.addBuildMessage("package_name=" + mi.getPackage());
            dpm.addBuildMessage("app_name=" + mi.getAppName());
            dpm.setFooterMsg("#Made with love by MagicPh </>");
			dexpro.write(dpm.output().getBytes());

			return true;
		} catch (Exception e) {
			addLog(e.getMessage());
			return false;
		}
	}

	/*void enProtect(String from, String to) {
     try {
     FileInputStream is = new FileInputStream(from);
     FileOutputStream os = new FileOutputStream(to);
     java.util.zip.DeflaterInputStream isx = new java.util.zip.DeflaterInputStream(is);
     ByteArrayOutputStream byt = new ByteArrayOutputStream();
     byte[] arrby = new byte[2048];
     do {
     int n;
     if ((n = isx.read(arrby)) == -1) {
     os.write(byt.toByteArray());
     os.flush();
     os.close();
     }
     byt.write(arrby, 0, n);
     } while (true);
     } catch (Exception e) {
     //Toast.makeText(this, e.getMessage(), 0).show();
     }
     }*/



	/*public final String md5(String str) {
     try {
     MessageDigest instance = MessageDigest.getInstance("MD5");
     instance.update(str.getBytes());
     byte[] digest = instance.digest();
     StringBuilder stringBuilder = new StringBuilder();
     for (byte b : digest) {
     String toHexString = Integer.toHexString(b & 255);
     while (toHexString.length() < 2) {
     toHexString = new StringBuffer().append("0").append(toHexString).toString();
     }
     stringBuilder.append(toHexString);
     }
     return stringBuilder.toString();
     } catch (NoSuchAlgorithmException e) {
     return null;
     }
     }

     public String getHWID() {
     return md5(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(Build.SERIAL).append(Build.BOARD.length() % 5).toString()).append(Build.BRAND.length() % 5).toString()).append(Build.DEVICE.length() % 5).toString()).append(Build.MANUFACTURER.length() % 5).toString()).append(Build.MODEL.length() % 5).toString()).append(Build.PRODUCT.length() % 5).toString()).append(Build.HARDWARE).toString()).toUpperCase(Locale.getDefault());
     }*/

	protected boolean sign(String inputFile, String outputFile) {
		try {
            if (dsp.getBoolean("use_user_keystore", false)) {
                if (dsp.getString("user_keystore", "").isEmpty()) {
                    def_keystore(inputFile, outputFile);
                } else if (dsp.getString("user_keystore", "").isEmpty()) {
                    def_keystore(inputFile, outputFile);
                } else {
                    try {
                        user_keystore(inputFile, outputFile);
                    } catch (Exception e) {
                        def_keystore(inputFile, outputFile);
                    }
                }
            } else {
                def_keystore(inputFile, outputFile);
            }
			Utils.deleteFolderContent(new File(xpath + "/output"));
			return true;
		} catch (Exception e) {
			addLog(e.getMessage());
			return false;
		}
	}

    void user_keystore(String in, String out) throws Exception {
		String keyPath = dsp.getString("user_keystore", "");
		String keyAlias = dsp.getString("user_keystore_alias", "");
		String keyPassword = dsp.getString("user_keystore_pswd_default", "");


		String alias=keyAlias;
		char[] keyPass=keyPassword.toCharArray();
		char[] storePass=keyPassword.toCharArray();
		PrivateKey privateKey;
		X509Certificate certificate;

		InputStream i = new FileInputStream(keyPath);
		JksKeyStore keyStore=new JksKeyStore();
		keyStore.load(i, storePass);
		if (alias.isEmpty())
			alias = keyStore.aliases().nextElement();
		PrivateKey prk = (PrivateKey) keyStore.getKey(alias, keyPass);
		X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);

		privateKey = prk;
		certificate = cert;

		ApkSigner.SignerConfig.Builder builder = new ApkSigner.SignerConfig.Builder("CERT", privateKey, ImmutableList.of(certificate));
		ApkSigner.SignerConfig signerConfig = builder.build();
		ApkSigner.Builder builder1 = new ApkSigner.Builder(ImmutableList.of(signerConfig));
		builder1.setInputApk(new File(in));
		builder1.setOutputApk(new File(out));
		builder1.setCreatedBy("DexProtector - TrunksKu");
		builder1.setMinSdkVersion(1);
		addLog("V1 Signing Enabled");
		builder1.setV1SigningEnabled(true);
		addLog("v2 signing enabled");
		builder1.setV2SigningEnabled(true);
		ApkSigner signer = builder1.build();
		signer.sign();
    }

    void def_keystore(String in, String out) throws Exception {
        InputStream cert = context.getAssets().open("key/testkey.x509.pem");
        X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance("X.509").
            generateCertificate(cert);
        cert.close();
        InputStream key = context.getAssets().open("key/testkey.pk8");
        PKCS8Key pkcs8 = new PKCS8Key();
        pkcs8.decode(key);
        PKCS8Key privateKey = pkcs8;
        key.close();

        ApkSigner.SignerConfig.Builder builder = new ApkSigner.SignerConfig.Builder("CERT", privateKey, ImmutableList.of(certificate));
        ApkSigner.SignerConfig signerConfig = builder.build();
        ApkSigner.Builder builder1 = new ApkSigner.Builder(ImmutableList.of(signerConfig));
        builder1.setInputApk(new File(in));
        builder1.setOutputApk(new File(out));
        builder1.setCreatedBy("DexProtector - TrunksKu");
        builder1.setMinSdkVersion(1);
        builder1.setV1SigningEnabled(true);
        builder1.setV2SigningEnabled(true);
        ApkSigner signer = builder1.build();
        // System.out.println("Signer -> " + in.getAbsolutePath());
        signer.sign();
        /*ZipSigner zipSigner = new ZipSigner();

         zipSigner.setKeymode(ZipSigner.MODE_AUTO_TESTKEY);
         zipSigner.signZip(in, out);*/

    }

	private boolean enDex(String apk) {
		try {

            if (dsp.getBoolean("encrypt_all_dex", false)) {
                File directory = new File(xpath + "/gen");
                File[] files = directory.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getName().endsWith(".dex")) {
                        addLog("Encrypting: " + files[i].getName());
                        okEnDex(files[i].getName());
                        new File(new StringBuffer().append(xpath).append("/gen/" + files[i].getName()).toString()).delete();
                    }
                }
            } else {
                if (project.getClassFilter()) { 
                    opt_dex("classes.dex");
                    new File(new StringBuffer().append(xpath).append("/gen/classes.dex").toString()).delete();
                    encDex();
                    File directory2 = new File(xpath + "/gen/assets/open-dex");
                    File[] files2 = directory2.listFiles();
                    for (int i = 0; i < files2.length; i++) {
                        if (new File(files2[i].getAbsolutePath()).exists()) {
                            if (files2[i].getName().endsWith(".dex")) {
                                move_dex(files2[i].getName());
                                new File(new StringBuffer().append(xpath).append("/gen/assets/open-dex/" + files2[i].getName()).toString()).delete();
                            }
                        }
                    }
                } else {
                    addLog("Encrypting: classes.dex");
                    okEnDex("classes.dex");
                    new File(new StringBuffer().append(xpath).append("/gen/" + "classes.dex").toString()).delete();

                }
            }

			FileOutputStream dexpro = new FileOutputStream(xpath + "/gen/assets/classes.dex.dat");
            dexpro.write("Secured by dexprotector of magicph :)".getBytes());
            addLog("Encrypting dex file complete");
			return true;
		} catch (Exception e) {
			addLog("Error: " + e.getMessage());
			return false;
		}
	}

    void encDex() throws Exception {
        File directory = new File(xpath + "/gen/assets/sec-dex");
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith(".dex")) {
                addLog("Encrypting: " + files[i].getName());
                okEnDex(files[i].getName());
                new File(new StringBuffer().append(xpath).append("/gen/assets/sec-dex/" + files[i].getName()).toString()).delete();
            }
        }
    }

	void okEnDex(String name)throws Exception {

        File folder = new File(xpath + "/gen/dp");
        if (!folder.exists()) {
            folder.mkdir();
        }
        FileOutputStream os = null;
        FileInputStream is = null; 
        String in = null;
        if (dsp.getBoolean("encrypt_all_dex", false)) {
            in = new StringBuffer().append(xpath).append("/gen/").append(name).toString();
        } else {
            if (project.getClassFilter()) {
                in = new StringBuffer().append(xpath).append("/gen/assets/sec-dex/").append(name).toString();
            } else {
                in = new StringBuffer().append(xpath).append("/gen/").append(name).toString();
            }
        }
        if (project.getStrDexGuard()) {
            publishProgress(mi.getAppName(), "DEX - String Securing...");

            try {
                new DexOptimizer().EncryptString(in, in, new DexStringEncryptor.EncryptCallBack(){

                        @Override
                        public void onProgress(int progress, int total) {
                            pd.setProgress(progress);
                            pd.setMax(total);
                            //DebugLog.i("", progress + "/" + total);
                        }

                        @Override
                        public void onClassDefName(String Name) {
                        }
                    });
            } catch (Exception e) {

            }
        }
        String n = DexOptimizer.createName("classes");
        String e = DexOptimizer.createName("dex");

        is = new FileInputStream(in);
        os = new FileOutputStream(xpath + "/gen/dp/" + n/*name.replace("classes", DexOptimizer.createName("classes")).replace("dex", "lua")*/ + "." + e);
        dexName.add(n + "." + e);
        publishProgress(mi.getAppName(), "DEX - Securing...");

        exfr(new File(in), os);

	}

    private void opt_dex(String name) {
        publishProgress(mi.getAppName(), "DEX - Optimizing...");

        DexFile dexFile = null;
        try {
            File folder = new File(xpath + "/gen/assets/sec-dex");
            if (!folder.exists()) {
                folder.mkdir();
            }
            File folder2 = new File(xpath + "/gen/assets/open-dex");
            if (!folder2.exists()) {
                folder2.mkdir();
            }
            loadIgnoredLibs();
            dexFile = DexFileFactory.loadDexFile(new File(xpath + "/gen/" + name).getAbsolutePath(), Opcodes.getDefault());
        } catch (Exception e) {
            addLog(e.getMessage());
            // broadcastStatus("exit");
            // UIHandler.post(new ToastRunnable("The app you selected cannot be decompiled. Please select another app."));
        }
        List<ClassDef> classes = new ArrayList<>();
        List<ClassDef> classes2 = new ArrayList<>();
        // broadcastStatus("optimising", "");

        for (ClassDef classDef : dexFile.getClasses()) {
            
			///addLog(classDef.toString());
            if (project.getIgnored()) {
				if (isIgnored(classDef.getType())) {
					classes2.add(classDef);
				} else {
					classes.add(classDef);
				}
			} else {
				if (isIgnored(classDef.getType())) {
					classes.add(classDef);
				} else {
					classes2.add(classDef);
				}
			}


        }


        try {
            //Log.d("DEBUGGER", "Start Writing");
            if (classes.size() != 0) {
                addLog("Encrypting " + classes.size() + "classes");
                DexFileFactory.writeDexFile(xpath + "/gen/assets/sec-dex/" + name, new ImmutableDexFile(Opcodes.getDefault(), classes));
            } else {
                addLog("Encrypt Fail");
            }
            if (classes2.size() != 0) {
                addLog("Ignoring " + classes2.size() + "classes");
                DexFileFactory.writeDexFile(xpath + "/gen/assets/open-dex/" + name, new ImmutableDexFile(Opcodes.getDefault(), classes2));
            }
            addLog("Writing done!");
        } catch (Exception e) {
            addLog(e.getMessage());
            // broadcastStatus("exit");
            // UIHandler.post(new ToastRunnable("The app you selected cannot be decompiled. Please select another app."));
        }



    }

    void move_dex(String name) throws Exception {
        InputStream inputStream = new FileInputStream(new File(xpath + "/gen/assets/open-dex/" + name));
        FileOutputStream outputStream = null;
        if (name.equals("classes.dex")) {
            outputStream = new FileOutputStream(xpath + "/gen/classes-op.dex");
        } else {
            outputStream = new FileOutputStream(xpath + "/gen/" + name);
        }
        c(inputStream, outputStream);
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }



    void rename_app_class() {
        try {
            DexBackedDexFile dexFile = DexFileFactory.loadDexFile(xpath + "/gen/classes.dex", Opcodes.getDefault());
            DexRewriter rewriter = new DexRewriter(new RewriterModule() {
                    public Rewriter<String> getTypeRewriter(Rewriters rewriters) {
                        return new Rewriter<String>() {
                            public String rewrite(String value) {
                                if (value.equals("Lxyz/dexpro/engine/a;")) {
                                    return "L" + dsp.getString("user_dexclass", "xyz.dexpro.engine.a").replace(".", "/") + ";";
                                }
                                return value;
                            }
                        };
                    }
                });
            DexFile rewrittenDexFile = rewriter.rewriteDexFile(dexFile);
            DexPool.writeTo(xpath + "/gen/classes.dex", rewrittenDexFile);
        } catch (Exception e) {

        }
    }

    private boolean rename_class(String path, final String old) {
        try {
            DexFile dexFile = DexFileFactory.loadDexFile(path, Opcodes.getDefault());
            DexRewriter rewriter = new DexRewriter(new RewriterModule() {
                    public Rewriter<String> getTypeRewriter(Rewriters rewriters) {
                        return new Rewriter<String>() {

                            public String rewrite(String value) {
                                if (dsp.getBoolean("encode_class", false)) {
                                    if (old.endsWith(";")) {
                                        if (value.equals("L" + old.replace(".", "/"))) {
                                            try {
                                                String[] bits = value.replace(".", "/").replace("L", "").replace(";", "").split("/");
                                                String lastOne = bits[bits.length - 1];

                                                return "Lcom/xyx/" + createName(lastOne) + ";";
                                            } catch (Exception e) {
                                                return value;
                                            }
                                        }
                                    } else {
                                        if (value.contains("L" + old.replace(".", "/"))) {
                                            try {
                                                String[] bits = value.replace(".", "/").replace("L", "").replace(";", "").split("/");
                                                String lastOne = bits[bits.length - 1];
                                                return "Lcom/xyz/" + createName(lastOne) + ";";
                                            } catch (Exception e) {
                                                return value;
                                            }
                                        }
                                    }
                                }

                                return value;
                            }
                        };
                    }
                });
            DexFile rewrittenDexFile = rewriter.rewriteDexFile(dexFile);
            DexPool.writeTo(path, rewrittenDexFile);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean encode_class() {

        String[] name = /*dsp.getString("user_encode_classes", "").split("\n")*/{"android.support.dexpro.Application", "android.support.coreutils.CoreUtils", "android.support.coreutils.AppUtils"};
        for (int i = 0; i < name.length; i++) {
            rename_class(xpath + "/gen/classes.dex", name[i]);
        }
        return true;
    }



    private static char chara = 'a';

    private Map<String, String> mUsingName = new HashMap<String, String>(16);

    public String createName(String type) {
        String name = mUsingName.get(type);
        if (name == null) {
            name = String.valueOf(chara);
            mUsingName.put(type, name);
            return name;
        } else {
            char[] chars = name.toCharArray();
            StringBuilder stringBuilder = new StringBuilder(chars.length);
            if (add(chars, chars.length - 1)) {
                stringBuilder.append(chara);
            }
            stringBuilder.append(chars);
            mUsingName.put(type, stringBuilder.toString());
            return stringBuilder.toString();
        }
    }

    private boolean add(char[] c, int index) {
        if (c[index] < 'z') {
            c[index]++;
            return false;
        } else {
            c[index] = chara;
            if (index > 0) {
                return add(c , index - 1);
            } else {
                return true;
            }
        }
    }

    public boolean addMyDex() {
        try {
            new File(xpath + "/gen/test.app").delete();
            InputStream inputStream = null;
            if (dsp.getString("user_dexprotectjar", "").isEmpty()) {
                inputStream = new FileInputStream(context.getFilesDir().getAbsolutePath() + "/dexpro.jar");
            } else {
                File file = new File(dsp.getString("user_dexprotectjar", ""));
                if (file.exists()) {
                    inputStream = new FileInputStream(new File(dsp.getString("user_dexprotectjar", "")));
                } else {
                    inputStream = new FileInputStream(context.getFilesDir().getAbsolutePath() + "/dexpro.jar");
                }
            }
			addLog("Core Version: 1.0");
			DexOptimizer dexop = new DexOptimizer();
			addLog("Encrypting Core String");
			//dexop.EncryptString(inputStream, new FileOutputStream(xpath + "/gen/classesEN.dex"));
            FileOutputStream outputStream = new FileOutputStream(xpath + "/gen/classes.dex");
            c(inputStream, outputStream);

            //c(new FileInputStream(xpath + "/gen/classesEN.dex"), outputStream);
            inputStream.close();
            outputStream.flush();
            outputStream.close();
			//new File(xpath + "/gen/classesEN.dex").delete();
            publishProgress(mi.getAppName(), "DEX - Renaming class...");
            //DexOptimizer dexop = new DexOptimizer();
			addLog("Rename class");
            ArrayList<ReplaceItem> item = new ArrayList();
            String pass = MyApp.getDefSharedPreferences().getString("DexKey", "dexprotect");
            if (pass.isEmpty()) {
                pass = "dexprotect";
            }
            item.add(new ReplaceItem("#dexpro.Dexes", String.join(", ", dexName)));
            System.out.println(String.join(", ", dexName));

            item.add(new ReplaceItem("#dexpro.Suffix", ManifestModify.a(".lua.mph")));

            item.add(new ReplaceItem("#dexpro.Supprt", ManifestModify.a(Utils.sealing(pass))));

            String app_class = dsp.getString("user_dexclass", "");
            if (app_class.isEmpty()) {
                app_class = "xyz.dexpro.engine.a";
                //app_class = mi.getPackage() + ".iApplication";
            }
            item.add(new ReplaceItem("xyz.dexpro.engine.a".replace(".", "/"), app_class.replace(".", "/")));

            dexop.replace(xpath + "/gen/classes.dex", item);


            dexop.EncryptString(xpath + "/gen/classes.dex", xpath + "/gen/classes.dex", new DexStringEncryptor.EncryptCallBack(){

                    @Override
                    public void onProgress(int progress, int total) {
                        pd.setProgress(progress);
                        pd.setMax(total);
                        //DebugLog.i("", progress + "/" + total);
                    }

                    @Override
                    public void onClassDefName(String Name) {
                    }
                });
            if (project.getObfuscate()) {
                dexop.obfuscate(xpath + "/gen/classes.dex");
            }
			/*ProguardDex proguard = new ProguardDex(context);
			proguard.start(new File(xpath + "/gen/classes.dex"));*/
            publishProgress(mi.getAppName(), "DEX - Merging...");

            if (!dsp.getBoolean("encrypt_all_dex", false)) {
                if (project.getClassFilter()) {
                    //publishProgress(mi.getAppName(), "DEX - Merging...");
                    dexop.optimize(xpath + "/gen", xpath + "/gen/classes.dex", xpath + "/gen/classes-op.dex");

                }
            }
            if (dsp.getBoolean("fix_multi_dex", false)) {
                try {
                    dexop.merge_multidex(xpath + "/gen");
                } catch (Exception e) {
                    addLog(e.getMessage());
                }
            }
            return true;
        } catch (Exception e) {
            addLog(e.getMessage());
            return false;
        }
	}

	public boolean addTestApp() {
		try {
			InputStream inputStream = context.getResources().openRawResource(R.raw.app);
			FileOutputStream outputStream = new FileOutputStream(xpath + "/gen/test.mz");
			c(inputStream, outputStream);
			inputStream.close();
			outputStream.flush();
			outputStream.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

    

    private static int[] FxIjsF(int[] iArr) {
        int[] iArr2 = new int[27];
        int i = iArr[0];
        iArr2[0] = i;
        int[] iArr3 = new int[]{iArr[1], iArr[2], iArr[3]};
        for (int i2 = 0; i2 < 26; i2++) {
            iArr3[i2 % 3] = (((iArr3[i2 % 3] >>> 8) | (iArr3[i2 % 3] << 24)) + i) ^ i2;
            i = ((i << 3) | (i >>> 29)) ^ iArr3[i2 % 3];
            iArr2[i2 + 1] = i;
        }
        return iArr2;
    }

    private void exfr(File input, OutputStream outputStream) throws Exception {

        InputStream inputStream = new DeflaterInputStream(new FileInputStream(input));
        String pass = dsp.getString("dexpro_key", "dexprotect");
        if (pass.isEmpty()) {
            pass = "dexprotect";
        }
        char[] toCharArray = Utils.sealing(pass).toCharArray();
        int[] iArr = new int[4];
        int i = 0 + 1;
        int i2 = i + 1;
        iArr[0] = toCharArray[0] | (toCharArray[i] << 16);
        i = i2 + 1;
        char c = toCharArray[i2];
        i2 = i + 1;
        iArr[1] = c | (toCharArray[i] << 16);
        i = i2 + 1;
        c = toCharArray[i2];
        i2 = i + 1;
        iArr[2] = c | (toCharArray[i] << 16);
        i = i2 + 1;
        c = toCharArray[i2];
        i2 = i + 1;
        iArr[3] = c | (toCharArray[i] << 16);
        int[] iArr2 = new int[2];
        i = i2 + 1;
        c = toCharArray[i2];
        i2 = i + 1;
        iArr2[0] = c | (toCharArray[i] << 16);
        iArr2[1] = toCharArray[i2] | (toCharArray[i2 + 1] << 16);
        iArr = FxIjsF(iArr);
        byte[] bArr = new byte[2048];
        int i3 = 0;
        long totalRead = 0;
        long totalSize = input.length();
        long lastProgressUpdateTime = 0;

        while (true) {
            int read = inputStream.read(bArr);
            totalRead += read;
            int perc = (int) ((totalRead / (float) totalSize) * 100);
            /*int perc = (int) (totalRead * 100 / totalSize);
            if (perc > 100) {
                perc = 100;
            }*/
            if (read >= 0) {
                int i4 = i3 + read;
                int i5 = 0;
                while (i3 < i4) {
                    int i6 = i3 % 8;
                    int i7 = i6 / 4;
                    int i8 = i3 % 4;
                    if (i6 == 0) {
                        nDnv(iArr, iArr2);
                    }
                    bArr[i5] = (byte) (((byte) (iArr2[i7] >> (i8 * 8))) ^ bArr[i5]);
                    i3++;
                    i5++;
                }
                outputStream.write(bArr, 0, read);
                /*long now = System.currentTimeMillis();
                 if (lastProgressUpdateTime == 0 || lastProgressUpdateTime < now - 100) {
                 lastProgressUpdateTime = now;

                 Log.e("", totalRead + " " + " " + percentage);
                 */ pd.setProgress(perc);
                /*if (listener != null)
                 this.listener.onUpdateProgress(percentage, totalRead);*/
                //}
            } else {
                return;
            }
        }
    }

    private static void nDnv(int[] iArr, int[] iArr2) {
        int i = iArr2[0];
        int i2 = iArr2[1];
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[0];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[1];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[2];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[3];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[4];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[5];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[6];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[7];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[8];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[9];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[10];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[11];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[12];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[13];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[14];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[15];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[16];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[17];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[18];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[19];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[20];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[21];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[22];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[23];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[24];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[25];
        i = ((i << 3) | (i >>> 29)) ^ i2;
        i2 = (((i2 >>> 8) | (i2 << 24)) + i) ^ iArr[26];
        iArr2[0] = ((i << 3) | (i >>> 29)) ^ i2;
        iArr2[1] = i2;
    }

	public static void c(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[2048];
        while (true) {
            int read = inputStream.read(bArr);
            int i = read;
            if (read != -1) {
                outputStream.write(bArr, 0, i);
            } else {
                return;
            }
        }
    }

    public String ignored_libs() {
        try {

            InputStream open = context.getResources().openRawResource(R.raw.ignored_class);
            ByteArrayOutputStream xf = new ByteArrayOutputStream();
            byte[] bArr = new byte[5242880];
            while (true) {
                int read = open.read(bArr);
                if (read == -1) {
                    break;
                }
                xf.write(bArr, 0, read);
            }
            return new String(xf.toByteArray()) ;
        } catch (Exception e) {
            return null;
        }
    }

    private void loadIgnoredLibs() {
        String[] ignored = project.getIgnoredClass().split(",");
        for (int i = 0; i < ignored.length; i++) {
            ignoredLibs.add(StringUtils.toClassName(ignored[i]));
        }
    }

    private boolean isIgnored(String className) {
        for (String ignoredClass : ignoredLibs) {
            if (className.startsWith(ignoredClass)) {
                return true;
            }
        }
        return false;
    }

    public static class SignProgress implements ProgressListener {

        @Override
        public void onProgress(ProgressEvent event) {

        }

    }

    class Params {

        String storePath;
        String storePass;
        String keyName;
        String keyAlgorithm;
        int keySize;
        String keyPass;
        int certValidityYears;
        String certSignatureAlgorithm;
        DistinguishedNameValues distinguishedNameValues = new DistinguishedNameValues();


    }
}
