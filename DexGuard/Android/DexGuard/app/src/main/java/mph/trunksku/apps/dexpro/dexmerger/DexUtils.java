package mph.trunksku.apps.dexpro.dexmerger;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import org.jf.dexlib2.writer.io.MemoryDataStore;
import org.jf.dexlib2.writer.builder.DexBuilder;
import java.util.Arrays;
import org.jf.dexlib2.iface.ClassDef;
import java.io.StringWriter;
import org.jf.util.IndentingWriter;
import org.jf.baksmali.Adaptors.ClassDefinition;
import org.jf.baksmali.BaksmaliOptions;

public class DexUtils {
    
    public static List<String> getClassesDexPathsFormApk(File file) throws IOException {
        List<String> arrayList = new ArrayList();
        try {
            for (String str : ZipUtils.getFilesPath(file)) {
                if (!str.contains("/") && str.startsWith("classes") && str.endsWith(".dex")) {
                    arrayList.add(str);
                }
            }
        } catch (IOException e) {}
        return arrayList;
    }
    
    public static  List<File> fileList(File[] tArr) {
        if (tArr == null) {
            return (List<File>) null;
        }
        ArrayList<File> arrayList = new ArrayList<File>();
        for (File file : tArr) {
            arrayList.add(file);

        }
        return arrayList;
    }
    
    public static boolean isDexFileByName(File file) {
        String name = file.getName();
        return file.isFile() && name.startsWith("classes") && name.endsWith(".dex");
    }
    
    public static void delectAllFileInDir(File file) {
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
	
	public static String getPkgNameByType(String type) {

        return type.replaceFirst("L", "").replace("/", ".").replace(";", "");
    }


    public static String getTypeByPkg(String pkg) {

        return "L"+pkg.replace(".","/")+";";
    }


    public static byte[] getDexBuilderData(DexBuilder dexBuilder) throws IOException {

        MemoryDataStore memoryDataStore = new MemoryDataStore();
        dexBuilder.writeTo(memoryDataStore);
        return Arrays.copyOf(memoryDataStore.getBufferData(), memoryDataStore.getSize());

    }


    public static String getSmali(ClassDef classDef) {
        String code = null;
        try {
            StringWriter stringWriter = new StringWriter();
            IndentingWriter writer = new IndentingWriter(stringWriter);
            ClassDefinition classDefinition = new ClassDefinition(new BaksmaliOptions(), classDef);
            classDefinition.writeTo(writer);
            writer.close();
            code = stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return code;
    }
}
