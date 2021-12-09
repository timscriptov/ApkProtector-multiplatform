package com.oscar0812.obfuscation.utils;

public class Substring {
    private final int startIndex;
    private final int endIndex;
    private final String text;

    public Substring(int startIndex, int endIndex, String text) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.text = text;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public String getText() {
        return text;
    }
}
