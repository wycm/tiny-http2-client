package com.github.wycm.http2;

/**
 * Created by wycm on 2019-12-19.
 */
public class Frame {
    // 3bytes
    private int length;

    // 1byte
    private byte type;

    // 1byte
    private byte flags = 0;

    // 1bit
    private int reserved = 0;

    // 31bits(和reserved一共占4byte)
    private int streamId = 0;

    public Frame() {
    }

    public byte[] getBytes() {
        byte[] bytes = new byte[3 + 1 + 1 + 4];
        byte[] lengthBytes = ByteUtils.intToByteArray(length);
        System.arraycopy(lengthBytes, 1, bytes, 0, 3);
        bytes[3] = type;
        bytes[4] = flags;

        System.arraycopy(new byte[4], 0, bytes, 5, 4);

        return bytes;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getFlags() {
        return flags;
    }

    public void setFlags(byte flags) {
        this.flags = flags;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }
}
