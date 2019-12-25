package com.github.wycm.http2;

import java.util.LinkedList;
import java.util.List;

/**
 * Headers Stream
 */
public class Headers {
    private Frame frame = new Frame();

    /**
     * The following properties default to true
     */
    private byte endStreams = 0x01;

    private byte endHeaders = 0x04;

    private byte padded = 0x08;

    private byte priority = 0x20;

    private byte unused = 0x00;

    private ConnectionContext connectionContext;


    public Headers(ConnectionContext connectionContext) {
        this.connectionContext = connectionContext;
        init();
    }

    private void init() {
        setPadded(false);
        setPriority(false);
        frame.setStreamId(connectionContext.generateStreamId());
        frame.setType((byte) 1);
    }

    private List<Header> headerList = new LinkedList<>();


    public byte[] getBytes() {
        byte[] headersBytes;
        byte[][] t = new byte[headerList.size()][];
        int i = 0;
        for (Header header : headerList) {
            t[i++] = connectionContext.getHeaderEncoder().encode(header);
        }
        headersBytes = ByteUtils.combine(t);
        frame.setLength(headersBytes.length);

        frame.setFlags((byte) (endStreams | endHeaders | padded | priority | unused));
        return ByteUtils.combine(frame.getBytes(), headersBytes);
    }



    public void addHeader(Header header) {
        headerList.add(header);
    }

    public void setEndStreams(boolean endStreams) {
        if (!endStreams) {
            this.endStreams = 0;
        }
    }

    public void setEndHeaders(boolean endHeaders) {
        if (!endHeaders) {
            this.endHeaders = 0;
        }
    }

    public void setPadded(boolean padded) {
        if (!padded) {
            this.padded = 0;
        }
    }

    public void setPriority(boolean priority) {
        if (!priority) {
            this.priority = 0;
        }
    }

    public void setUnused(boolean unused) {
        if (!unused) {
            this.unused = 0;
        }
    }
}
