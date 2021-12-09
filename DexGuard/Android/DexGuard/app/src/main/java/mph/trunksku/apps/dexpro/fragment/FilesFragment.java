package mph.trunksku.apps.dexpro.fragment;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mph.dexprotect.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import mph.trunksku.apps.dexpro.MainActivity;
import mph.trunksku.apps.dexpro.adapter.ManagerFilesAdapter;

public class FilesFragment extends Fragment implements ManagerFilesAdapter.OnItemClickListener {

    private View mView;

    //private RecyclerView rvManagerList;
    private static final String HOME_PATH = Environment.getExternalStorageDirectory().toString();
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String BACK_DIR = "../";

    private RecyclerView rvManagerList;
    private ManagerFilesAdapter adapter;

    private List<ManagerFilesAdapter.ManagerItem> folderList;
    private List<ManagerFilesAdapter.ManagerItem> fileList;
    private String currentPath;
    private File backDir;
	private String pathAbertoPeloInicio;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_files, container, false);
        rvManagerList = (RecyclerView) mView.findViewById(R.id.rvMain_ManagerList);
        //setToolbar();

        // Para Android 6.0 Marshmallow e superior
        /*if (Build.VERSION.SDK_INT >= 23 && !permissionGranted()) {
            requestPermissionInfo(); // Para Android 5.1 Lollipop e inferior
            return;
        }*/

         startMainListManager();
		
        return mView;
    }
    
    @Override
    public void onItemClick(View view, int position) {
        File file = new File(folderList.get(position).getDirPath());
        if (file.isDirectory()) {
            fileManager(folderList.get(position).getDirPath());
        } else {
            // Tratamento para arquivos
            String file_extensao = getExtension(file);
            MainActivity.drawerLayout.closeDrawers();
            HomeFragment.change(getActivity(), file.getAbsolutePath());
            
           /* if (file_extensao != null && file_extensao.equals(ConfigParser.FILE_EXTENSAO)) {
                try {
                    importarConfigInputFile(new FileInputStream(file));
                } catch (FileNotFoundException e) {
                    Toast.makeText(this, R.string.error_file_not_found,
                                   Toast.LENGTH_SHORT).show();
                }
            }*/
        }
    }

    @Override
    public void onItemLongClick(View view, int position)
    {
        File file = new File(folderList.get(position).getDirPath());
        if (!file.isDirectory()) {
              //showApagarPrompt(file);
        }
    }

    private void startMainListManager() {
        folderList = new ArrayList<>();
        fileList = new ArrayList<>();
        currentPath = null;
        pathAbertoPeloInicio = null;

       /* if (mToolbar != null) {
            mToolbar.setSubtitle(R.string.select_file_setting);
        }*/

        backDir = null;

        String[] listDirs = {
            HOME_PATH,
            HOME_PATH + "/Download"/*,
            HOME_PATH + "/SocksHttp"*/
        };

        for (String dir : listDirs) {
            File file = new File(dir);

            if (file.exists() && !file.isHidden() && file.canRead()) {
                if (file.isDirectory()) {
                    String dir_name = file.getName();

                    if (dir.equals(HOME_PATH))
                        dir_name = getString(R.string.dir_home_name);

                    folderList.add(new ManagerFilesAdapter.ManagerItem(dir_name, file.getPath(), getString(R.string.dir_name)));
                }
            }

        }

        folderList.addAll(fileList);

        adapter = new ManagerFilesAdapter(getActivity(), folderList);
        rvManagerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(this);
        rvManagerList.setAdapter(adapter);
    }

    private void fileManager(String folderPath) {
        if (folderPath == null || folderPath.equals(BACK_DIR)) {
            if (backDir != null && canGoBackFolder())
                folderPath = backDir.getPath();
            else {
                startMainListManager();
                return;
            }
        }

        folderList = new ArrayList<>();
        fileList = new ArrayList<>();

        // está vindo do inicio
        if (currentPath == null) {
            pathAbertoPeloInicio = folderPath;
        }

        currentPath = folderPath;

        /*if (mToolbar != null) {
            mToolbar.setSubtitle(folderPath);
        }*/

        File path = new File(folderPath);
        if (path.getParentFile() != null && !currentPath.equals(pathAbertoPeloInicio)) {
            backDir = path.getParentFile();
        }

        for (File file : path.listFiles()) {
            if (!file.isHidden() && file.canRead()) {
                if (file.isDirectory()) {
                    folderList.add(new ManagerFilesAdapter.ManagerItem(file.getName(), file.getPath(), getString(R.string.dir_name)));
                } else {
                    String file_extensao = getExtension(file);
                    if (file_extensao != null && file_extensao.equals("apk")) {
                        String dateLastModified = String.format("%s %s",
                                                                android.text.format.DateFormat.getDateFormat(getActivity()).format(file.lastModified()),
                                                                android.text.format.DateFormat.getTimeFormat(getActivity()).format(file.lastModified()));

                        fileList.add(new ManagerFilesAdapter.ManagerItem(file.getName(), file.getPath(), dateLastModified));
                    }
                }
            }
        }

        Collections.sort(folderList);
        Collections.sort(fileList);
        folderList.addAll(fileList);

        // Adiciona a opção de voltar
        if (canGoBackFolder() || currentPath.equals(pathAbertoPeloInicio)) {
            folderList.add(0, new ManagerFilesAdapter.ManagerItem("...", BACK_DIR, "Pasta"));
        }

        adapter = new ManagerFilesAdapter(getActivity(), folderList);
        rvManagerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(this);
        rvManagerList.setAdapter(adapter);
    }

    /*@Override
    public void onBackPressed() {
        if (canGoBackFolder() || currentPath != null && currentPath.equals(pathAbertoPeloInicio)) {
            fileManager(BACK_DIR);
        }
        else {
            super.onBackPressed();
        }
    }*/

    private boolean canGoBackFolder() {
        if (backDir != null) {
            return backDir.canRead() && !backDir.getPath().equals(currentPath);
        }
        return false;
    }

    /*private void requestPermissionInfo() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(ConfigImportFileActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Dialog de informação para caso o usuário já tenha negado as permissões pelo menos uma vez
            /*AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(R.string.title_permission_request);
            dialog.setMessage(R.string.message_permission_request);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });
            dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {*/
                        //ActivityCompat.requestPermissions(ConfigImportFileActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                        //dialogInterface.dismiss();
                    /*}
                });
            dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        dialogInterface.dismiss();
                        finish();
                    }
                });
            dialog.show();*/
       /* } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }*/

    // Método chamado assim que o usuário concede ou nega uma permissão
    /*@Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //fileManager(HOME_PATH);
                    startMainListManager();
                } else {
                    requestPermissionInfo();
                }
                break;
        }
    }

    private boolean permissionGranted() {
        int result = ContextCompat.checkSelfPermission(ConfigImportFileActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }*/

    public String getExtension(File file) {
        String filename = file.getAbsolutePath();

        if (filename.contains(".")) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        }

        return "";
    }

    /*public void importarConfigInputFile(InputStream inputFile) {
        try {

            if (!ConfigParser.convertInputAndSave(inputFile, this)) {
                throw new IOException(getString(R.string.error_save_settings));
            }

            long mValidade = new Settings(this)
                .getPrefsPrivate().getLong(Settings.CONFIG_VALIDADE_KEY, 0);

            if (mValidade > 0) {
                SkStatus.logInfo(R.string.log_settings_valid,
                                 android.text.format.DateFormat.getDateFormat(this).format(mValidade));
            }

            Toast.makeText(this, R.string.success_import_settings, Toast.LENGTH_SHORT)
                .show();

            // atualiza views
            SocksHttpMainActivity.updateMainViews(this);

        } catch(IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT)
                .show();
        }

        Intent intent = new Intent(this, LauncherActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finish();
    }*/


    /**
     * Toolbar
     */

    

    /*private void showApagarPrompt(final File file) {
        AlertDialog dialog = new AlertDialog.Builder(this)
            .create();

        dialog.setTitle(R.string.title_delete_file);
        dialog.setMessage(getString(R.string.alert_delete_file));

        dialog.setButton(dialog.BUTTON_POSITIVE, getString(R.string.yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface p1, int p2)
                {
                    if (file.delete())
                        fileManager(currentPath);
                    else
                        Toast.makeText(getApplicationContext(), R.string.error_delete_file,
                                       Toast.LENGTH_SHORT).show();
                }
            });

        dialog.setButton(dialog.BUTTON_NEGATIVE, getString(R.string.no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface p1, int p2) {}
            });

        dialog.show();
    }*/
    
}
