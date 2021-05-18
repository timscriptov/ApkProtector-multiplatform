package com.mcal.apkprotector.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

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
    private static final String TAG = ManifestModify.class.getSimpleName();
    private static final String NS_ANDROID = "http://schemas.android.com/apk/res/android";

    private String cApplication;
    private Context context;

    public ManifestModify(Context c, String cApplication) {
        try {
            this.context = c;
            this.cApplication = cApplication;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private static List<Node> findLauncherActivityNodes(Axml axml) {
        List<Node> launcherActivityNodes = new ArrayList();
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
                        if (b) {
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
    @Deprecated
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
        //setAppLabel(axml);
        //addPermission(axml);
        //removeActivity(axml);
        addMetaData(axml);
        setDexApp(axml);

        makeDebuggable(axml);
        AxmlWriter aw = new AxmlWriter();
        axml.accept(aw);
        return aw.toByteArray();
    }

    private void addMetaData(Axml axml) {
        Node applicationNode = findNodeByName(axml, "manifest.application", new String[0]);
        if (applicationNode != null) {
            String name = getAttributeValue_NotInt(applicationNode, "name");
            if (name != null) {
                Node node = new Node();
                node.name = "meta-data";
                node.attr(NS_ANDROID, "name", android.R.attr.name, 3, "RealApplication");
                node.attr(NS_ANDROID, "value", android.R.attr.value, 3, CommonUtils.encryptStrings(name, 2));
                applicationNode.children.add(node);
            }

            Node protectKey = new Node();
            protectKey.name = "meta-data";
            protectKey.attr(NS_ANDROID, "name", android.R.attr.name, 3, "ProtectKey");
            protectKey.attr(NS_ANDROID, "value", android.R.attr.value, 3, CommonUtils.encryptStrings(Preferences.getProtectKeyString(), 2));
            applicationNode.children.add(protectKey);
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