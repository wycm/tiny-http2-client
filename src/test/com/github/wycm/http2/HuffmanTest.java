package com.github.wycm.http2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wycm on 2019-12-24.
 */
public class HuffmanTest {
    private String hexString = "f1 e3 c2 e5 f2 3a 6b a0 ab 90 f4 ff";

    private String authority = "www.example.com";

    @Test
    public void encode() {
        Assert.assertArrayEquals(ByteUtils.hexStringToByteArray(hexString)
                , Huffman.encode(authority));
    }

    @Test
    public void decode() {
        Assert.assertEquals(authority, Huffman.decode(ByteUtils.hexStringToByteArray(hexString)));
        Assert.assertEquals("302", Huffman.decode(ByteUtils.hexStringToByteArray("64 02")));
        Assert.assertEquals("private", Huffman.decode(ByteUtils.hexStringToByteArray("ae c3 77 1a 4b")));
        Assert.assertEquals("Mon, 21 Oct 2013 20:13:21 GMT", Huffman.decode(ByteUtils.hexStringToByteArray("d0 7a be 94 10 54 d4 44 a8 20 05 95 04 0b 81 66 e0 82 a6 2d 1b ff")));
        Assert.assertEquals("https://www.example.com", Huffman.decode(ByteUtils.hexStringToByteArray("9d 29 ad 17 18 63 c7 8f 0b 97 c8 e9 ae 82 ae 43 d3")));
    }
}