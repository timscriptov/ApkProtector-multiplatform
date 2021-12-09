package mph.trunksku.apps.dexpro.utils;
import android.app.Activity;
import android.content.SharedPreferences;
import com.mph.dexprotect.R;

public class ThemeManager
{

    private Activity act;

    private SharedPreferences sp;
    
    public ThemeManager(Activity act, SharedPreferences sp){
        this.act = act;
        this.sp = sp;
    }
    
    public void init() {
        if(sp.getBoolean("darkMode", false)){
            act.setTheme(R.style.AppTheme_Black);
        }else{
            act.setTheme(R.style.AppTheme);
        }
    }
}
