package mph.trunksku.apps.dexpro;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;
import mph.trunksku.apps.dexpro.utils.SecurePreferences;
import mph.trunksku.apps.dexpro.utils.ExceptionHandler;
import android.app.Application;
import java.io.StringWriter;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;
import android.os.Environment;
import java.security.Security;
import sun1.security.provider.JavaProvider;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import android.content.Intent;
import android.app.PendingIntent;
import android.app.AlarmManager;
import android.os.Handler;
import android.os.Looper;
import mph.trunksku.apps.dexpro.utils.AssetsSDK;

public class MyApp extends MultiDexApplication
{

	private static Context context;
	private static SharedPreferences sharedPreferences;
    private static SharedPreferences defsharedPreferences;
	private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    

	@Override
	public void onCreate() {
		super.onCreate();
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(final Thread thread, final Throwable ex) {
                    if(isUIThread()) {
                        invokeLogActivity(thread, ex);
                    }else{  //handle non UI thread throw uncaught exception

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    invokeLogActivity(thread, ex);
                                }


                            });
                    }
				}
            });

        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        context = this;
		sharedPreferences = new SecurePreferences(this);
        defsharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Security.addProvider(new JavaProvider());
        Security.addProvider(new BouncyCastleProvider());
		try {
			AssetsSDK.install(this);
		} catch (Exception e) {}
	}
	
	public boolean isUIThread(){
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    private String getStackTrace(Throwable th){
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        Throwable cause = th;
        while(cause != null){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        final String stacktraceAsString = result.toString();
        printWriter.close();
        return stacktraceAsString;
    }

    private void invokeLogActivity(Thread thread, Throwable ex) {
        Intent intent = new Intent(getApplicationContext(), ExceptionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("error", getStackTrace(ex));
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 11111, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, pendingIntent);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(2);
        uncaughtExceptionHandler.uncaughtException(thread, ex);

    }
	
    public static SharedPreferences getDefSharedPreferences() {
        return defsharedPreferences;
	}
    
	public static SharedPreferences getSharedPreferences() {
		return sharedPreferences;
	}
	
	
    
    public static String getVersion() {
        try{
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            /*Toast.makeText(this,
                           "PackageName = " + info.packageName + "\nVersionCode = "
                           + info.versionCode + "\nVersionName = "
                           + info.versionName + "\nPermissions = " */
            return info.versionName;
        }catch(Exception e){
            return "1.0";
        }
    }
    
    public static String getVersionCode() {
        try{
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            /*Toast.makeText(this,
             "PackageName = " + info.packageName + "\nVersionCode = "
             + info.versionCode + "\nVersionName = "
             + info.versionName + "\nPermissions = " */
            return Integer.toString(info.versionCode);
        }catch(Exception e){
            return "1";
        }
    }
    
    public static boolean isNetworkAvailable()
    {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
    
    public static Context app(){
        return context;
    }
    
}
