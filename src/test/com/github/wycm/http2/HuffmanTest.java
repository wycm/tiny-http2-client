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
    }
}