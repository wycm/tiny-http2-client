package com.github.wycm.http2;

/**
 * Created by wycm on 2019-12-25.
 */
public class ByteArrayBuffer {
    private int capacity = 16;

    private int len = 0;

    private byte[] bytes = new byte[capacity];

    public void append(byte[] t, int appendLen) {
        while (capacity - len < appendLen) {
            capacity = capacity << 2;
        }
        byte[] newBytes = new byte[capacity];
        for (int i = 0; i < len; i++) {
            newBytes[i] = bytes[i];
        }
        bytes = newBytes;

        for (int i = 0; i < appendLen; i++) {
            bytes[len++] = t[i];
        }
    }

    public byte[] getBytes() {
        byte[] t = new byte[len];
        System.arraycopy(bytes, 0, t, 0, len);
        return t;
    }
}
