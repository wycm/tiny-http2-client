package com.github.wycm.http2;

/**
 * Created by wycm on 2019-12-19.
 */
public class ByteUtils {
    public static final byte[] intToByteArray(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value};
    }

    public static byte[] combine(byte[]... bytes) {
        int totalLen = 0;
        for (byte[] b : bytes) {
            totalLen += b.length;
        }
        byte[] result = new byte[totalLen];
        int indexLen = 0;
        for (byte[] b : bytes) {
            System.arraycopy(b, 0, result, indexLen, b.length);
            indexLen += b.length;
        }
        return result;
    }

}
