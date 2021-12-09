package mph.trunksku.apps.dexpro.utils;
import java.io.*;
import java.util.*;
import mph.trunksku.apps.dexpro.logger.Log;
import java.util.zip.*;
import android.content.Context;


public class ZipUtils {

    private final List<File> fileList;

    private List<String> paths;

    public ZipUtils() {
        fileList = new ArrayList<>();
        paths = new ArrayList<>(25);
    }

    public boolean unZipIt(File zipFileO, File destFile) {
        byte[] buffer = new byte[1024];
        try {
            //create output directory is not exists
            float prev = -1; // to check if the percent changed and its worth updating the UI
            int finalSize = 0;
            float current = 0;
            String zipFile = null;
            //get the zip file content
            finalSize = (int) new File(zipFile).length();
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                current += ze.getCompressedSize();
                FileOutputStream fos = null;
                String fileName = ze.getName();
                if (!fileName.contains("META-INF")) {
                    File newFile = new File(destFile + File.separator + fileName);
                    new File(newFile.getParent()).mkdirs();
                    fos = new FileOutputStream(newFile);
                    //addLog("Recompiling - " + fileName);
                    do {
                        int numread = zis.read(buffer);
                        if (numread <= 0) {
                            break;
                        }
                        fos.write(buffer, 0, numread);
                        if (prev != current / finalSize * 100) {
                            prev = current / finalSize * 100;
                            Log.i("", String.valueOf(((int) prev) + "%"));
                            //publishProgress(mi.getAppName(), "Compiling... " + ((int) prev) + "%", "40");
                        }
                    } while (true);
                    fos.close();
                }
                ze = zis.getNextEntry();
            }
            new File(zipFile).delete();
            //Utils.deleteFolder(new File(xpath + File.separator + "gen" + File.separator + "META-INF"));
            Log.i("", "Extracting apk complete");
            return true;
        } catch (Exception ex) {
            Log.i("", "Extract Error: " + ex.getMessage());
            return false;
        }
	}

    public void zipIt(File sourceFile, File zipFile) {
        if (sourceFile.isDirectory()) {
            byte[] buffer = new byte[2048];
            FileOutputStream fos = null;
            ZipOutputStream zos = null;

            try {

                String sourcePath = sourceFile.getPath();
                generateFileList(sourceFile);

                fos = new FileOutputStream(zipFile);
                zos = new ZipOutputStream(fos);
				try {
					zos.setLevel(Deflater.BEST_COMPRESSION);
				} catch (InternalError e) {}
               // System.out.println("Output to Zip : " + zipFile);
                FileInputStream in = null;

                for (File file : this.fileList) {
                    String path = file.getParent().trim();
                    path = path.substring(sourcePath.length());

                    if (path.startsWith(File.separator)) {
                        path = path.substring(1);
                    }

                    if (path.length() > 0) {
                        if (!paths.contains(path)) {
                            paths.add(path);
                            ZipEntry ze = new ZipEntry(path + "/");
                            zos.putNextEntry(ze);
                            zos.closeEntry();
                        }
                        path += "/";
                    }

                    String entryName = path + file.getName();
                    //Log.i("", "Building - " + entryName);
                    ZipEntry ze = new ZipEntry(entryName);

                    zos.putNextEntry(ze);
                    try {
                        in = new FileInputStream(file);
                        int len;
                        while ((len = in.read(buffer)) > 0) {
                            zos.write(buffer, 0, len);
                        }
                    } finally {
                        in.close();
                    }
                }

                zos.closeEntry();
                //System.out.println("Folder successfully compressed");

            } catch (IOException ex) {
               // ex.printStackTrace();
            } finally {
                try {
                    zos.close();
                } catch (IOException e) {
                   // e.printStackTrace();
                }
            }
        }
    }

    protected void generateFileList(File node) {

        // add file only
        if (node.isFile()) {
            fileList.add(node);

        }

        if (node.isDirectory()) {
            File[] subNote = node.listFiles();
            for (File filename : subNote) {
                generateFileList(filename);
            }
        }
    }
    // ZipFile zipFile = new ZipFile(new File("/path/to/zip/file.zip));
    // InputStream inputStream = searchWithinZipArchive("findMe.txt", zipFile);

    public static boolean isExist(String name, File file) {
        try {
            Enumeration<? extends java.util.zip.ZipEntry> entries = new java.util.zip.ZipFile(file).entries();
            while (entries.hasMoreElements()) {
                java.util.zip.ZipEntry zipEntry = entries.nextElement();
                if (zipEntry.getName().toLowerCase().endsWith(name)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
	
	public static List<String> getRes( File file) {
		List<String> list = new ArrayList();
		
        try {
			Enumeration<? extends java.util.zip.ZipEntry> entries = new java.util.zip.ZipFile(file).entries();
            while (entries.hasMoreElements()) {
                java.util.zip.ZipEntry zipEntry = entries.nextElement();
                if (zipEntry.getName().toLowerCase().startsWith("res/")) {
                    list.add(RemoveLastPart(zipEntry.getName().toLowerCase()));
                }
            }
            return list;
        } catch (Exception e) {
            return list;
        }
    }
	private static String RemoveLastPart(String str) {
        try {
            String[] arr = str.split("/");
            String result = "";
            if (arr.length > 0) {
                result = str.substring(0, str.lastIndexOf("/" + arr[arr.length - 1]));
            }
			if(result.contains("-")){
				result = result.split("-")[0];
			}
            return result;
        } catch (Exception e) {
            return str;
        }
    }
    public static File readFile(Context con, File zip, String name) {
        byte[] bArr = new byte[1024];
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zip));
            for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
                if (nextEntry.getName().equals(name)) {
                    File file = new File(new StringBuilder().append(con.getCacheDir()).append(File.separator).append(nextEntry.getName()).toString());
                    new File(file.getParent()).mkdirs();
                    if (file.exists()) {
                        file.delete();
                    } 
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.close();
                    break;
                }
            }
            zipInputStream.closeEntry();
            zipInputStream.close();
            //new File(con.getCacheDir() + File.separator + "security.msi").delete();
            return new File(con.getCacheDir() + File.separator + name);
        } catch (IOException e) {
            return null;
        }
	}
}
