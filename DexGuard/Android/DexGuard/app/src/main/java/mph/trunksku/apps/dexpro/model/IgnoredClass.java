package mph.trunksku.apps.dexpro.model;

import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;

public class IgnoredClass implements Comparable<IgnoredClass> {
    private String describe;
    private String label;
    private String name;
    private PackageManager pm;

    

    public IgnoredClass(String str) {
        this.name = str;
        
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String getLabel() {
        
         return name;
    }

    public String getName() {
       /* if (this.label != null) {
            return this.label;
        }

        int lastIndexOf = this.name.lastIndexOf(".");
        if (lastIndexOf >= 0) {
            label = this.name.substring(lastIndexOf + 1);
            return label;
        }*/
        return name;
    }

    public void setDescribe(String str) {
        this.describe = str;
    }

    public String getDescribe() {
        if (this.describe != null) {
            return this.describe;
        }
        describe = "No description";
        return describe;
    }

    @Override
    public int compareTo(IgnoredClass permission) {
        return permission.getLabel().compareTo(getLabel());
    }
}

