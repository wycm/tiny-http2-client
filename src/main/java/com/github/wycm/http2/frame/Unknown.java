package com.github.wycm.http2.frame;

import com.github.wycm.http2.ByteArrayBuffer;

/**
 * Created by wycm on 2019-12-26.
 */
public class Unknown extends Frame{
    @Override
    public void decode(ByteArrayBuffer byteArrayBuffer) {
        System.out.println("The Unknown Frame is to be decoded");
    }
}
