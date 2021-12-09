package mph.trunksku.apps.dexpro.core;
import java.util.zip.ZipFile;
import mph.trunksku.apps.dexpro.utils.ZipOut;
import java.util.HashMap;
import mph.trunksku.apps.dexpro.utils.FileUtils;
import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import mph.trunksku.apps.dexpro.logger.DebugLog;
public class EncryptResources {

	private ZipFile zipFile;
	public EncryptResources(String inputPath, String outputPath) throws Exception {
        //super(inputPath, outputPath);
        
        zipFile=new ZipFile(inputPath);
        ZipOut zipOut=new ZipOut(outputPath).setInput(zipFile);
        zipOut.removeFile("resources.arsc");
        ArscObfuscator arscObfuser=new ArscObfuscator(getZipInputStream("resources.arsc"));
        zipOut.addFile("resources.arsc", arscObfuser.getData());

        HashMap<String,String> map=arscObfuser.getMap();
        int i=0;

        for (String key:map.keySet()) {
            zipOut.removeFile(key);
			//DebugLog.i("", map.get(key).toString());
            
            zipOut.addFile(map.get(key), FileUtils.toByteArray(getZipInputStream(key)));
            //println(key+"->"+map.get(key));
            //println(key);
            /*i++;
            int ii=i;
            activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						fragment.progressBar.setMax(map.size());
						fragment.progressBar.setProgress(ii);
						fragment.tv_label.setText(key);
					}
				});*/


        }
        //println(activity.getString(R.string.saving_file));

        zipOut.save();
        //println(activity.getString(R.string.file_out_puting) + outputPath);

    }
	
	public InputStream getZipInputStream(String entry) throws IOException {

		return new ByteArrayInputStream(FileUtils.toByteArray(zipFile.getInputStream(zipFile.getEntry(entry))));
	}
}
