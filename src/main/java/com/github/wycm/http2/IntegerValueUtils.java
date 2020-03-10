package com.github.wycm.http2;

/**
 * Integer value codec tool
 * https://http2.github.io/http2-spec/compression.html#integer.representation.example3
 * The case has not been considered
 */
public class IntegerValueUtils {

    /**
     *
     * @param value
     * @param N The prefix size, N is always between 1 and 8 bits
     * @return
     */
    public static byte[] encode(int value, int N, int flag) {
        int t = (int) (Math.pow(2, N) - 1);
        if (value < t) {
            return new byte[]{(byte) ((flag << N) | value)};
        } else {
            byte[] bytes = new byte[64];
            value = value - t;
            int i = 0;
            bytes[i++] = (byte) ((1 << N) | (0xff >> (8 - N)));
            while (value >= 128) {
               bytes[i++] = (byte)(value % 128 + 128);
               value = value / 128;
            }
            bytes[i++] = (byte) value;
            byte[] result = new byte[i];
            System.arraycopy(bytes, 0, result, 0, i);
            return result;
        }
    }

    /**
     * The format of the flag is 1 or 01 or 001 or 0001
     *
     * @param bytes
     * @return
     */
    private static int parseFlagLength(byte[] bytes) {
        int c = bytes[0];
        int i = 1;
        while ((c & 0x80) != 0x80) {
            c = c << 1;
            i++;
            if (i > 4) {
                throw new RuntimeException("Integer value flag exception");
            }
        }
        return i;
    }

    public static int decode(byte[] bytes, int N) {
        int flagLen = 8 - N;
        int t = (int) (Math.pow(2, N) - 1);

        int result = bytes[0] << (flagLen + 24) >>> (flagLen + 24);

        if ((t & bytes[0]) != t) {
            // value < 2^N - 1
            return result;
        } else {
            int m = 0;
            int i = 1;
            byte b;
            /**
             * The calculation formula given by the official specification is suspected to be problematic?
             * The value of the N-bit prefix of the first byte is not calculated in the pseudo code, it is calculated in the example
             * https://http2.github.io/http2-spec/compression.html#integer.representation
             */

            do {
                b = bytes[i++];
                result = result + (b & 127) * (int)Math.pow(2, m);
                m = m + 7;
            } while ((b & 128) == 128);
            return result;

        }
    }
}
