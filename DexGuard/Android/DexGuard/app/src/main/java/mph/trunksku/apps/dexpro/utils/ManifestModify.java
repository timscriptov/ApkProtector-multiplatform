package mph.trunksku.apps.dexpro.utils;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mph.trunksku.apps.dexpro.MyApp;
import mph.trunksku.apps.dexpro.logger.Log;
import pxb.android.axml.Axml;
import pxb.android.axml.Axml.Node;
import pxb.android.axml.Axml.Node.Attr;
import pxb.android.axml.AxmlReader;
import pxb.android.axml.AxmlVisitor;
import pxb.android.axml.AxmlWriter;
import pxb.android.axml.NodeVisitor;

public class ManifestModify
{
	private static final String TAG = ManifestModify.class.getSimpleName();
	private static final String NS_ANDROID = "http://schemas.android.com/apk/res/android";
	private Resources mResources;

    private String data;

    private boolean splash = false;

    private String cApplication;

    private Context context;

	public ManifestModify(Context c, String mPackageName, String cApplication, String data, boolean splash)
	{
		try
		{
            this.context = c;
            this.splash = splash;
            this.cApplication = cApplication;
            this.data = data;
			PackageManager pm = c.getPackageManager();
			mResources = pm.getResourcesForApplication(mPackageName);
		}
		catch (Exception e)
		{

		}
	}

    public String getBuildSerial()
    {
        String buildSerial = Build.SERIAL;
        if (!"unknown".equals(buildSerial))
        {
            return buildSerial;
        }
        try
        {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        }
        catch (Exception e)
        {
            //Log.w(TAG, e);
            return buildSerial;
        }
    }
    
	public byte[] modifyAxml(byte[] androidManifestData) throws IOException
	{
		Axml axml = new Axml();
        new AxmlReader(androidManifestData).accept(getNodeVisitor(axml));
		//setAppLabel(axml);
		//addPermission(axml);
        //removeActivity(axml);
		addMetaData(axml);
		setDexApp(axml);
		if(splash){
            splashAct(axml);
        }
		makeDebuggable(axml);
		AxmlWriter aw = new AxmlWriter();
        axml.accept(aw);
        return aw.toByteArray();
	}

	private void setAppLabel(Axml axml)
	{
		Node applicationNode = findNodeByName(axml, "manifest.application", new String[0]);
		if (applicationNode != null)
		{
			setLabel(applicationNode, "DexProtect");
		}
    }

	private void setLabel(Node node, String label)
	{
        if (node == null)
		{
            throw new IllegalArgumentException("Node not provided");
        }
		else if (TextUtils.isEmpty(label))
		{
            throw new IllegalArgumentException("Label not provided");
        }
		else
		{
            Attr attr = getAttribute_NotInt(node, "label");
            if (attr != null)
			{
                String oldValue = null;
                if (attr.type == 1)
				{
                    try
					{
                        oldValue = mResources.getString(((Integer) attr.value).intValue());
                    }
					catch (Exception e)
					{
                        Log.w(TAG, e);
                    }
                }
                if (!label.equals(oldValue))
				{
                    node.attrs.remove(attr);
                }
				else
				{
                    return;
                }
            }
            node.attr(NS_ANDROID, "label", android.R.attr.label, 3, label);
        }
    }

	private void addMetaData(Axml axml)
	{
        Node applicationNode = findNodeByName(axml, "manifest.application", new String[0]);
        if (applicationNode != null)
		{
			String name = getAttributeValue_NotInt(applicationNode, "name");
            if (name != null)
			{
				Node node = new Node();
				node.name = "meta-data";
				node.attr(NS_ANDROID, "name", android.R.attr.name, 3, "dexpro.Application");
				node.attr(NS_ANDROID, "value", android.R.attr.value, 3, a(name));
				applicationNode.children.add(node);
                
			}
            Node node2 = new Node();
            node2.name = "meta-data";
            node2.attr(NS_ANDROID, "name", android.R.attr.name, 3, "dexpro.Secure");
            node2.attr(NS_ANDROID, "value", android.R.attr.value, 3, a(data));
            applicationNode.children.add(node2);
            /*Node node3 = new Node();
            node3.name = "meta-data";
            node3.attr(NS_ANDROID, "name", android.R.attr.name, 3, "dexpro.Support");
            String pass = MyApp.getDefSharedPreferences().getString("DexKey", "dexprotect");
            if(pass.isEmpty()){
                node3.attr(NS_ANDROID, "value", android.R.attr.value, 3, a(Utils.sealing("dexprotect")));
            }else{
                node3.attr(NS_ANDROID, "value", android.R.attr.value, 3, a(Utils.sealing(pass)));
            }
            applicationNode.children.add(node3);*/
			Node node4 = new Node();
            node4.name = "meta-data";
            node4.attr(NS_ANDROID, "name", android.R.attr.name, 3, "dexpro.Suffix");
            node4.attr(NS_ANDROID, "value", android.R.attr.value, 3, a(".lua.mph"));
            applicationNode.children.add(node4);
            /*Node myActivityNode = new Node();
            myActivityNode.name = "activity";
            myActivityNode.attr(NS_ANDROID, "name", android.R.attr.name, 3, "android.support.dexpro.CopyClip");
            applicationNode.children.add(myActivityNode);*/
        }
    }
    
    

	private void setDexApp(Axml axml)
	{
		Node manifestNode = findNodeByName(axml, "manifest", new String[0]);
		if (manifestNode != null)
		{
			Node applicationNode = findNodeByName(manifestNode, "application", new String[0]);
			if (applicationNode != null)
			{
				removeAttribute(applicationNode, "name");
				applicationNode.attr(NS_ANDROID, "name", android.R.attr.name, 3, cApplication);

			}
		}
    }

	private void makeDebuggable(Axml axml)
	{
		Node manifestNode = findNodeByName(axml, "manifest", new String[0]);
		if (manifestNode != null)
		{
			Node applicationNode = findNodeByName(manifestNode, "application", new String[0]);
			if (applicationNode != null)
			{
				removeAttribute(applicationNode, "debuggable");
				applicationNode.attr(NS_ANDROID, "debuggable", android.R.attr.debuggable, 18, Integer.valueOf(0));

			}
		}

    }

	private void addPermission(Axml axml)
	{
		Node manifestNode = findNodeByName(axml, "manifest", new String[0]);
		if (manifestNode != null)
		{
			Node usesPermissionNode = new Node();
			usesPermissionNode.name = "uses-permission";
			usesPermissionNode.attr(NS_ANDROID, "name", 16842755, 3, "android.permission.SYSTEM_ALERT_WINDOW");
			manifestNode.children.add(usesPermissionNode);
		}
	}
    
    private void removePermission(Axml axml)
    {
        Node manifestNode = findNodeByName(axml, "manifest", new String[0]);
        if (manifestNode != null)
        {
            Node usesPermissionNode = new Node();
            usesPermissionNode.name = "uses-permission";
            usesPermissionNode.attr(NS_ANDROID, "name", 16842755, 3, "android.permission.SYSTEM_ALERT_WINDOW");
            manifestNode.children.remove(usesPermissionNode);
        }
	}
    
    private void removeActivity(Axml axml)
    {
        Node applicationNode = findNodeByName(axml, "manifest.application", new String[0]);
        if (applicationNode != null)
		{
            Node myActivityNode = new Node();
            myActivityNode.name = "activity";
            myActivityNode.attr(NS_ANDROID, "name", android.R.attr.name, 3, "com.google.android.gms.ads.purchase.InAppPurchaseActivity");
            applicationNode.children.remove(myActivityNode);
        }
	}
    
	private static List<Node> findLauncherActivityNodes(Axml axml)
	{
        List<Node> launcherActivityNodes = new ArrayList();
        Node applicationNode = findNodeByName(axml, "manifest.application", new String[0]);
        if (applicationNode != null)
		{
            addLauncherActivityNodes(findNodesByName(applicationNode, "activity"), launcherActivityNodes);
            addLauncherActivityNodes(findNodesByName(applicationNode, "activity-alias"), launcherActivityNodes);
        }
        return launcherActivityNodes;
    }

    private static void addLauncherActivityNodes(List<Node> activityNodes, List<Node> launcherActivityNodes)
	{
        for (Node activityNode : activityNodes)
		{
            if (findNodeByName(activityNode, "intent-filter.category", "name", "android.intent.category.LAUNCHER") != null)
			{
                launcherActivityNodes.add(activityNode);
            }
        }
    }


	private void splashAct(Axml axml)
	{
		Node manifestNode = findNodeByName(axml, "manifest", new String[0]);
		if (manifestNode != null)
		{
			Node applicationNode = findNodeByName(manifestNode, "application", new String[0]);
			if (applicationNode != null)
			{
				List<Node> launcherActivityNodes = findLauncherActivityNodes(axml);
				int i = 0;
				while (i < launcherActivityNodes.size())
				{
					Attr attr;
					Node launcherActivityNode = (Node) launcherActivityNodes.get(i);
					String originalActivityName = getAttributeValue_NotInt(launcherActivityNode, "name");
					Node newLauncherActivityNode = cloneNode(launcherActivityNode);
					if ("activity-alias".equals(newLauncherActivityNode.name))
					{
						newLauncherActivityNode.name = "activity";
						attr = getAttribute_NotInt(newLauncherActivityNode, "targetActivity");
						if (attr != null)
						{
							originalActivityName = attr.value.toString();
							newLauncherActivityNode.attrs.remove(attr);
						}
					}
					attr = getAttribute_NotInt(newLauncherActivityNode, "name");
					if (attr != null)
					{
						newLauncherActivityNode.attrs.remove(attr);
					}
					newLauncherActivityNode.attr(NS_ANDROID, "name", android.R.attr.name, 3, "xyz.dexpro.engine.c.a" + (i > 0 ? Integer.toString(i + 1) : ""));
					for (Node intentFilterNode : findNodesByName(launcherActivityNode, "intent-filter"))
					{
						launcherActivityNode.children.remove(intentFilterNode);
					}
					Node metaDataNode = new Node();
					metaDataNode.name = "meta-data";
					metaDataNode.attr(NS_ANDROID, "name", android.R.attr.name, 3, "dexpro.Real");
					metaDataNode.attr(NS_ANDROID, "value", android.R.attr.value, 3, originalActivityName);
					applicationNode.children.add(metaDataNode);
					Node myActivityNode = new Node();
					myActivityNode.name = "activity";
					myActivityNode.attr(NS_ANDROID, "name", android.R.attr.name, 3, originalActivityName);
					applicationNode.children.add(myActivityNode);

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


	public static Node cloneNode(Node node)
	{
        try
		{
            AxmlWriter aw = new AxmlWriter();
            node.accept(aw);
            AxmlReader ar = new AxmlReader(aw.toByteArray());
            Axml axml = new Axml();
            ar.accept(axml);
            return (Node) axml.firsts.get(0);
        }
		catch (IOException e)
		{
            throw new RuntimeException(e);
        }
    }

	private AxmlVisitor getNodeVisitor(NodeVisitor original)
	{
        return new AxmlVisitor(original) {
            public NodeVisitor child(String ns, String name)
			{
                return new NodeVisitor(super.child(ns, name)) {
                    public NodeVisitor child(String ns, String name)
					{
                        return name.equals("application") ? new NodeVisitor(super.child(ns, name)) {
                            public NodeVisitor child(String ns, String name)
							{
                                return name.equals("provider") ? new NodeVisitor(super.child(ns, name)) {
                                    public void attr(String ns, String name, int resourceId, int type, Object obj)
									{
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

    private static Node findNodeByName(Axml axml, String name, String... attributes)
	{
        return axml != null ? findNodeByName(axml.firsts, name, attributes) : null;
    }

    private static Node findNodeByName(Node node, String name, String... attributes)
	{
        return node != null ? findNodeByName(node.children, name, attributes) : null;
    }

    private static Node findNodeByName(List<Node> nodes, String name, String... attributes)
	{
        if (!(nodes == null || nodes.isEmpty()))
		{
            int pos = name.indexOf(".");
            if (pos != -1)
			{
                String parentName = name.substring(0, pos);
                String childName = name.substring(pos + 1);
                for (Node node : nodes)
				{
                    if (parentName.equals(node.name))
					{
                        Node childNode = findNodeByName(node, childName, attributes);
                        if (childNode != null)
						{
                            return childNode;
                        }
                    }
                }
                return null;
            }
            for (Node node2 : nodes)
			{
                if (name.equals(node2.name))
				{
                    if (attributes.length > 0)
					{
                        boolean b = false;
                        int n = 0;
                        while (n < attributes.length)
						{
                            for (Attr attr : node2.attrs)
							{
                                if (attributes[n].equals(attr.name) && attributes[n + 1].equals(attr.value))
								{
                                    b = true;
                                    break;
                                }
                            }
                            n += 2;
                        }
                        if (b)
						{
                        }
                    }
                    return node2;
                }
            }
        }
        return null;
    }

    private static List<Node> findNodesByName(Node node, String name)
	{
        List<Node> nodes = new ArrayList();
        for (Node child : node.children)
		{
            if (name.equals(child.name))
			{
                nodes.add(child);
            }
        }
        return nodes;
    }

    private static List<Node> findNodesByNames(Node node, String[] names)
	{
        List<Node> nodes = new ArrayList();
        for (String name : names)
		{
            nodes.addAll(findNodesByName(node, name));
        }
        return nodes;
    }

    private static void removeAttribute(Node node, String name)
	{
        Attr attr = getAttribute(node, name);
        if (attr != null)
		{
            node.attrs.remove(attr);
        }
    }

    private static Attr getAttribute(Node node, String name)
	{
        for (Attr attr : node.attrs)
		{
            if (attr.name.equals(name))
			{
                return attr;
            }
        }
        return null;
    }

    private static String getAttributeValue(Node node, String name)
	{
        for (Attr attr : node.attrs)
		{
            if (attr.name.equals(name))
			{
                if (attr.value != null)
				{
                    return attr.value.toString();
                }
                return null;
            }
        }
        return null;
    }

    @Deprecated
    private static Attr getAttribute_NotInt(Node node, String name)
	{
        for (Attr attr : node.attrs)
		{
            if (attr.name.equals(name) && attr.type != 16)
			{
                return attr;
            }
        }
        return null;
    }

    @Deprecated
    private static String getAttributeValue_NotInt(Node node, String name)
	{
        for (Attr attr : node.attrs)
		{
            if (attr.name.equals(name) && attr.type != 16)
			{
                if (attr.value != null)
				{
                    return attr.value.toString();
                }
                return null;
            }
        }
        return null;
    }
	
	
	
	public static String a(String str) {
		boolean qwerty21345hjdnjd = false;

		while (qwerty21345hjdnjd) {
            switch (1) {
                case 1:
                    while (qwerty21345hjdnjd) {
                        try {
                            Throwable throwable=new Throwable();
                            Throwable cause = throwable.getCause();
                        } catch (NullPointerException e) {
                        } finally {
                        }
                    }
                    break;
            }
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        char[] toCharArray = str.toCharArray();
        for (int i = 0; i < toCharArray.length; i++) {
            toCharArray[i] = (char) (toCharArray[i] ^ 16);
        }
        return String.valueOf(toCharArray);
    }
	
    /*private String a(String a)
    {
        char[] ag = new char[]{'\u6033'};
        try
        {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < a.length(); i++)
            {
                output.append((char) (a.charAt(i) ^ ag[i % ag.length]));
            }
            return output.toString();
        }
        catch (Exception ex)
        {
            return "";
        }
	}*/
}
