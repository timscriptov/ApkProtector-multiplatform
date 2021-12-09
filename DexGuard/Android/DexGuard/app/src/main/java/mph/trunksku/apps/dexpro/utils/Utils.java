package mph.trunksku.apps.dexpro.utils;
import java.io.*;
import android.app.Notification;
import android.graphics.drawable.Icon;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Application;
import android.os.Build;
import android.content.Context;
import android.graphics.BitmapFactory;
import mph.trunksku.apps.dexpro.logger.Log;
import java.lang.reflect.Method;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.provider.Settings;

public class Utils
{
    private static final String NOTIFICATION_CHANNEL_ID = "AppCloner";
    private static final String NOTIFICATION_CHANNEL_ID_HIGH_IMPORTANCE = "AppClonerHighImportance";
    private static final String NOTIFICATION_CHANNEL_NAME = "App Cloner";
    private static final String NOTIFICATION_CHANNEL_NAME_HIGH_IMPORTANCE = "App Cloner High Importance";
    private static Application sApplication;
    private static boolean sNotificationChannelCreated;
    private static boolean sNotificationChannelCreatedHighImportance;
    private static Icon sNotificationIcon;
    
    public static String sealing(String input) {
        int i2;
        byte[] bArr = new byte[]{(byte) 3, (byte) 5, (byte) 9, (byte) 1};
        int length = bArr.length;
        byte[] bytes = input.getBytes();
        for (i2 = 0; i2 < bytes.length; i2 += 1) {
            bytes[i2] = (byte) (bytes[i2] ^ bArr[i2 % length]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        for (i2 = 0; i2 < bytes.length; i2 += 1) {
            stringBuilder.append(cArr[(bytes[i2] >> 4) & 15]);
            stringBuilder.append(cArr[bytes[i2] & 15]);
        }
        return stringBuilder.toString();
    }
    
    public static String getBuildSerial()
    {
        String buildSerial = Build.SERIAL;
        if (!"unknown".equals(buildSerial))
        {
            return buildSerial;
        }
        try
        {
            return Settings.Secure.getString(getApplication().getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        catch (Exception e)
        {
            //Log.w(TAG, e);
            return buildSerial;
        }
    }
    
	public static boolean sourceExists(File sourceDir) {
        if (sourceDir.exists() && sourceDir.isDirectory()) {
            File infoFile = new File(sourceDir.getAbsolutePath());
            if (infoFile.exists()) {
                SourceInfo sourceInfo = SourceInfo.getSourceInfo(infoFile);
                if (sourceInfo != null) {
                    return true;
                }
            }
        }
        return false;
    }
	
    /*public static boolean isNetworkAvailable(Context context)
    {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}*/

    public static SourceInfo getSourceInfoFromSourcePath(File sourceDir) {
        if (sourceDir.isDirectory()) {
            File infoFile = new File(sourceDir.getAbsolutePath());
            if (infoFile.exists()) {
                return SourceInfo.getSourceInfo(infoFile);
            }
        }
        return null;
    }
	
	public static void deleteFolder(File folder)
	{
		File[] files = folder.listFiles();
		if (files != null)
		{ //some JVMs return null for empty dirs
			for (File f: files)
			{
				if (f.isDirectory())
				{
					deleteFolder(f);
				}
				else
				{
					f.delete();
				}
			}
		}
		folder.delete();
	}

	public static void deleteFolderContent(File folder)
	{
		File[] files = folder.listFiles();
		if (files != null)
		{ //some JVMs return null for empty dirs
			for (File f: files)
			{
				if (f.isDirectory())
				{
					deleteFolder(f);
				}
				else
				{
					f.delete();
				}
			}
		}
	}
    
    public static Application getApplication() {
        Application application = sApplication;
        if (application != null) {
            return application;
        }
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            if (Build.VERSION.SDK_INT >= 9) {
                sApplication = (Application) activityThreadClass.getMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
                return sApplication;
            }
            for (Method method : activityThreadClass.getMethods()) {
                if ("currentActivityThread".equals(method.getName())) {
                    Object currentActivityThread = method.invoke(null, new Object[0]);
                    for (Method m2 : activityThreadClass.getMethods()) {
                        if ("getApplication".equals(m2.getName())) {
                            sApplication = (Application) m2.invoke(currentActivityThread, new Object[0]);
                            return sApplication;
                        }
                    }
                    continue;
                }
            }
            return null;
        } catch (Exception e) {
            //Log.w(TAG, e);
            return null;
        }
    }
    
    public static void setSmallNotificationIcon(Notification.Builder b) {
        setSmallNotificationIcon(b, false);
    }

    public static void setSmallNotificationIcon(Notification.Builder b, boolean highImportance) {
        if (Build.VERSION.SDK_INT >= 23) {
           /* Icon icon = getNotificationIcon();
            if (icon != null) {
                b.setSmallIcon(icon);
            } else {
                b.setSmallIcon(17301569);
            }*/
            b.setColor(-16537100);
        } /*else {
            b.setSmallIcon(17301569);
        }*/
        if (Build.VERSION.SDK_INT >= 26) {
            boolean z;
            String str2;
            NotificationChannel channel;
            Application application;
            NotificationManager notificationManager;
            if (highImportance) {
                try {
                    z = sNotificationChannelCreatedHighImportance;
                    str2 = NOTIFICATION_CHANNEL_ID_HIGH_IMPORTANCE;
                    if (!z) {
                        channel = new NotificationChannel(str2, NOTIFICATION_CHANNEL_NAME_HIGH_IMPORTANCE, 4);
                        application = getApplication();
                        if (application != null) {
                            notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
                            if (notificationManager != null) {
                                notificationManager.createNotificationChannel(channel);
                                sNotificationChannelCreatedHighImportance = true;
                                //Log.i(TAG, "setSmallNotificationIcon; notification channel created: AppClonerHighImportance");
                            }
                        }
                    }
                    b.setChannelId(str2);
                    b.setPriority(1);
                    return;
                } catch (Throwable t) {
                    //Log.w(TAG, t);
                    return;
                }
            }
            z = sNotificationChannelCreated;
            str2 = NOTIFICATION_CHANNEL_ID;
            if (!z) {
                channel = new NotificationChannel(str2, NOTIFICATION_CHANNEL_NAME, 2);
                application = getApplication();
                if (application != null) {
                    notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
                    if (notificationManager != null) {
                        notificationManager.createNotificationChannel(channel);
                        sNotificationChannelCreated = true;
                        //Log.i(TAG, "setSmallNotificationIcon; notification channel created: AppCloner");
                    }
                }
            }
            b.setChannelId(str2);
        } else if (highImportance && Build.VERSION.SDK_INT >= 16) {
            b.setPriority(1);
        }
    }

    private static Icon getNotificationIcon() {
        if (sNotificationIcon == null) {
            try {
                byte[] data = android.util.Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAMAAABiM0N1AAAAw1BMVEUAAADGHBzDKCjFJyfFJyfH KSnHJyfEJibFKSmqAADJKCjHJyfFKCjGKCjGKCjGJyfHJyfFKSnEJyfVKyvGJyfGJyfGKCjFKCjG KCjFJyfEKCjGJyfHKSnIJCS/ICDGKCjGKCjGJyfGKCjFJCTGJyfFJyfGJyfHKCjGJyfFJibFKCjG KSnFJyfHJyfFJyfHJSXEKSnDJibGKCjIKSnCKSnGKCjFKCjFKCjGKCjGKCjFKSnFIyPFJyfGKCjF KSnFJibEKCiqZb2wAAAAQXRSTlMACSZCYWRbPB8DE4OsxciidksNBkicmW27tXpVMhwQhrhwvyOv lqmMfTVzXsJOiSlFL2c4GaWAnz+yWBaQkyxqUkyE99MAAAN9SURBVFjD7ZhZY6IwEIDxqkhlPPAA QUUQT2y13rpt9///qp2IIiQc0e3b7jxpCF8mc2WCIPyXv5JMNpcvoLwUxdLTECn3WpbBF7lSrdUf pyiNZgDiw1qNx1hSWyXvaR3d6GbIQK9bfOmbZEwt8KOkgYVvtHSRfmDnh4+gcmTlfjbyWano4MOR wYGpj3HmxI6fMK3ghJmSxsmWAYbFxCmlOe68aSdzauiqSSZtte4ILWWkcNwFT3C8AbgJeusyqO98 HukkkZYyDMWIaFgZEfnxgaR1NMe2QNvQg5v2lkS41l9HkEwp0u9lcI0oB12lTy/SR99FZfIOQKc5 /WCiqdS261uAD5ZTxDVL7Jqof0ev5fcXUpeKORdkxqbKCNQeNXYgb3e8qDqQvBmzZnJoUIHdmHLE d+e+1QnpQG0dN0eZVTGhQm9sSnQohfR7o6asGZVOACtayTa+GTRBC61EL+bQKmmwZcyPBj4G/+cR nGVdFNLSAGBTDItYK5SIwFhEECogB8vcK6hKFMhJBelhFUzGs7ygswy/ApHF+p4XhOa27vVrDmA/ DvoMnVSOl2am8DhoYwVBK8/RLaZwWRAtFTFEvolnKAuqFKcBsRLQ8td99FJYJdbW+XjQMFA71dug 53QRoMHmMAfI35zrFZgVwPQ5EKmGRAZ+ghhPgjIjMnS8RhJqtE4AmflFMw4kTMnRULsFxP1nBEjF ol8ax4GEAcD+VlzObO4HQB3y/ysWpOzBPygVGdrxoNk1G2NAwvvg/rsczGAadLHkdzxICFTNcbgU UsbGvX3JCaCA4HqbeFAeQ5gTtGLcFgTlvErAA8KsfY0H9W5nLgdoDFopDiQ7KLwaLelDlDdFGA9q sP8REPHb6kdAkhWOyXk8qJzcXM48N/sHlBwLmglpKpnB/tqoTqLlJa3nP6WuFdF2Ro46TB+VJguY RA2fVbDERzhZK8bwRRmOEj/HNkGNudmgzx2Fl3M22QIdjIHmhnNfGkAh8apytHk4a9Tnd0qquLV0 zjfG6yl5ytLFgzzl8itim6pO0xYzsE0/6gmXyHobO54WR6AoVUhAXb4IWCe+7xvZLTmoq1/sGoc3 l/QwNm+MlJaX29BwV7N9xTbrguNeWqH3h/JoOrn2fmoFS/Z+6F6r2of4aGYLvcZMCxUit9k2nv32 IxX16u7TcT53g4WhCP+s/AFLe3OKam3X0wAAAABJRU5ErkJggg== ", 0);
                sNotificationIcon = Icon.createWithBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
            } catch (Exception e) {
                //Log.w(TAG, e);
            }
        }
        return sNotificationIcon;
    }
}
