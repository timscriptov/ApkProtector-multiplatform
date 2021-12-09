package pxb.android.arsc;

public class ResEntry {
    public final int flag;

    final ResSpec spec;
    /**
     * {@link BagValue} or {@link Value}
     */
    public Object value;

    /* package */ int wOffset;

    ResEntry(int flag, ResSpec spec) {
        super();
        this.flag = flag;
        this.spec = spec;
    }
}