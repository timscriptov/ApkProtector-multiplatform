package com.mcal.apkprotector.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;

import com.mcal.apkprotector.data.Preferences;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
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
    private Resources mResources;

    public ManifestModify(Context context, String mPackageName, String cApplication) {
        try {
            this.cApplication = cApplication;
            PackageManager pm = context.getPackageManager();
            mResources = pm.getResourcesForApplication(mPackageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private static List<Node> findLauncherActivityNodes(Axml axml) {
        List<Node> launcherActivityNodes = new ArrayList<>();
        Node applicationNode = findNodeByName(axml, "manifest.application", new String[0]);
        if (applicationNode != null) {
            addLauncherActivityNodes(findNodesByName(applicationNode, "activity"), launcherActivityNodes);
            addLauncherActivityNodes(findNodesByName(applicationNode, "activity-alias"), launcherActivityNodes);
        }
        return launcherActivityNodes;
    }

    private static void addLauncherActivityNodes(@NotNull List<Node> activityNodes, List<Node> launcherActivityNodes) {
        for (Node activityNode : activityNodes) {
            if (findNodeByName(activityNode, "intent-filter.category", "name", "android.intent.category.LAUNCHER") != null) {
                launcherActivityNodes.add(activityNode);
            }
        }
    }

    public static Node cloneNode(@NotNull Node node) {
        try {
            AxmlWriter aw = new AxmlWriter();
            node.accept(aw);
            AxmlReader ar = new AxmlReader(aw.toByteArray());
            Axml axml = new Axml();
            ar.accept(axml);
            return (Node) axml.firsts.get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Contract("null, _, _ -> null")
    private static Node findNodeByName(Axml axml, String name, String... attributes) {
        return axml != null ? findNodeByName(axml.firsts, name, attributes) : null;
    }

    @Contract("null, _, _ -> null")
    private static Node findNodeByName(Node node, String name, String... attributes) {
        return node != null ? findNodeByName(node.children, name, attributes) : null;
    }

    @Contract("null, _, _ -> null")
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
                        boolean b = false;
                        int n = 0;
                        while (n < attributes.length) {
                            for (Attr attr : node2.attrs) {
                                if (attributes[n].equals(attr.name) && attributes[n + 1].equals(attr.value)) {
                                    b = true;
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

    @NotNull
    private static List<Node> findNodesByName(@NotNull Node node, String name) {
        List<Node> nodes = new ArrayList<>();
        for (Node child : node.children) {
            if (name.equals(child.name)) {
                nodes.add(child);
            }
        }
        return nodes;
    }

    @NotNull
    private static List<Node> findNodesByNames(Node node, @NotNull String[] names) {
        List<Node> nodes = new ArrayList<>();
        for (String name : names) {
            nodes.addAll(findNodesByName(node, name));
        }
        return nodes;
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

    @Nullable
    private static String getAttributeValue(@NotNull Node node, String name) {
        for (Attr attr : node.attrs) {
            if (attr.name.equals(name)) {
                if (attr.value != null) {
                    return attr.value.toString();
                }
                return null;
            }
        }
        return null;
    }

    @Nullable
    @Contract(pure = true)
    @Deprecated
    private static Attr getAttribute_NotInt(@NotNull Node node, String name) {
        for (Attr attr : node.attrs) {
            if (attr.name.equals(name) && attr.type != 16) {
                return attr;
            }
        }
        return null;
    }

    @Nullable
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

    public static String toHexString(byte[] ba) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ba.length; i++)
            str.append(String.format("%x", ba[i]));
        return str.toString();
    }

    public static String fromHexString(String hex) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            str.append((char) Integer.parseInt(hex.substring(i, i + 2), 16));
        }
        return str.toString();
    }

    public byte[] modifyAxml(byte[] androidManifestData) throws IOException {
        Axml axml = new Axml();
        new AxmlReader(androidManifestData).accept(getNodeVisitor(axml));
        //addMetaData(axml);
        setDexApp(axml);
        AxmlWriter aw = new AxmlWriter();
        axml.accept(aw);
        return aw.toByteArray();
    }

    private void setAppLabel(Axml axml) {
        Node applicationNode = findNodeByName(axml, "manifest.application", new String[0]);
        if (applicationNode != null) {
            setLabel(applicationNode, "ApkProtector");
        }
    }

    @Contract("null, _ -> fail")
    private void setLabel(Node node, String label) {
        if (node == null) {
            throw new IllegalArgumentException("Node not provided");
        } else if (TextUtils.isEmpty(label)) {
            throw new IllegalArgumentException("Label not provided");
        } else {
            Attr attr = getAttribute_NotInt(node, "label");
            if (attr != null) {
                String oldValue = null;
                if (attr.type == 1) {
                    try {
                        oldValue = mResources.getString(((Integer) attr.value).intValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (!label.equals(oldValue)) {
                    node.attrs.remove(attr);
                } else {
                    return;
                }
            }
            node.attr(NS_ANDROID, "label", android.R.attr.label, 3, label);
        }
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
            //randomName.attr(NS_ANDROID, "value", android.R.attr.value, 3, CommonUtils.encryptStrings(String.valueOf(Preferences.getRandomName()), 2));
            applicationNode.children.add(randomName);

            Node dexFolderName = new Node();
            dexFolderName.name = "meta-data";
            dexFolderName.attr(NS_ANDROID, "name", android.R.attr.name, 3, "DexFolderName");
            //dexFolderName.attr(NS_ANDROID, "value", android.R.attr.value, 3, CommonUtils.encryptStrings(Preferences.getDexFolderName(), 2));
            applicationNode.children.add(dexFolderName);

            Node replaceDexName = new Node();
            replaceDexName.name = "meta-data";
            replaceDexName.attr(NS_ANDROID, "name", android.R.attr.name, 3, "ReplaceDexName");
            //replaceDexName.attr(NS_ANDROID, "value", android.R.attr.value, 3, CommonUtils.encryptStrings(Preferences.getReplaceDexName(), 2));
            applicationNode.children.add(replaceDexName);
        }
    }

    private void setDexApp(Axml axml) {
        Node manifestNode = findNodeByName(axml, "manifest", new String[0]);
        if (manifestNode != null) {

            Node applicationNode = findNodeByName(manifestNode, "application", new String[0]);
            Node sdkNode = findNodeByName(manifestNode, "uses-sdk", new String[0]);

            if (applicationNode != null) {
                // application
                removeAttribute(applicationNode, "name");
                applicationNode.attr(NS_ANDROID, Preferences.isProtectManifest() ? CommonUtils.encryptStrings("name", 2) : "name", android.R.attr.name, 3, cApplication);

                /*Preferences.setTempAxml(getAttributeValue_NotInt(applicationNode, "icon"));
                removeAttribute(applicationNode, "icon");
                applicationNode.attr(NS_ANDROID, CommonUtils.encryptStrings("icon", 2), android.R.attr.label, 1, Integer.valueOf(Preferences.getTempAxml()));

                Preferences.setTempAxml(getAttributeValue_NotInt(applicationNode, "theme"));
                removeAttribute(applicationNode, "theme");
                applicationNode.attr(NS_ANDROID, CommonUtils.encryptStrings("theme", 2), android.R.attr.theme, 1, Integer.valueOf(Preferences.getTempAxml()));
                                                                                                                                      
                Preferences.setTempAxml(getAttributeValue_NotInt(applicationNode, "label"));
                removeAttribute(applicationNode, "label");
                applicationNode.attr(NS_ANDROID, CommonUtils.encryptStrings("label", 2), android.R.attr.label, 1, Integer.valueOf(Preferences.getTempAxml()));

                // manifest
                Preferences.setTempAxml(getAttributeValue_NotInt(manifestNode, "package"));
                removeAttribute(manifestNode, "package");
                manifestNode.attr(NS_ANDROID, CommonUtils.encryptStrings("package", 2), android.R.attr.packageNames, 1, Preferences.getTempAxml());

                Preferences.setTempAxml(getAttributeValue_NotInt(manifestNode, "versionCode"));
                removeAttribute(manifestNode, "versionCode");
                manifestNode.attr(NS_ANDROID, CommonUtils.encryptStrings("versionCode", 2), android.R.attr.versionCode, 2, Preferences.getTempAxml());

                Preferences.setTempAxml(getAttributeValue_NotInt(manifestNode, "versionName"));
                removeAttribute(manifestNode, "versionName");
                manifestNode.attr(NS_ANDROID, CommonUtils.encryptStrings("versionName", 2), android.R.attr.versionName, 2, Preferences.getTempAxml());


                // uses-sdk
                Preferences.setTempAxml(getAttributeValue_NotInt(sdkNode, "minSdkVersion"));
                removeAttribute(sdkNode, "minSdkVersion");
                sdkNode.attr(NS_ANDROID, CommonUtils.encryptStrings("minSdkVersion", 2), android.R.attr.minSdkVersion, 3, Preferences.getTempAxml());

                Preferences.setTempAxml(getAttributeValue_NotInt(sdkNode, "targetSdkVersion"));
                removeAttribute(sdkNode, "targetSdkVersion");
                sdkNode.attr(NS_ANDROID, CommonUtils.encryptStrings("targetSdkVersion", 2), android.R.attr.targetSdkVersion, 3, Preferences.getTempAxml());
                */
            }
        }
    }

    private void makeDebuggable(Axml axml) {
        Node manifestNode = findNodeByName(axml, "manifest", new String[0]);
        if (manifestNode != null) {
            Node applicationNode = findNodeByName(manifestNode, "application", new String[0]);
            if (applicationNode != null) {
                removeAttribute(applicationNode, "debuggable");
                applicationNode.attr(NS_ANDROID, "debuggable", android.R.attr.debuggable, 18, Integer.valueOf(0));

            }
        }
    }

    private void addPermission(Axml axml) {
        Node manifestNode = findNodeByName(axml, "manifest", new String[0]);
        if (manifestNode != null) {
            Node usesPermissionNode = new Node();
            usesPermissionNode.name = "uses-permission";
            usesPermissionNode.attr(NS_ANDROID, "name", 16842755, 3, "android.permission.SYSTEM_ALERT_WINDOW");
            manifestNode.children.add(usesPermissionNode);
        }
    }

    private void removePermission(Axml axml) {
        Node manifestNode = findNodeByName(axml, "manifest", new String[0]);
        if (manifestNode != null) {
            Node usesPermissionNode = new Node();
            usesPermissionNode.name = "uses-permission";
            usesPermissionNode.attr(NS_ANDROID, "name", 16842755, 3, "android.permission.SYSTEM_ALERT_WINDOW");
            manifestNode.children.remove(usesPermissionNode);
        }
    }

    private void removeActivity(Axml axml) {
        Node applicationNode = findNodeByName(axml, "manifest.application", new String[0]);
        if (applicationNode != null) {
            Node myActivityNode = new Node();
            myActivityNode.name = "activity";
            myActivityNode.attr(NS_ANDROID, "name", android.R.attr.name, 3, "com.google.android.gms.ads.purchase.InAppPurchaseActivity");
            applicationNode.children.remove(myActivityNode);
        }
    }

    private void splashAct(Axml axml) {
        Node manifestNode = findNodeByName(axml, "manifest", new String[0]);
        if (manifestNode != null) {
            Node applicationNode = findNodeByName(manifestNode, "application", new String[0]);
            if (applicationNode != null) {
                List<Node> launcherActivityNodes = findLauncherActivityNodes(axml);
                int i = 0;
                while (i < launcherActivityNodes.size()) {
                    Attr attr;
                    Node launcherActivityNode = (Node) launcherActivityNodes.get(i);
                    String originalActivityName = getAttributeValue_NotInt(launcherActivityNode, "name");
                    Node newLauncherActivityNode = cloneNode(launcherActivityNode);
                    if ("activity-alias".equals(newLauncherActivityNode.name)) {
                        newLauncherActivityNode.name = "activity";
                        attr = getAttribute_NotInt(newLauncherActivityNode, "targetActivity");
                        if (attr != null) {
                            originalActivityName = attr.value.toString();
                            newLauncherActivityNode.attrs.remove(attr);
                        }
                    }
                    attr = getAttribute_NotInt(newLauncherActivityNode, "name");
                    if (attr != null) {
                        newLauncherActivityNode.attrs.remove(attr);
                    }
                    newLauncherActivityNode.attr(NS_ANDROID, "name", android.R.attr.name, 3, "com.mcal.apkprotector.activities.SplashActivity" + (i > 0 ? Integer.toString(i + 1) : ""));
                    for (Node intentFilterNode : findNodesByName(launcherActivityNode, "intent-filter")) {
                        launcherActivityNode.children.remove(intentFilterNode);
                    }
                    Node metaDataNode = new Node();
                    metaDataNode.name = "meta-data";
                    metaDataNode.attr(NS_ANDROID, "name", android.R.attr.name, 3, "RealActivity");
                    metaDataNode.attr(NS_ANDROID, "value", android.R.attr.value, 3, originalActivityName);
                    applicationNode.children.add(metaDataNode);
					/*Node myActivityNode = new Node();
					 myActivityNode.name = "activity";
					 myActivityNode.attr(NS_ANDROID, "name", android.R.attr.name, 3, originalActivityName);
					 applicationNode.children.add(myActivityNode);*/

                    applicationNode.children.add(newLauncherActivityNode);
                    i++;
                }
				/*Node usesPermissionNode = new Node();
				 usesPermissionNode.name = "uses-permission";
				 usesPermissionNode.attr(NS_ANDROID, "name", 16842755, 3, "android.permission.SYSTEM_ALERT_WINDOW");
				 manifestNode.children.add(usesPermissionNode);*/
            }
        }
    }

    @NotNull
    @Contract("_ -> new")
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