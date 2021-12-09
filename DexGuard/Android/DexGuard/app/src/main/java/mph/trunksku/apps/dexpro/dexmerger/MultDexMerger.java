package mph.trunksku.apps.dexpro.dexmerger;

import com.android.dexj.Dex;
import com.android.dexj.DexIndexOverflowException;
import com.android.dx.merge.CollisionPolicy;
import com.android.dx.merge.DexMerger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultDexMerger {
    private static final int COMPACT_WASTE_THRESHOLD = 1048576;
    private final DxContext mContext;
    private final Parameter mParameter;

    public static class Builder {
        private CollisionPolicy mCollisionPolicy;
        private int mCompactWasteThreshold;
        private List<Dex> mDexs;
        private final DxContext mDxContext;
        private int mMaxIndex;

        public Builder() {
            this(new DxContext());
        }

        public Builder(DxContext dxContext) {
            this.mDexs = new ArrayList();
            this.mCollisionPolicy = CollisionPolicy.KEEP_FIRST;
            this.mCompactWasteThreshold = MultDexMerger.COMPACT_WASTE_THRESHOLD;
            this.mMaxIndex = 65535;
            this.mDxContext = dxContext;
        }

        public void setDexs(List<Dex> list) {
            this.mDexs = list;
        }

        public List<Dex> getDexs() {
            return this.mDexs;
        }

        public Builder add(Dex dex) {
            if (dex != null) {
                this.mDexs.add(dex);
            }
            return this;
        }

        public Builder add(Dex... dexArr) {
            int i = 0;
            while (dexArr != null && i < dexArr.length) {
                add(dexArr[i]);
                i++;
            }
            return this;
        }

        public Builder add(File file) throws IOException {
            return add(new Dex(file));
        }

        public Builder add(File... fileArr) throws IOException {
            int i = 0;
            while (fileArr != null && i < fileArr.length) {
                add(fileArr[i]);
                i++;
            }
            return this;
        }

        public Builder add(String str) throws IOException {
            return add(new File(str));
        }

        public Builder add(String... strArr) throws IOException {
            int i = 0;
            while (strArr != null && i < strArr.length) {
                add(strArr[i]);
                i++;
            }
            return this;
        }

        public Builder setCollisionPolicy(CollisionPolicy collisionPolicy) {
            if (collisionPolicy == null) {
                throw new IllegalArgumentException("collisionPolicy is null");
            }
            this.mCollisionPolicy = collisionPolicy;
            return this;
        }

        public CollisionPolicy getCollisionPolicy() {
            return this.mCollisionPolicy;
        }

        public Builder setCompactWasteThreshold(int i) {
            this.mCompactWasteThreshold = i;
            return this;
        }

        public void setMaxIndex(int i) {
            if (i < 0 || i > 16777215) {
                throw new DexIndexOverflowException(new StringBuffer().append("type ID not in [0, 0xffff]: ").append(i).toString());
            }
            this.mMaxIndex = i;
        }

        public Builder clear() {
            this.mDexs.clear();
            return this;
        }

        public Builder builder() {
            return this;
        }

        public MultDexMerger create() {
            List arrayList = new ArrayList();
            add(arrayList, 0);
            print(arrayList);
            Parameter parameter = new Parameter();
            parameter.setGroupDexs(arrayList);
            parameter.setCollisionPolicy(this.mCollisionPolicy);
            parameter.setCompactWasteThreshold(this.mCompactWasteThreshold);
            return new MultDexMerger(this.mDxContext, parameter);
        }

        private void add(List<List<Dex>> list, int i) {
            if (list != null && i >= 0 && i < this.mDexs.size()) {
                List arrayList = new ArrayList();
                int i2 = i;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = i;
                while (i2 < this.mDexs.size()) {
                    int i8;
                    Dex dex = (Dex) this.mDexs.get(i2);
                    i6 += dex.fieldIds().size();
                    i5 += dex.methodIds().size();
                    i4 += dex.typeIds().size();
                    i3 += dex.protoIds().size();
                    if (isIndexOverflow(i6, i5, i4, i3)) {
                        i8 = i7;
                    } else {
                        i7++;
                        arrayList.add(dex);
                        i8 = i7;
                    }
                    i2++;
                    i7 = i8;
                }
                list.add(arrayList);
                add(list, i7);
            }
        }

        private boolean isIndexOverflow(int... iArr) {
            int i = 0;
            while (iArr != null && i < iArr.length) {
                if (iArr[i] > this.mMaxIndex) {
                    return true;
                }
                i++;
            }
            return false;
        }

        private void print(List<List<Dex>> list) {
            this.mDxContext.out.println(new StringBuffer().append("group size:").append(list.size()).toString());
            for (int i = 0; i < list.size(); i++) {
                List<Dex> list2 = (List) list.get(i);
                int i2 = 0;
                for (Dex methodIds : list2) {
                    i2 = methodIds.methodIds().size() + i2;
                }
                this.mDxContext.out.println(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append("[").append(i).toString()).append("] size:").toString()).append(list2.size()).toString()).append(", method=").toString()).append(i2).toString());
            }
        }
    }

    public static class Parameter {
        private CollisionPolicy mCollisionPolicy = CollisionPolicy.KEEP_FIRST;
        private int mCompactWasteThreshold = MultDexMerger.COMPACT_WASTE_THRESHOLD;
        private List<List<Dex>> mGroupDexs;

        public Parameter setGroupDexs(List<List<Dex>> list) {
            this.mGroupDexs = list;
            return this;
        }

        public List<List<Dex>> getGroupDexs() {
            return this.mGroupDexs;
        }

        public Parameter setCollisionPolicy(CollisionPolicy collisionPolicy) {
            this.mCollisionPolicy = collisionPolicy;
            return this;
        }

        public CollisionPolicy getCollisionPolicy() {
            return this.mCollisionPolicy;
        }

        public Parameter setCompactWasteThreshold(int i) {
            this.mCompactWasteThreshold = i;
            return this;
        }

        public int getCompactWasteThreshold() {
            return this.mCompactWasteThreshold;
        }
    }

    public MultDexMerger(DxContext dxContext, Parameter parameter) {
        this.mContext = dxContext;
        this.mParameter = parameter;
    }

    public List<Dex> merger() throws IOException {
        List<Dex> arrayList = new ArrayList();
        mergerTo(arrayList);
        return arrayList;
    }

    public void mergerTo(List<Dex> list) throws IOException {
        List groupDexs = this.mParameter.getGroupDexs();
        int i = 0;
        while (list != null && groupDexs != null && i < groupDexs.size()) {
            list.add(merger((Dex[]) ((List) groupDexs.get(i)).toArray(new Dex[0])));
            i++;
        }
    }

    public Dex merger(Dex... dexArr) throws IOException {
        return merger(dexArr, dexArr[0], 1);
    }

    private Dex merger(Dex[] dexArr, Dex dex, int i) throws IOException {
        if (i + 1 > dexArr.length) {
            return dex;
        }
        /*DexMerger dexMerger = new DexMerger(dex, dexArr[i], this.mParameter.getCollisionPolicy());
        dexMerger.setCompactWasteThreshold(this.mParameter.getCompactWasteThreshold());
        return merger(dexArr, dexMerger.merge(), i + 1);*/
        return null;
    }

    public DxContext getContext() {
        return this.mContext;
    }

    public Parameter getParameter() {
        return this.mParameter;
    }
}

