package mph.trunksku.apps.dexpro.dexmerger;

import com.android.dexj.Dex;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import mph.trunksku.apps.dexpro.logger.Log;

public class ApkDexMerger {
    private final File mApkFile;
    private final DxContext mContext;
    public final File mDecompressDir;
    public final File mWorkDir;

    private class DexFileComparator implements Comparator<File> {
        
        @Override
        public int compare(File file, File file2) {
            if (isDexFileByName(file) && isDexFileByName(file2)) {
                return getDexFilePosByName(file, 1) - getDexFilePosByName(file2, 1);
            }
            return file.compareTo(file2);
        }

        private int getDexFilePosByName(File file, int i) {
            if (isDexFileByName(file)) {
                String name = file.getName();
                try {
                    name = name.substring("classes".length(), name.length() - ".dex".length());
                    if (name.trim().length() != 0) {
                        i = Integer.parseInt(name);
                    }
                } catch (Throwable th) {
                    mContext.err.println(th.toString());
                }
            }
            return i;
        }

        
    }

    private class DexFileFilter implements FileFilter {
        
        
        @Override
        public boolean accept(File file) {
            return isDexFileByName(file);
        }
    }

    public ApkDexMerger(File file, File tmp) {
        this(new DxContext(), file, tmp);
    }

    public ApkDexMerger(DxContext dxContext, File file, File tmp) {
        dxContext.out.println("initialization...");
        this.mContext = dxContext;
        this.mApkFile = file;
        this.mWorkDir = tmp/*new File(file.getParentFile(), ".dexmerger")*/;
        this.mDecompressDir = new File(this.mWorkDir.getAbsolutePath() + "/decompress");
        //clearWorkDir();
    }

    public void merger(File file) throws IOException {
        long nanoTime = System.nanoTime();
        this.mContext.out.println("Decompressing");
        try {
            //File file2 = this.mDecompressDir;
            if (!mDecompressDir.exists()) {
                mDecompressDir.mkdirs();
            }
            Log.i("", mApkFile.getAbsolutePath());
            try{
               // ZipUtils.unzipFile(mApkFile, mDecompressDir);
                new mph.trunksku.apps.dexpro.utils.ZipUtils().unZipIt(mApkFile, mDecompressDir);
                Log.i("", mDecompressDir.getAbsolutePath());
                
            }catch(Exception e){
                Log.i("", e.getMessage());
            }
            this.mContext.out.println("Get DEX");
            List<File> array2List = null;
            try{
                for (String str : mDecompressDir.list()) {
                    Log.i("","Files: " + str );
                }
                array2List = array2List(mDecompressDir.listFiles(new DexFileFilter()));
            }catch(Exception e){
                Log.i("", e.getMessage());
            }
            if (array2List == null) {
                throw new IOException("Failed to get DEX list");
            } else if (array2List.isEmpty()) {
                throw new IOException("No DEX file");
            } else {
                Collections.sort(array2List, new DexFileComparator());
                MultDexMerger.Builder builder = new MultDexMerger.Builder(this.mContext);
                builder.add(array2List.toArray(new File[0]));
                for (int i = 0; i < array2List.size(); i++) {
                    this.mContext.out.println(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append("[").append(i).toString()).append("] ").toString()).append(((File) array2List.get(i)).getName()).toString());
                }
                this.mContext.out.println("Start merging DEX");
                try {
                    List merger = builder.create().merger();
                    for (File delete : array2List) {
                        delete.delete();
                    }
                    for (int i2 = 0; i2 < merger.size(); i2++) {
                        String str;
                        Dex dex = (Dex) merger.get(i2);
                        String str2 = "classes%s.dex";
                        if (i2 < 1) {
                            str = "";
                        } else {
                            str = String.valueOf(i2 + 1);
                        }
                        str = String.format(str2, new Object[]{str});
                        this.mContext.out.println(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append("[").append(i2).toString()).append("] ").toString()).append(str).toString());
                        File file2 = this.mDecompressDir;
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        dex.writeTo(new File(file2, str));
                    }
                    File delete2 = new File(this.mWorkDir, "build/apk");
                    delete2.getParentFile().mkdirs();
                    this.mContext.out.println("Rebuild APK");
                    Collection array2List2 = array2List(this.mDecompressDir.listFiles());
                    if (array2List2 == null || array2List2.isEmpty()) {
                        throw new IOException("Failed to get file list");
                    }
                    try {
                        ZipUtils.zipFiles(array2List2, delete2);
                        if (!delete2.renameTo(file)) {
                            this.mContext.err.println("Apk export failed");
                        }
                        this.mContext.out.println("Clear cache");
                        clearWorkDir();
                        this.mContext.out.printf("Completed /%. 1FS", new Object[]{new Float(((float) (System.nanoTime() - nanoTime)) / 1.0E9f)});
                    } catch (IOException e) {
                        this.mContext.err.println("Apk build failed");
                        throw e;
                    }
                } catch (IOException e2) {
                    this.mContext.err.println("DEX merge failed");
                    throw e2;
                }
            }
        } catch (IOException e22) {
            this.mContext.err.println("Decompression failed");
            throw e22;
        }
    }

    public void clearWorkDir() {
        delectAllFileInDir(this.mWorkDir);
    }

    public static List<String> getClassesDexPathsFormApk(File file) throws IOException {
        List<String> arrayList = new ArrayList();
        for (String str : ZipUtils.getFilesPath(file)) {
            if (!str.contains("/") && str.startsWith("classes") && str.endsWith(".dex")) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private static  List<File> array2List(File[] tArr) {
        if (tArr == null) {
            return (List<File>) null;
        }
        ArrayList<File> arrayList = new ArrayList<File>();
        for (File file : tArr) {
            arrayList.add(file);
            
        }
        return arrayList;
    }

    private static void delectAllFileInDir(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                int i = 0;
                while (listFiles != null && i < listFiles.length) {
                    delectAllFileInDir(listFiles[i]);
                    i++;
                }
                file.delete();
                return;
            }
            file.delete();
        }
    }

    private boolean isDexFileByName(File file) {
        String name = file.getName();
        return file.isFile() && name.startsWith("classes") && name.endsWith(".dex");
    }
}

