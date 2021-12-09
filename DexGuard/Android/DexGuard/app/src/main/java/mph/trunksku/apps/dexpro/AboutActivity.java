package mph.trunksku.apps.dexpro;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;
import com.mph.dexprotect.R;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import mph.trunksku.apps.dexpro.adapter.OpenSourceAdapter;
import mph.trunksku.apps.dexpro.utils.ThemeManager;
import mph.trunksku.apps.dexpro.view.CenteredToolBar;

public class AboutActivity extends AppCompatActivity
{

    private RecyclerView recycleview;

    private OpenSourceAdapter lvA;
    ArrayList<HashMap<String, String>> licenseList = new ArrayList<HashMap<String, String>>();

    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        new ThemeManager(this, MyApp.getDefSharedPreferences()).init();
        setContentView(R.layout.activity_about);
        setupToolbar("About");

        
        /*recycleview = (RecyclerView) findViewById(R.id.cardListView);
        recycleview.setHasFixedSize(true);
        recycleview.setItemAnimator(new DefaultItemAnimator());
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        
       //lvA.notifyDataSetChanged();
        try
        {
            JSONObject jSONObject = new JSONObject(license());
            JSONArray jsonarray = jSONObject.getJSONArray("Licenses");
            for (int i = 0; i < jsonarray.length(); i++)
            {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Name", jsonarray.getJSONObject(i).getString("Name"));
                map.put("Creator", jsonarray.getJSONObject(i).getString("Creator"));
                map.put("Desc", jsonarray.getJSONObject(i).getString("Desc"));
                map.put("Version", jsonarray.getJSONObject(i).getString("Version"));
                map.put("License", jsonarray.getJSONObject(i).getString("License"));

                licenseList.add(map);

            }
            lvA = new OpenSourceAdapter(AboutActivity.this, licenseList);
            recycleview.setAdapter(lvA);

            lvA.notifyDataSetChanged();
            //Toast.makeText(AboutActivity.this, "Success", 1).show();
        }
        catch (Exception e)
        {
            Toast.makeText(AboutActivity.this, e.getMessage(), 1).show();
        }
        */
        ((TextView) findViewById(R.id.about_name)).setText(app_name());
        ((TextView) findViewById(R.id.about_version)).setText(vb());
        ((TextView) findViewById(R.id.about_build_date)).setText(getInstallDate());
        ((TextView) findViewById(R.id.about_content)).setText(app_name() + " is the protector and obfuscator for Android platforms. It helps you secure your applications and libraries against unauthorized or illegal use, reverse engineering, and cracking."); 
    }

    @SuppressWarnings("ConstantConditions")
    private void setupToolbar(String title)
    {
        CenteredToolBar toolbar = (CenteredToolBar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (title != null)
        {
            getSupportActionBar().setTitle(title);
        }
        else
        {
            
                ActivityInfo activityInfo;
                try
                {
                    activityInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
                    String currentTitle = activityInfo.loadLabel(getPackageManager()).toString();
                     getSupportActionBar().setTitle(currentTitle);
                    
                }
                catch (PackageManager.NameNotFoundException ignored)
                {

                }
            
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public String license()
    {
        try
        {

            InputStream open = getResources().openRawResource(R.raw.license);
            ByteArrayOutputStream xf = new ByteArrayOutputStream();
            byte[] bArr = new byte[5242880];
            while (true)
            {
                int read = open.read(bArr);
                if (read == -1)
                {
                    break;
                }
                xf.write(bArr, 0, read);
            }
            return new String(xf.toByteArray()) ;
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), 1).show();
            return null;
        }
    }
    
    private String getInstallDate() {
        // get app installation date

        PackageManager packageManager = getPackageManager();
        long installTimeInMilliseconds; // install time is conveniently provided in milliseconds

        Date installDate = null;
        String installDateString = null;

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            installTimeInMilliseconds = packageInfo.lastUpdateTime;
            installDateString  = getDate(installTimeInMilliseconds, "MMMM, dd, yyyy");
        }
        catch (Exception e) {
            // an error occurred, so display the Unix epoch
            installDate = new Date(0);
            installDateString = installDate.toString();
        }

        return installDateString;
    }

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public String app_name(){
        String Name = "(unknown)";

        try{
            PackageManager packManager = getPackageManager();
            ApplicationInfo app = getPackageManager().getApplicationInfo(getPackageName(), 0);
            Name = packManager.getApplicationLabel(app).toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return Name;
    }

    public String vb() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 128);
            return packageInfo.versionName ;
        } catch (Exception e) {
            return "null";
        }
    }
}
