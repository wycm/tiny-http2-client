package com.github.wycm.http2.frame;

import com.github.wycm.http2.ByteArrayBuffer;
import com.github.wycm.http2.Constants;

/**
 * Created by wycm on 2019-12-26.
 */
public class Data extends Frame{
    private String responseStr;

    @Override
    public void decode(ByteArrayBuffer byteArrayBuffer) {
        byte[] bytes = byteArrayBuffer.readBytesByPos(Constants.FRAME_PREFIX_LEN, byteArrayBuffer.readInt(3));
        setResponseStr(new String(bytes));
    }

    public String getResponseStr() {
        return responseStr;
    }

    public void setResponseStr(String responseStr) {
        this.responseStr = responseStr;
    }
}
