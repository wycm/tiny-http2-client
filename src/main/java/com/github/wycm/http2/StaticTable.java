package com.github.wycm.http2;

import java.util.LinkedList;
import java.util.List;

/**
 * 静态表
 */
public class StaticTable {
    private final List<Header> hPackHeaderList = new LinkedList<>();

    {
        hPackHeaderList.add(new Header(":authority", ""));
        hPackHeaderList.add(new Header(":method", "GET"));
        hPackHeaderList.add(new Header(":method", "POST"));
        hPackHeaderList.add(new Header(":path", "/"));
        hPackHeaderList.add(new Header(":path", "/index.html"));
        hPackHeaderList.add(new Header(":scheme", "http"));
        hPackHeaderList.add(new Header(":scheme", "https"));
        hPackHeaderList.add(new Header(":status", "200"));
        hPackHeaderList.add(new Header(":status", "204"));
        hPackHeaderList.add(new Header(":status", "206"));
        hPackHeaderList.add(new Header(":status", "304"));
        hPackHeaderList.add(new Header(":status", "400"));
        hPackHeaderList.add(new Header(":status", "404"));
        hPackHeaderList.add(new Header(":status", "500"));
        hPackHeaderList.add(new Header("accept-charset", ""));
        hPackHeaderList.add(new Header("accept-encoding", "gzip, deflate"));
        hPackHeaderList.add(new Header("accept-language", ""));
        hPackHeaderList.add(new Header("accept-ranges", ""));
        hPackHeaderList.add(new Header("accept", ""));
        hPackHeaderList.add(new Header("access-control-allow-origin", ""));
        hPackHeaderList.add(new Header("age", ""));
        hPackHeaderList.add(new Header("allow", ""));
        hPackHeaderList.add(new Header("authorization", ""));
        hPackHeaderList.add(new Header("cache-control", ""));
        hPackHeaderList.add(new Header("content-disposition", ""));
        hPackHeaderList.add(new Header("content-encoding", ""));
        hPackHeaderList.add(new Header("content-language", ""));
        hPackHeaderList.add(new Header("content-length", ""));
        hPackHeaderList.add(new Header("content-location", ""));
        hPackHeaderList.add(new Header("content-range", ""));
        hPackHeaderList.add(new Header("content-type", ""));
        hPackHeaderList.add(new Header("cookie", ""));
        hPackHeaderList.add(new Header("date", ""));
        hPackHeaderList.add(new Header("etag", ""));
        hPackHeaderList.add(new Header("expect", ""));
        hPackHeaderList.add(new Header("expires", ""));
        hPackHeaderList.add(new Header("from", ""));
        hPackHeaderList.add(new Header("host", ""));
        hPackHeaderList.add(new Header("if-match", ""));
        hPackHeaderList.add(new Header("if-modified-since", ""));
        hPackHeaderList.add(new Header("if-none-match", ""));
        hPackHeaderList.add(new Header("if-range", ""));
        hPackHeaderList.add(new Header("if-unmodified-since", ""));
        hPackHeaderList.add(new Header("last-modified", ""));
        hPackHeaderList.add(new Header("link", ""));
        hPackHeaderList.add(new Header("location", ""));
        hPackHeaderList.add(new Header("max-forwards", ""));
        hPackHeaderList.add(new Header("proxy-authenticate", ""));
        hPackHeaderList.add(new Header("proxy-authorization", ""));
        hPackHeaderList.add(new Header("range", ""));
        hPackHeaderList.add(new Header("referer", ""));
        hPackHeaderList.add(new Header("refresh", ""));
        hPackHeaderList.add(new Header("retry-after", ""));
        hPackHeaderList.add(new Header("server", ""));
        hPackHeaderList.add(new Header("set-cookie", ""));
        hPackHeaderList.add(new Header("strict-transport-security", ""));
        hPackHeaderList.add(new Header("transfer-encoding", ""));
        hPackHeaderList.add(new Header("user-agent", ""));
        hPackHeaderList.add(new Header("vary", ""));
        hPackHeaderList.add(new Header("via", ""));
        hPackHeaderList.add(new Header("www-authenticate", ""));
    }

    public List<Header> gethPackHeaderList() {
        return hPackHeaderList;
    }
}
