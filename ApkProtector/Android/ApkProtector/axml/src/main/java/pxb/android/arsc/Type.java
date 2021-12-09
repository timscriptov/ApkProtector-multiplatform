package pxb.android.arsc;

import java.util.ArrayList;
import java.util.List;

public class Type {
    public int id;
    public String name;
    List<Config> configs = new ArrayList<Config>();
    ResSpec[] specs;
    /* package */ int wPosition;

    void addConfig(Config config) {
        if (config.entryCount != specs.length) {
            throw new RuntimeException();
        }
        configs.add(config);
    }

    ResSpec getSpec(int resId) {
        ResSpec res = specs[resId];
        if (res == null) {
            res = new ResSpec(resId);
            specs[resId] = res;
        }
        return res;
    }
}