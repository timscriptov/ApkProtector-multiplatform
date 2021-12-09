package mph.trunksku.apps.dexpro.fragment;
import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.mph.dexprotect.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import mph.trunksku.apps.dexpro.MainActivity;

public class AppListFragment extends Fragment {

    private View mView;

    private ListView listView;

    private LinearLayout loadLayout;

    
    private LinearLayout loadedLayout;

   // private ProgressDialog packageLoadDialog;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_applist, container, false);
        listView = (ListView) mView.findViewById(R.id.list);
        loadLayout = (LinearLayout) mView.findViewById(R.id.load_layout);
        loadedLayout = (LinearLayout) mView.findViewById(R.id.loaded_layout);
        
        
        ApplicationLoader runner = new ApplicationLoader();
        runner.execute();
        return mView;
    }
  /*  private void showProgressDialog() {
        if (packageLoadDialog == null) {
            packageLoadDialog = new ProgressDialog(getActivity());
            packageLoadDialog.setIndeterminate(false);
            packageLoadDialog.setCancelable(false);
            packageLoadDialog.setInverseBackgroundForced(false);
            packageLoadDialog.setCanceledOnTouchOutside(false);
            packageLoadDialog.setMessage("Loading installed applications...");
        }
        packageLoadDialog.show();
    }

    private void dismissProgressDialog() {
        if (packageLoadDialog != null && packageLoadDialog.isShowing()) {
            packageLoadDialog.dismiss();
        }
    }*/

    private void setupList(ArrayList<PackageInfoHolder> AllPackages) {
        if (AllPackages.size() < 1) {
            loadedLayout.setVisibility(View.GONE);
            //pickApp.setVisibility(View.GONE);
            loadLayout.setVisibility(View.VISIBLE);
        }else{
            loadLayout.setVisibility(View.INVISIBLE);
            
            final ArrayAdapter<PackageInfoHolder> aa = new ArrayAdapter<PackageInfoHolder>(getActivity(), R.layout.package_list_item, AllPackages) {
                @SuppressLint("InflateParams")
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = getActivity().getLayoutInflater().inflate(R.layout.package_list_item, null);
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
                    holder.packageVersion.setText("version " + pkg.packageVersion);
                    holder.packageFilePath.setText(pkg.packageFilePath);

                    holder.packageIcon.setImageDrawable(pkg.packageIcon);

                    return convertView;
                }
            };
            listView.setAdapter(aa);
            listView.setTextFilterEnabled(true);
            listView.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        // final ViewHolder holder = (ViewHolder) view.getTag();
                        PackageInfoHolder pkg = aa.getItem(position);
                        HomeFragment.change(getActivity(), pkg.packageFilePath);
                        MainActivity.drawerLayout.closeDrawers();

                    }
                });
            loadedLayout.setVisibility(View.VISIBLE);
        }
        
    }

    private ArrayList<PackageInfoHolder> getInstalledApps(ApplicationLoader task) {
        ArrayList<PackageInfoHolder> res = new ArrayList<>();
        List<PackageInfo> packages = getActivity().getPackageManager().getInstalledPackages(0);

        int totalPackages = packages.size();

        for (int i = 0; i < totalPackages; i++) {
            PackageInfo p = packages.get(i);
            if (!isSystemPackage(p)) {
                ApplicationInfo appInfo = null;
                try {
                    appInfo = getActivity().getPackageManager().getApplicationInfo(p.packageName, 0);
                } catch (PackageManager.NameNotFoundException e) {
                    // Ln.e(e);
                }

                int count = i + 1;

                final PackageInfoHolder newInfo = new PackageInfoHolder();
                newInfo.packageLabel = p.applicationInfo.loadLabel(getActivity().getPackageManager()).toString();

                task.doProgress("Loading application " + count + " of " + totalPackages + " (" + newInfo.packageLabel + ")");

                newInfo.packageName = p.packageName;
                newInfo.packageVersion = p.versionName;

                if (appInfo != null) {
                    newInfo.packageFilePath = appInfo.publicSourceDir;
                }

                newInfo.packageIcon = p.applicationInfo.loadIcon(getActivity().getPackageManager());
                res.add(newInfo);
            }
        }
        Comparator<PackageInfoHolder> AppNameComparator = new Comparator<PackageInfoHolder>() {
            public int compare(PackageInfoHolder o1, PackageInfoHolder o2) {
                return o1.getPackageLabel().toLowerCase().compareTo(o2.getPackageLabel().toLowerCase());
            }
        };
        Collections.sort(res, AppNameComparator);
        return res;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    /*@Override
    protected void onDestroy() {
        isDestroyed = true;
        dismissProgressDialog();
        super.onDestroy();
    }*/

    private static class ViewHolder {
        TextView packageLabel;
        TextView packageName;
        TextView packageVersion;
        TextView packageFilePath;
        ImageView packageIcon;
        int position;
    }

    private class ApplicationLoader extends AsyncTask<String, String, ArrayList<PackageInfoHolder>> {

        @Override
        protected ArrayList<PackageInfoHolder> doInBackground(String... params) {
            publishProgress("Retrieving installed application");
            return getInstalledApps(this);
        }

        @Override
        protected void onPostExecute(ArrayList<PackageInfoHolder> AllPackages) {
            setupList(AllPackages);
           /* if (!isDestroyed) {
                dismissProgressDialog();
            }*/
        }

        public void doProgress(String value) {
            publishProgress(value);
        }

        @Override
        protected void onPreExecute() {
            //showProgressDialog();
        }

        @Override
        protected void onProgressUpdate(String... text) {
           // packageLoadDialog.setMessage(text[0]);
        }
    }

    class PackageInfoHolder {
        private String packageLabel = "";
        private String packageName = "";
        private String packageVersion = "";
        private String packageFilePath = "";
        private Drawable packageIcon;

        public String getPackageLabel() {
            return packageLabel;
        }
    }

   

    
    
}
