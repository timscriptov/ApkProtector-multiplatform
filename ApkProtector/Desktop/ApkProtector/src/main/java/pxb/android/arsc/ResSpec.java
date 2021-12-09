package pxb.android.arsc;

public class ResSpec {
    public final int id;
    public int flags;
    public String name;

    ResSpec(int id) {
        super();
        this.id = id;
    }

    void updateName(String s) {
        String name = this.name;
        if (name == null) {
            this.name = s;
        } else if (!s.equals(name)) {
            throw new RuntimeException();
        }
    }
}