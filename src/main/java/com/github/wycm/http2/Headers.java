package com.github.wycm.http2;

import java.util.LinkedList;
import java.util.List;

/**
 * Headers Stream
 */
public class Headers {
    private Frame frame = new Frame();

    private List<Header> hpackHeaderList = new LinkedList<>();

    public byte[] encode() {
        for (Header hpackHeader : hpackHeaderList) {

        }
        return null;
    }

    public void addHeader(Header hpackHeader) {

    }

}
