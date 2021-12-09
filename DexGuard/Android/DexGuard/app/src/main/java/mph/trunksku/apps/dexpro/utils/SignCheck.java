package mph.trunksku.apps.dexpro.utils;

import android.annotation.*;
import android.content.*;
import android.content.pm.*;
import android.util.Base64;
import java.security.*;

import android.content.pm.Signature;

public class SignCheck
{
	private static PackageManager pm;
	private static PackageInfo pi;

	public SignCheck(Context c, String apkpath){
		pm = c.getPackageManager();
	    pi = pm.getPackageArchiveInfo(apkpath, PackageManager.GET_SIGNATURES);
		pi.applicationInfo.sourceDir       = apkpath;
		pi.applicationInfo.publicSourceDir = apkpath;

		//Drawable APKicon = pi.applicationInfo.loadIcon(pm);
		//String   AppName = (String)pi.applicationInfo.loadLabel(pm);
	}
	
	@SuppressLint("PackageManagerGetSignatures")
    public String getCurrentSignature() {
        String res = "";
        try {
            for (Signature signature : pi.signatures) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                res = Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT);
            }
        } catch (NoSuchAlgorithmException ignored) {
        }
        return res.trim();
    }
}
