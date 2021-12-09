package mph.trunksku.apps.dexpro.utils;
import android.content.*;
import android.content.pm.*;
import android.graphics.drawable.*;
import android.util.Base64;
import java.security.*;

import android.content.pm.Signature;

public class MyAppInfo
{

	private static PackageManager pm;
	private static PackageInfo pi;
	
	public MyAppInfo(Context c, String apkpath){
		pm = c.getPackageManager();
	    pi = pm.getPackageArchiveInfo(apkpath, 0);
		pi.applicationInfo.sourceDir       = apkpath;
		pi.applicationInfo.publicSourceDir = apkpath;

		//Drawable APKicon = pi.applicationInfo.loadIcon(pm);
		//String   AppName = (String)pi.applicationInfo.loadLabel(pm);
	}
	
	public static Drawable getIcon(){
		return pi.applicationInfo.loadIcon(pm);
	}
	
	public static String getAppName(){
		return (String)pi.applicationInfo.loadLabel(pm);
	}
	
	public static String getPackage(){
		return pi.applicationInfo.packageName;
	}
	
	public static String getVName(){
		return pi.versionName;
	}
	
	public static int getVCode(){
		return pi.versionCode;
	}
	
	public static String getSignature(){
		String res = "";
        try {
            PackageInfo packageInfo = pm.getPackageInfo(
				pi.packageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : packageInfo.signatures) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                res = Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT);
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException ignored) {
        }
        return res.trim();
	}
	
	public boolean isDebug() {
        return ((pi.applicationInfo.flags & pi.applicationInfo.FLAG_DEBUGGABLE) != 0);
    }
}
