package pxb.android.arsc;

public class Value {
    public final int data;
    public final int type;
    public String raw;

    Value(int type, int data, String raw) {
        super();
        this.type = type;
        this.data = data;
        this.raw = raw;
    }

    public String toString() {
        if (type == 0x03) {
            return raw;
        }
        return String.format("{t=0x%02x d=0x%08x}", type, data);
    }
}