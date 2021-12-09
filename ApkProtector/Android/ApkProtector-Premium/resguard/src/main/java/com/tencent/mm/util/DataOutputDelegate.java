package com.tencent.mm.util;

import java.io.DataOutput;
import java.io.IOException;

public class DataOutputDelegate implements DataOutput {
    protected final DataOutput mDelegate;

    public DataOutputDelegate(DataOutput delegate) {
        this.mDelegate = delegate;
    }

    @Override
    public void write(int b) throws IOException {
        this.mDelegate.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.mDelegate.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.mDelegate.write(b, off, len);
    }

    @Override
    public void writeBoolean(boolean v) throws IOException {
        this.mDelegate.writeBoolean(v);
    }

    @Override
    public void writeByte(int v) throws IOException {
        this.mDelegate.writeByte(v);
    }

    @Override
    public void writeShort(int v) throws IOException {
        this.mDelegate.writeShort(v);
    }

    @Override
    public void writeChar(int v) throws IOException {
        this.mDelegate.writeChar(v);
    }

    @Override
    public void writeInt(int v) throws IOException {
        this.mDelegate.writeInt(v);
    }

    @Override
    public void writeLong(long v) throws IOException {
        this.mDelegate.writeLong(v);
    }

    @Override
    public void writeFloat(float v) throws IOException {
        this.mDelegate.writeFloat(v);
    }

    @Override
    public void writeDouble(double v) throws IOException {
        this.mDelegate.writeDouble(v);
    }

    @Override
    public void writeBytes(String s) throws IOException {
        this.mDelegate.writeBytes(s);
    }

    @Override
    public void writeChars(String s) throws IOException {
        this.mDelegate.writeChars(s);
    }

    @Override
    public void writeUTF(String s) throws IOException {
        this.mDelegate.writeUTF(s);
    }
}
