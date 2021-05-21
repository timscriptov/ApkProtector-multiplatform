package com.mcal.apkprotector.utils;

import com.mcal.apkprotector.data.Preferences;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

import pxb.android.axml.Axml;
import pxb.android.axml.Axml.Node;
import pxb.android.axml.Axml.Node.Attr;
import pxb.android.axml.AxmlReader;
import pxb.android.axml.AxmlVisitor;
import pxb.android.axml.AxmlWriter;
import pxb.android.axml.NodeVisitor;

public class ManifestModify {
    private static final String NS_ANDROID = "http://schemas.android.com/apk/res/android";

    private String cApplication;

    public ManifestModify(String cApplication) {
        try {
            this.cApplication = cApplication;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node findNodeByName(Axml axml, String name, String... attributes) {
        return axml != null ? findNodeByName(axml.firsts, name, attributes) : null;
    }

    private static Node findNodeByName(Node node, String name, String... attributes) {
        return node != null ? findNodeByName(node.children, name, attributes) : null;
    }

    private static Node findNodeByName(List<Node> nodes, String name, String... attributes) {
        if (!(nodes == null || nodes.isEmpty())) {
            int pos = name.indexOf(".");
            if (pos != -1) {
                String parentName = name.substring(0, pos);
                String childName = name.substring(pos + 1);
                for (Node node : nodes) {
                    if (parentName.equals(node.name)) {
                        Node childNode = findNodeByName(node, childName, attributes);
                        if (childNode != null) {
                            return childNode;
                        }
                    }
                }
                return null;
            }
            for (Node node2 : nodes) {
                if (name.equals(node2.name)) {
                    if (attributes.length > 0) {
                        int n = 0;
                        while (n < attributes.length) {
                            for (Attr attr : node2.attrs) {
                                if (attributes[n].equals(attr.name) && attributes[n + 1].equals(attr.value)) {
                                    break;
                                }
                            }
                            n += 2;
                        }
                    }
                    return node2;
                }
            }
        }
        return null;
    }

    private static void removeAttribute(Node node, String name) {
        Attr attr = getAttribute(node, name);
        if (attr != null) {
            node.attrs.remove(attr);
        }
    }

    @Nullable
    @Contract(pure = true)
    private static Attr getAttribute(@NotNull Node node, String name) {
        for (Attr attr : node.attrs) {
            if (attr.name.equals(name)) {
                return attr;
            }
        }
        return null;
    }

    private static String getAttributeValue_NotInt(@NotNull Node node, String name) {
        for (Attr attr : node.attrs) {
            if (attr.name.equals(name) && attr.type != 16) {
                if (attr.value != null) {
                    return attr.value.toString();
                }
                return null;
            }
        }
        return null;
    }

    public byte[] modifyAxml(byte[] androidManifestData) throws IOException {
        Axml axml = new Axml();
        new AxmlReader(androidManifestData).accept(getNodeVisitor(axml));
        addMetaData(axml);
        setDexApp(axml);
        AxmlWriter aw = new AxmlWriter();
        axml.accept(aw);
        return aw.toByteArray();
    }

    private void addMetaData(Axml axml) {
        Node applicationNode = findNodeByName(axml, "manifest.application", new String[0]);
        if (applicationNode != null) {
            String name = getAttributeValue_NotInt(applicationNode, "name");
            if (name != null) {
                Node realApplication = new Node();
                realApplication.name = "meta-data";
                realApplication.attr(NS_ANDROID, "name", android.R.attr.name, 3, "RealApplication");
                realApplication.attr(NS_ANDROID, "value", android.R.attr.value, 3, CommonUtils.encryptStrings(name, 2));
                applicationNode.children.add(realApplication);
            }

            Node protectKey = new Node();
            protectKey.name = "meta-data";
            protectKey.attr(NS_ANDROID, "name", android.R.attr.name, 3, "ProtectKey");
            protectKey.attr(NS_ANDROID, "value", android.R.attr.value, 3, CommonUtils.encryptStrings(Preferences.getProtectKeyString(), 2));
            applicationNode.children.add(protectKey);

            Node randomName = new Node();
            randomName.name = "meta-data";
            randomName.attr(NS_ANDROID, "name", android.R.attr.name, 3, "RandomName");
            randomName.attr(NS_ANDROID, "value", android.R.attr.value, 3, CommonUtils.encryptStrings(String.valueOf(Preferences.getRandomName()), 2));
            applicationNode.children.add(randomName);

            Node dexFolderName = new Node();
            dexFolderName.name = "meta-data";
            dexFolderName.attr(NS_ANDROID, "name", android.R.attr.name, 3, "DexFolderName");
            dexFolderName.attr(NS_ANDROID, "value", android.R.attr.value, 3, CommonUtils.encryptStrings(Preferences.getDexFolderName(), 2));
            applicationNode.children.add(dexFolderName);

            Node replaceDexName = new Node();
            replaceDexName.name = "meta-data";
            replaceDexName.attr(NS_ANDROID, "name", android.R.attr.name, 3, "ReplaceDexName");
            replaceDexName.attr(NS_ANDROID, "value", android.R.attr.value, 3, CommonUtils.encryptStrings(Preferences.getReplaceDexName(), 2));
            applicationNode.children.add(replaceDexName);
        }
    }

    private void setDexApp(Axml axml) {
        Node manifestNode = findNodeByName(axml, "manifest", new String[0]);
        if (manifestNode != null) {
            Node applicationNode = findNodeByName(manifestNode, "application", new String[0]);
            if (applicationNode != null) {
                removeAttribute(applicationNode, "name");
                applicationNode.attr(NS_ANDROID, "name", android.R.attr.name, 3, cApplication);
            }
        }
    }

    private AxmlVisitor getNodeVisitor(NodeVisitor original) {
        return new AxmlVisitor(original) {
            public NodeVisitor child(String ns, String name) {
                return new NodeVisitor(super.child(ns, name)) {
                    public NodeVisitor child(String ns, String name) {
                        return name.equals("application") ? new NodeVisitor(super.child(ns, name)) {
                            public NodeVisitor child(String ns, String name) {
                                return name.equals("provider") ? new NodeVisitor(super.child(ns, name)) {
                                    public void attr(String ns, String name, int resourceId, int type, Object obj) {
                                        /*if ("authorities".equals(name)) {
										 String value = null;
										 if (type == 3) {
										 value = (String) obj;
										 } else if (type == 1) {
										 try {
										 value = PackageNameReplacer.this.mResources.getString(((Integer) obj).intValue());
										 Log.i(TAG, "attr; looked up authorities reference; value: " + value + ", id: " + obj);
										 } catch (Exception e) {
										 Log.w(TAG, e);
										 }
										 }
										 if (!(TextUtils.isEmpty(value) || value.contains(PackageNameReplacer.this.mPackageName))) {
										 String newValue = PackageNameReplacer.modifyName(value, PackageNameReplacer.this.mCloneSettings.cloneNumber, PackageNameReplacer.this.mCloneSettings.isLegacyMode());
										 Log.i(TAG, "attr; changing authorities; value: " + value + ", newValue: " + newValue);
										 addAuthoritiesReplacement(value, newValue);
										 super.attr(ns, name, android.R.attr.authorities, 3, newValue);
										 return;
										 }
										 }*/
                                        super.attr(ns, name, resourceId, type, obj);
                                    }
                                } : super.child(ns, name);
                            }
                        } : super.child(ns, name);
                    }
                };
            }
        };
    }
}