package mph.trunksku.apps.dexpro.dexmerger;
import java.io.FileFilter;
import java.io.File;

public class DexFileFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        return DexUtils.isDexFileByName(file);
    }
    


   
}
