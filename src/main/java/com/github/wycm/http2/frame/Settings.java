package com.github.wycm.http2.frame;

import com.github.wycm.http2.ByteArrayBuffer;
import com.github.wycm.http2.ByteUtils;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Settings frame
 */
public class Settings extends Frame{

    private Set<Setting> settingSet = new LinkedHashSet<>();

    /**
     * The following properties default to false
     */
    private byte ack = 0x00;

    private byte unused = 0x00;



    public Settings() {
        setHeaderTableSize(8192);
        enablePush(0);
        setMaxConcurrentStreams(250);
        setInitialWindowsSize(65535);
        setMaxFrameSize(65536);
        setMaxHeaderListSize(0xffffff);
        setType((byte) 4);
    }

    public void setHeaderTableSize(int value) {
        settingSet.add(new Setting(Setting.HEADER_TABLE_SIZE, value));
    }

    public void enablePush(int value) {
        settingSet.add(new Setting(Setting.ENABLE_PUSH, value));
    }

    public void setMaxConcurrentStreams(int value) {
        settingSet.add(new Setting(Setting.MAX_CONCURRENT_STREAMS, value));
    }

    public void setInitialWindowsSize(int value) {
        settingSet.add(new Setting(Setting.INITIAL_WINDOWS_SIZE, value));
    }

    public void setMaxFrameSize(int value) {
        settingSet.add(new Setting(Setting.MAX_FRAME_SIZE, value));
    }

    public void setMaxHeaderListSize(int value) {
        settingSet.add(new Setting(Setting.MAX_HEADER_LIST_SIZE, value));
    }


    public byte[] getBytes() {
        int settingsLen = settingSet.size() * 6;
        byte[] frameBytes = super.getBytes();
        byte[] bytes = new byte[frameBytes.length + settingsLen];
        setFlags((byte) (unused | ack));
        setLength(settingsLen);
        System.arraycopy(super.getBytes(), 0, bytes, 0, frameBytes.length);
        AtomicInteger i = new AtomicInteger();
        settingSet.forEach(setting -> {
            System.arraycopy(setting.getBytes(), 0, bytes, frameBytes.length + i.get() * 6, 6);
            i.getAndIncrement();
        });
        return bytes;
    }

    @Override
    public void decode(ByteArrayBuffer byteArrayBuffer) {
        System.out.println("The Settings Frame is to be decoded");
    }

    static class Setting {
        public static final int HEADER_TABLE_SIZE = 01;

        public static final int ENABLE_PUSH = 02;

        public static final int MAX_CONCURRENT_STREAMS = 03;

        public static final int INITIAL_WINDOWS_SIZE = 04;

        public static final int MAX_FRAME_SIZE = 05;

        public static final int MAX_HEADER_LIST_SIZE = 06;

        Setting(int type, int value) {
            this.type = type;
            this.value = value;
        }
        /**
         * 2bytes
         */
        private int type;

        /**
         * 4bytes
         */
        private int value;

        public byte[] getBytes() {
            byte[] bytes = new byte[6];
            System.arraycopy(ByteUtils.intToByteArray(type), 2, bytes, 0, 2);
            System.arraycopy(ByteUtils.intToByteArray(value), 0, bytes, 2, 4);
            return bytes;
        }
    }
}
