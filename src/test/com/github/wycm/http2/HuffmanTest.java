package com.github.wycm.http2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wycm on 2019-12-24.
 */
public class HuffmanTest {

    @Test
    public void encode() {
        Assert.assertArrayEquals(new byte[]{(byte) 0xf1, (byte) 0xe3, (byte) 0xc2, (byte)0xe5, (byte)0xf2, 0x3a, 0x6b
                        , (byte) 0xa0, (byte) 0xab, (byte) 0x90, (byte) 0xf4, (byte) 0xff}
        , Huffman.encode("www.example.com"));
    }

    @Test
    public void decode() {
    }
}