package pxb.android.arsc;

import java.util.Map;
import java.util.TreeMap;

public class Config {
    public final byte[] id;
    final int entryCount;
    public Map<Integer, ResEntry> resources = new TreeMap<Integer, ResEntry>();
    /* package */ int wChunkSize;
    /* package */ int wEntryStart;
    /* package */ int wPosition;

    Config(byte[] id, int entryCount) {
        super();
        this.id = id;
        this.entryCount = entryCount;
    }
}