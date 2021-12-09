package mph.trunksku.apps.dexpro.utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import android.os.*;

/**
 * Created by Niranjan on 30-05-2015.
 */
public class SourceInfo {

    private String packageLabel;
    private String packageName;

    private String packagePath;
    //private final boolean hasSource;
	//private static String path = Environment.getExternalStorageDirectory().toString()+"/DexProtect/output";
	
	private SourceInfo(String packageLabel, String packageName, String path) {
        this.packageLabel = packageLabel;
        this.packageName = packageName;
        this.packagePath = path;
        //this.hasSource = true;
    }

    public static void initialise(String path, MyAppInfo processService) {
        try {
            JSONObject json = new JSONObject();
            json.put("package_label", processService.getAppName());
            json.put("package_name", processService.getPackage());
            //json.put("has_java_sources", false);
            //json.put("has_xml_sources", false);
            String filePath = path+"/"+processService.getPackage();
			//new File(filePath).mkdirs();
            FileUtils.writeStringToFile(new File(filePath+"/info.mz"), json.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    /*public static void setjavaSourceStatus(ProcessService processService, Boolean status) {
        try {
            File infoFile = new File(processService.sourceOutputDir + "/info.json");
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            json.put("has_java_sources", status);
            FileUtils.writeStringToFile(infoFile, json.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setXmlSourceStatus(ProcessService processService, Boolean status) {
        try {
            File infoFile = new File(processService.sourceOutputDir + "/info.json");
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            json.put("has_xml_sources", status);
            FileUtils.writeStringToFile(infoFile, json.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
*/
    public static void delete(String path, MyAppInfo processService) {
        try {
            File infoFile = new File(path+"/"+processService.getPackage() + "/info.mz");
            infoFile.delete();
        } catch (Exception e) {
           // Ln.e(e);
        }
    }

    public static String getLabel(String directory) {
        try {
            File infoFile = new File(directory + "/info.mz");
            JSONObject json = new JSONObject(FileUtils.readFileToString(infoFile));
            return json.getString("package_label");
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public static SourceInfo getSourceInfo(File infoFile) {
        try {
            JSONObject json = new JSONObject(FileUtils.readFileToString(new File(infoFile.getAbsolutePath() + "/info.mz")));
            return new SourceInfo(json.getString("package_label"), json.getString("package_name"), infoFile.getAbsolutePath());
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public String getPackageLabel() {
        return packageLabel;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPackagePath() {
        return packagePath;
    }
}

