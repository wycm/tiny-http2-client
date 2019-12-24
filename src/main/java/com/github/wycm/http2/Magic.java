package com.github.wycm.http2;

/**
 * Created by wycm on 2019-12-19.
 */
public class Magic {
    private String constant = "PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n";

    public byte[] getBytes() {
        return constant.getBytes();
    }
}
