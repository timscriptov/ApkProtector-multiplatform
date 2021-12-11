package pxb.android;

public class StringItem {
    public String data;
    public int index;
    int dataOffset;

    public StringItem() {
        super();
    }

    public StringItem(String data) {
        super();
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StringItem other = (StringItem) obj;
        if (data == null) {
            return other.data == null;
        } else return data.equals(other.data);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }

    public String toString() {
        return String.format("S%04d %s", index, data);
    }
}