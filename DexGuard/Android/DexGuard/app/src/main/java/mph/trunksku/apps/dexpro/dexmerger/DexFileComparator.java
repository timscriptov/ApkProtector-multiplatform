package mph.trunksku.apps.dexpro.dexmerger;
import java.util.Comparator;
import java.io.File;

public class DexFileComparator implements Comparator<File> {

    @Override
    public int compare(File file, File file2) {
        if (DexUtils.isDexFileByName(file) && DexUtils.isDexFileByName(file2)) {
            return getDexFilePosByName(file, 1) - getDexFilePosByName(file2, 1);
        }
        return file.compareTo(file2);
    }

    private int getDexFilePosByName(File file, int i) {
        if (DexUtils.isDexFileByName(file)) {
            String name = file.getName();
            try {
                name = name.substring("classes".length(), name.length() - ".dex".length());
                if (name.trim().length() != 0) {
                    i = Integer.parseInt(name);
                }
            } catch (Throwable th) {
                //mContext.err.println(th.toString());
            }
        }
        return i;
    }


}
