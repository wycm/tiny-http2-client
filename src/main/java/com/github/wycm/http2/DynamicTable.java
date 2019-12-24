package com.github.wycm.http2;

import java.util.LinkedList;
import java.util.List;

/**
 * 动态表
 */
public class DynamicTable {

    private List<Header> dynamicHeaderList = new LinkedList<>();


    public List<Header> getDynamicHeaderList() {
        return dynamicHeaderList;
    }
}
