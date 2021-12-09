package mph.trunksku.apps.dexpro.view;

import android.content.Context;
import android.widget.ListView;

public class IgnoredClassView extends ListView {
    private Context mContext;

    public IgnoredClassView(Context context) {
        super(context);
        this.mContext = context;
    }

   /* public List<IgnoredClass> load(File file) throws ParserConfigurationException, IOException, SAXException {
        return load(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file));
    }

    public List<IgnoredClass> load(Document document) throws SAXException {
        NodeList elementsByTagName = document.getElementsByTagName("manifest");
        if (elementsByTagName.getLength() != 0) {
            return load(elementsByTagName.item(0).getChildNodes());
        }
        throw new SAXException("The manifest node was not found");
    }

    public List<IgnoredClass> load(NodeList nodeList) {
        List<IgnoredClass> arrayList = new ArrayList();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            NamedNodeMap attributes = item.getAttributes();
            if ("uses-permission".equals(item.getNodeName()) && attributes.getLength() == 1) {
                int i2 = 0;
                while (i2 < attributes.getLength()) {
                    Node item2 = attributes.item(i2);
                    if ("android:name".equals(item2.getNodeName())) {
                        String nodeValue = item2.getNodeValue();
                        if (!(TextUtils.isEmpty(nodeValue.trim()) || IgnoredClassAdapter.hasAdded(arrayList, nodeValue))) {
                            arrayList.add(new IgnoredClass(nodeValue));
                        }
                    } else {
                        i2++;
                    }
                }
            }
        }
        return arrayList;
    }*/

    @Override
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }
}

