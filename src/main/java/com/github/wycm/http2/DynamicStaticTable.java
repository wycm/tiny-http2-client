package com.github.wycm.http2;

/**
 * Created by wycm on 2019-12-23.
 */
public class DynamicStaticTable {
    private StaticTable staticTable = new StaticTable();

    private DynamicTable dynamicTable = new DynamicTable();

    /**
     * Get exact match index
     * @param header
     * @return
     */
    public int getExactMatchIndex(Header header) {
        for (int i = 0; i < staticTable.gethPackHeaderList().size(); i++) {
            Header staticHeader = staticTable.gethPackHeaderList().get(i);
            if (staticHeader.getName().equals(header.getName()) && staticHeader.getValue().equals(header.getValue())) {
                return i + 1;
            }
        }

        for (int i = 0; i < dynamicTable.getDynamicHeaderList().size(); i++) {
            Header dynamicHeader = dynamicTable.getDynamicHeaderList().get(i);
            if (dynamicHeader.getName().equals(header.getName()) && dynamicHeader.getValue().equals(header.getValue())) {
                return staticTable.gethPackHeaderList().size() + i + 1;
            }
        }
        return -1;
    }

    /**
     * Get name matching index
     * @param header
     * @return
     */
    public int getNameMatchIndex(Header header) {
        for (int i = 0; i < staticTable.gethPackHeaderList().size(); i++) {
            Header staticHeader = staticTable.gethPackHeaderList().get(i);
            if (staticHeader.getName().equals(header.getValue())) {
                return i + 1;
            }
        }

        for (int i = 0; i < dynamicTable.getDynamicHeaderList().size(); i++) {
            Header dynamicHeader = dynamicTable.getDynamicHeaderList().get(i);
            if (dynamicHeader.getName().equals(header.getName())) {
                return staticTable.gethPackHeaderList().size() + i + 1;
            }
        }
        return -1;
    }

    public Header getHeaderByIndex(int index) {
        int staticLen = staticTable.gethPackHeaderList().size();
        if (index <= staticLen) {
            return staticTable.gethPackHeaderList().get(index - 1);
        } else {
            return staticTable.gethPackHeaderList().get(index - staticLen - 1);
        }
    }

    public void addHeader(Header header) {
        dynamicTable.getDynamicHeaderList().add(header);
    }

}
