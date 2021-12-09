package mph.trunksku.apps.dexpro.view;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mph.dexprotect.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import mph.trunksku.apps.dexpro.utils.DialogHelper;
public class AppListDialog
{
	private ProgressDialog packageLoadDialog;
	private AlertDialog.Builder adb;
	private boolean isDestroyed;
	private Context mContext;

	private AlertDialog adx;

	private Listener listener;

    public interface Listener
    {
        void selected(String path);
    }

	public AppListDialog(Context c, Listener e)
    {
		mContext = c;
		listener = e;
		adb = new AlertDialog.Builder(c);
		adb.setTitle("Select a APK");
		adb.setNegativeButton("Cancel", null);
	}

	public void show()
    {
		ApplicationLoader runner = new ApplicationLoader();
        runner.execute();

	}

	private void showProgressDialog()
    {
        if (packageLoadDialog == null)
        {
            packageLoadDialog = new ProgressDialog(mContext);
            packageLoadDialog.setIndeterminate(false);
            packageLoadDialog.setCancelable(false);
            packageLoadDialog.setInverseBackgroundForced(false);
            packageLoadDialog.setCanceledOnTouchOutside(false);
            packageLoadDialog.setMessage("Loading installed applications...");
        }
        new DialogHelper(mContext).showBottomDialog(packageLoadDialog);
        //packageLoadDialog.show();
        
    }

    private void dismissProgressDialog()
    {
        if (packageLoadDialog != null && packageLoadDialog.isShowing())
        {
            packageLoadDialog.dismiss();
        }
    }

    private void setupList(ArrayList<PackageInfoHolder> AllPackages)
    {
        final ArrayAdapter<PackageInfoHolder> aa = new ArrayAdapter<PackageInfoHolder>(mContext, R.layout.package_list_item, AllPackages) {
            @SuppressLint("InflateParams")
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                if (convertView == null)
                {
                    convertView = ((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.package_list_item, null);
                }

                PackageInfoHolder pkg = getItem(position);

                ViewHolder holder = new ViewHolder();

                holder.packageLabel = (TextView) convertView.findViewById(R.id.pkg_name);
                holder.packageName = (TextView) convertView.findViewById(R.id.pkg_id);
                holder.packageVersion = (TextView) convertView.findViewById(R.id.pkg_version);
                holder.packageFilePath = (TextView) convertView.findViewById(R.id.pkg_dir);
                holder.packageIcon = (ImageView) convertView.findViewById(R.id.pkg_img);
                holder.position = position;

                convertView.setTag(holder);

                holder.packageLabel.setText(pkg.packageLabel);
                holder.packageName.setText(pkg.packageName);
                holder.packageVersion.setText(pkg.packageVersion);
                holder.packageFilePath.setText(pkg.packageFilePath);

                holder.packageIcon.setImageDrawable(pkg.packageIcon);

                return convertView;
            }
        };
        adb.setAdapter(aa, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					// TODO: Implement this method
					PackageInfoHolder pkg = aa.getItem(p2);
					String myApp = mContext.getPackageName();
					/*if (pkg.packageName.toLowerCase().contains(myApp.toLowerCase())) {
                     Toast.makeText(mContext.getApplicationContext(), "The application " + pkg.packageName + " cannot be decompiled !", Toast.LENGTH_SHORT).show();
                     } else {*/

                    listener.selected(pkg.packageFilePath);
                    adx.dismiss();
                    //setResult(1, new Intent().putExtra("apkpath", holder.packageFilePath.getText().toString()));
                    //finish();
                    //startActivity(new Intent(AppListing.this, MainActivity.class).putExtra("apkpath", holder.packageFilePath.getText().toString()));
                    //showDecompilerSelection(holder)
					//}
				}
			});
       adx = adb.create();
        new DialogHelper(mContext).showBottomDialog(adx);
	    //adx = ;
    }

    private ArrayList<PackageInfoHolder> getInstalledApps(ApplicationLoader task)
    {
        ArrayList<PackageInfoHolder> res = new ArrayList<>();
        List<PackageInfo> packages = mContext.getPackageManager().getInstalledPackages(0);

        int totalPackages = packages.size();

        for (int i = 0; i < totalPackages; i++)
        {
            PackageInfo p = packages.get(i);
            if (!isSystemPackage(p))
            {
                ApplicationInfo appInfo = null;
                try
                {
                    appInfo = mContext.getPackageManager().getApplicationInfo(p.packageName, 0);
                }
                catch (PackageManager.NameNotFoundException e)
                {
					// Ln.e(e);
                }

                int count = i + 1;
                final PackageInfoHolder newInfo = new PackageInfoHolder();
                newInfo.packageLabel = p.applicationInfo.loadLabel(mContext.getPackageManager()).toString();
                task.doProgress("Loading application " + count + " of " + totalPackages + " (" + newInfo.packageLabel + ")");
                newInfo.packageName = p.packageName;
                newInfo.packageVersion = p.versionName;
                if (appInfo != null)
                {
                    newInfo.packageFilePath = appInfo.publicSourceDir;
                }
                newInfo.packageIcon = p.applicationInfo.loadIcon(mContext.getPackageManager());
                res.add(newInfo);
            }
        }
        Comparator<PackageInfoHolder> AppNameComparator = new Comparator<PackageInfoHolder>() {
            public int compare(PackageInfoHolder o1, PackageInfoHolder o2)
            {
                return o1.getPackageLabel().toLowerCase().compareTo(o2.getPackageLabel().toLowerCase());
            }
        };
        Collections.sort(res, AppNameComparator);
        return res;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo)
    {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

	private static class ViewHolder
    {
        TextView packageLabel;
        TextView packageName;
        TextView packageVersion;
        TextView packageFilePath;
        ImageView packageIcon;
        int position;
    }

    private class ApplicationLoader extends AsyncTask<String, String, ArrayList<PackageInfoHolder>>
    {

        @Override
        protected ArrayList<PackageInfoHolder> doInBackground(String... params)
        {
            publishProgress("Retrieving installed application");
            return getInstalledApps(this);
        }

        @Override
        protected void onPostExecute(ArrayList<PackageInfoHolder> AllPackages)
        {
            setupList(AllPackages);
            if (!isDestroyed)
            {
                dismissProgressDialog();
            }
        }

        public void doProgress(String value)
        {
            publishProgress(value);
        }

        @Override
        protected void onPreExecute()
        {
            showProgressDialog();
        }

        @Override
        protected void onProgressUpdate(String... text)
        {
            packageLoadDialog.setMessage(text[0]);
        }
    }

    class PackageInfoHolder
    {
        private String packageLabel = "";
        private String packageName = "";
        private String packageVersion = "";
        private String packageFilePath = "";
        private Drawable packageIcon;

        public String getPackageLabel()
        {
            return packageLabel;
        }
    }


}
