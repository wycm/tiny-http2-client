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

    public void append(byte[] t) {
        append(t, t.length);
    }



    public int readInt(int readLen) {
        int result = 0;
        for (int i = 0; i < readLen; i++) {
            result |= (bytes[i] & 0xff) << ((readLen - i - 1) * 8);
        }
        return result;
    }

    public byte readByte(int index) {
        return bytes[index];
    }

    /**
     * Reads all byte arrays from the specified location
     * @param startPos
     * @return
     */
    public byte[] readBytesByPos(int startPos) {
        byte[] t = new byte[len - startPos];
        System.arraycopy(bytes, startPos, t, 0, t.length);
        return t;
    }

    /**
     * Reads an array of bytes of a specified length from a specified location
     * @param startPos
     * @param readLen
     * @return
     */
    public byte[] readBytesByPos(int startPos, int readLen) {
        byte[] t = new byte[readLen];
        System.arraycopy(bytes, startPos, t, 0, readLen);
        return t;
    }

    public byte[] readAllBytes() {
        return readBytes(len);
    }

    public byte[] readBytes(int readLen) {
        byte[] t = new byte[len];
        System.arraycopy(bytes, 0, t, 0, readLen);
        return t;
    }

    public int getLen() {
        return len;
    }
}
