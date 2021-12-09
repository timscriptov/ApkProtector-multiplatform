package mph.trunksku.apps.dexpro.core;
import pxb.android.axml.NodeVisitor;
import pxb.android.axml.AxmlReader;
import java.nio.file.Files;
import pxb.android.axml.AxmlWriter;
import pxb.android.axml.AxmlVisitor;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class PackageNameChange {
    static boolean needRemoveConflict = false;
    static boolean needRemoveLib = false;
    static String newPackageFullName;
	static boolean changed;
    
    public static void change(File path, String npackage) throws Exception {
        newPackageFullName = npackage;
        AxmlReader ar = new AxmlReader(FileUtils.readFileToByteArray(path));
        AxmlWriter aw = new AxmlWriter();
        ar.accept(new AxmlVisitor(aw) {

                @Override
                public NodeVisitor child(String ns, String name) {
                    return new MyNodeVisitor(super.child(ns, name), name);
                }
            });

        if (changed) {
            FileUtils.writeByteArrayToFile(path, aw.toByteArray());
			
            //Files.write(androidManifestBinXml, aw.toByteArray());
        } else {
            throw new Exception("Faild");
        }
    }
    
    static String NS = "http://schemas.android.com/apk/res/android";

    static class MyNodeVisitor extends NodeVisitor {
        static String level = "";
        static String oldPackageName;
        boolean didLogNodeName = false;
        String nodeName = "";

        MyNodeVisitor(NodeVisitor nv, String nodeName) {
            super(nv);
            this.nodeName = nodeName;
        }

        @Override
        public NodeVisitor child(String ns, String name) {
            // LOG(level + "<" + name + ">");
            if (needRemoveConflict && ("original-package".equals(name) || "provider".equals(name)) && ns == null) {
                //LOG("x   " + level + "<" + name + "> will be removed");
                changed = true;
                return null;
            } else if (needRemoveLib && ("uses-library".equals(name)) && ns == null) {
                //LOG("x   " + level + "<" + name + "> will be removed");
                changed = true;
                return null;
            }
            level += "    ";
            return new MyNodeVisitor(super.child(ns, name), name);
        }

        @Override
        public void attr(String ns, String name, int resourceId, int type, Object val) {
            String oldName = name;
            Object oldVal = val;
            // LOG(level + "    " + oldName + "=" + oldVal);
            if (ns == null && "package".equals(name) && "manifest".equals(nodeName) && type == NodeVisitor.TYPE_STRING && level.length() == 0) {
                oldPackageName = (String) val;
                if (!newPackageFullName.equals(val)) {
                    val = newPackageFullName;
                }
            } else if (type == NodeVisitor.TYPE_STRING && ("name".equals(name) || "backupAgent".equals(name) || "manageSpaceActivity".equals(name) || "targetActivity".equals(name)) && NS.equals(ns) && val != null && val instanceof String) {
                if (((String) val).startsWith(".")) {
                    val = oldPackageName + val;
                } else if (!((String) val).contains(".") && ((String) val).length() > 0) {
                    val = oldPackageName + "." + val;
                }
            } else if (type == NodeVisitor.TYPE_STRING && "value".equals(name) && NS.equals(ns) && val != null && val instanceof String) {
                if (((String) val).startsWith(".")) {
                    val = oldPackageName + val;
                }
            } else if (needRemoveConflict && ("protectionLevel".equals(name) || "process".equals(name) || "sharedUserId".equals(name)) && NS.equals(ns)) {
                name = null;
            } else if (needRemoveConflict && ("coreApp".equals(name)) && ns == null) {
                name = null;
            }

            if (name != oldName || val != oldVal) {
                changed = true;
                if (!didLogNodeName) {
                    didLogNodeName = true;
                    //LOG(level + "<" + nodeName + ">");
                }
                if (name == null) {
                   // LOG("x   " + level + oldName + "=" + oldVal + " will be removed");
                    return;
                } else {
                    //LOG(level + "    " + oldName + "=" + oldVal);
                    //LOG("=>  " + level + name + "=" + val);
                }
            }

            super.attr(ns, name, resourceId, type, val);
        }

        @Override
        public void end() {
            level = level.length() > 4 ? level.substring(4) : "";
            super.end();
        }
    }
    
    
}
