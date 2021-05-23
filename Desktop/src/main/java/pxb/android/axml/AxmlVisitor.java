package pxb.android.axml;

/**
 * visitor to visit an axml
 *
 * @author <a href="mailto:pxb1988@gmail.com">Panxiaobo</a>
 */
public class AxmlVisitor extends NodeVisitor {

    AxmlVisitor() {
        super();
    }

    protected AxmlVisitor(NodeVisitor av) {
        super(av);
    }

    /**
     * create a ns
     *
     * @param prefix
     * @param uri
     * @param ln
     */
    public void ns(String prefix, String uri, int ln) {
        if (nv != null && nv instanceof AxmlVisitor) {
            ((AxmlVisitor) nv).ns(prefix, uri, ln);
        }
    }
}