package com.github.wycm.http2;

import java.util.List;

/**
 * Created by wycm on 2019-12-23.
 */
public class HeaderDecoder {

    private DynamicStaticTable dynamicStaticTable;

    public HeaderDecoder(DynamicStaticTable dynamicStaticTable) {
        this.dynamicStaticTable = dynamicStaticTable;
    }

    public List<Header> decode(ByteArrayBuffer byteArrayBuffer) {
        //todo
        return null;
    }
}
