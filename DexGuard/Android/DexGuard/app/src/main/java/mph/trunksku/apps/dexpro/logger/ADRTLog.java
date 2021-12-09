package mph.trunksku.apps.dexpro.logger;

import android.util.Log;

public class ADRTLog {

    protected static StringBuffer log = new StringBuffer();
    protected static final int MAX_LENGTH = 20480; // 20k text

    public static void d(String tag, String txt) {
        //Log.d(tag, txt);
        append(txt + "\n");
    }

    public static void e(String tag, String txt) {
        //Log.e(tag, txt);
        append(txt + "\n");
    }

    public static void e(String tag, String txt, Exception e) {
        //Log.e(tag, txt,e);
        append(txt + "\nException - " + e.toString() + "\n");
    }

    public static void i(String tag, String txt) {
        //Log.i(tag, txt);

        System.out.println(txt);
        append( txt + "\n");
    }

    public static synchronized void append(String s) {
        if (log.length() + s.length() > MAX_LENGTH) {
            log.delete(0, log.length() + s.length() - MAX_LENGTH);
        }
        log.append(s, Math.max(0, s.length() - MAX_LENGTH), s.length());
    }

    public static String dump() {
        return log.toString();
    }
    
	public static void clear(){
		log.delete(0, log.length());
	}
}

