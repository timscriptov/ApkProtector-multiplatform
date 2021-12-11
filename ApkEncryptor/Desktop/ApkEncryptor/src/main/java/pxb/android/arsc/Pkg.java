package pxb.android.arsc;

import java.util.TreeMap;

public class Pkg {
    public final int id;
    public String name;
    public TreeMap<Integer, Type> types = new TreeMap<Integer, Type>();

    Pkg(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    Type getType(int tid, String name, int entrySize) {
        Type type = types.get(tid);
        if (type != null) {
            if (name != null) {
                if (type.name == null) {
                    type.name = name;
                } else if (!name.endsWith(type.name)) {
                    throw new RuntimeException();
                }
                if (type.specs.length != entrySize) {
                    throw new RuntimeException();
                }
            }
        } else {
            type = new Type();
            type.id = tid;
            type.name = name;
            type.specs = new ResSpec[entrySize];
            types.put(tid, type);
        }
        return type;
    }
}