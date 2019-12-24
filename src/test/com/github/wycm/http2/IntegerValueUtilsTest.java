package com.github.wycm.http2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by wycm on 2019-12-24.
 */
public class IntegerValueUtilsTest {

    @Test
    public void encode() {
        Assert.assertArrayEquals(new byte[]{0b00101010}, IntegerValueUtils.encode(10, 5, 1));
        Assert.assertArrayEquals(new byte[]{0b00001010}, IntegerValueUtils.encode(10, 5, 0));
        Assert.assertArrayEquals(new byte[]{0b00111111, (byte) 0b10011010, 0b00001010}, IntegerValueUtils.encode(1337, 5, 1));
    }

    @Test
    public void decode() {
        Assert.assertEquals(0b00001110, IntegerValueUtils.decode(new byte[]{0b00101110}, 5));
        Assert.assertEquals(10, IntegerValueUtils.decode(new byte[]{0b00101010}, 5));
        Assert.assertEquals(1337, IntegerValueUtils.decode(new byte[]{0b00111111, (byte) 0b10011010, 0b00001010}, 5));
    }
}