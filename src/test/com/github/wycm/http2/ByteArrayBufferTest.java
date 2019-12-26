package com.github.wycm.http2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wycm on 2019-12-26.
 */
public class ByteArrayBufferTest {

    @Test
    public void readInt() {
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer();
        byteArrayBuffer.append(new byte[]{0, 0, 24});
        Assert.assertEquals(24, byteArrayBuffer.readInt(3));

        byteArrayBuffer = new ByteArrayBuffer();
        byteArrayBuffer.append(new byte[]{0, 0, (byte) 0xd9});
        Assert.assertEquals(217, byteArrayBuffer.readInt(3));
    }
}