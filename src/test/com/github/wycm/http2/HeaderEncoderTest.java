package com.github.wycm.http2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wycm on 2019-12-25.
 */
public class HeaderEncoderTest {

    @Test
    public void testEncodeByStaticTable() {
        HeaderEncoder headerEncoder = new HeaderEncoder(true, new DynamicStaticTable());

        Assert.assertArrayEquals(new byte[]{(byte) 0b10000010},
                headerEncoder.encode(new Header(":method", "GET")));

        Assert.assertArrayEquals(new byte[]{(byte) 0b10000110},
                headerEncoder.encode(new Header(":scheme", "http")));

        Assert.assertArrayEquals(ByteUtils.hexStringToByteArray("41 88 aa 69 d2 9a c4 b9 ec 9b"),
                headerEncoder.encode(new Header(":authority", "nghttp2.org")));

        Assert.assertArrayEquals(ByteUtils.hexStringToByteArray("44 8e 62 74 a6 b8 cd 53 16 a0 b6 2c 39 8b 52 7f"),
                headerEncoder.encode(new Header(":path", "/httpbin/user-agent")));

        Assert.assertArrayEquals(ByteUtils.hexStringToByteArray("7a a0 86 b1 92 72 ad 8d 29 ae f1 ec 2b 0d ae 05 a3 " +
                        "2a 46 10 29 fd 65 1f 71 b0 15 de 5c 11 05 90 ff 7f"),
                headerEncoder.encode(new Header("user-agent", "Apache-HttpCore/5.0-beta10 (Java/1.8.0_131)")));
    }

    @Test
    public void testEncodeByDynamicTable() {
        HeaderEncoder headerEncoder = new HeaderEncoder(true, new DynamicStaticTable());

        //First request
        Assert.assertArrayEquals(new byte[]{(byte) 0b10000010},
                headerEncoder.encode(new Header(":method", "GET")));

        Assert.assertArrayEquals(new byte[]{(byte) 0b10000110},
                headerEncoder.encode(new Header(":scheme", "http")));

        Assert.assertArrayEquals(ByteUtils.hexStringToByteArray("41 88 aa 69 d2 9a c4 b9 ec 9b"),
                headerEncoder.encode(new Header(":authority", "nghttp2.org")));

        Assert.assertArrayEquals(ByteUtils.hexStringToByteArray("44 8e 62 74 a6 b8 cd 53 16 a0 b6 2c 39 8b 52 7f"),
                headerEncoder.encode(new Header(":path", "/httpbin/user-agent")));

        Assert.assertArrayEquals(ByteUtils.hexStringToByteArray("7a a0 86 b1 92 72 ad 8d 29 ae f1 ec 2b 0d ae 05 a3 " +
                        "2a 46 10 29 fd 65 1f 71 b0 15 de 5c 11 05 90 ff 7f"),
                headerEncoder.encode(new Header("user-agent", "Apache-HttpCore/5.0-beta10 (Java/1.8.0_131)")));


        //Second request
        Assert.assertArrayEquals(new byte[]{(byte) 0b10000010},
                headerEncoder.encode(new Header(":method", "GET")));

        Assert.assertArrayEquals(new byte[]{(byte) 0b10000110},
                headerEncoder.encode(new Header(":scheme", "http")));

        Assert.assertArrayEquals(ByteUtils.hexStringToByteArray("c0"),
                headerEncoder.encode(new Header(":authority", "nghttp2.org")));

        Assert.assertArrayEquals(ByteUtils.hexStringToByteArray("bf"),
                headerEncoder.encode(new Header(":path", "/httpbin/user-agent")));

        Assert.assertArrayEquals(ByteUtils.hexStringToByteArray("be"),
                headerEncoder.encode(new Header("user-agent", "Apache-HttpCore/5.0-beta10 (Java/1.8.0_131)")));
    }
}