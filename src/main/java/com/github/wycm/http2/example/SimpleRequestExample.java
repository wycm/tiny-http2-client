package com.github.wycm.http2.example;

import com.github.wycm.http2.Http2Client;

import java.io.IOException;

/**
 * Created by wycm on 2019-12-25.
 */
public class SimpleRequestExample {
    public static void main(String[] args) throws IOException {
        String response = new Http2Client().execute("http://nghttp2.org/httpbin/user-agent");
        System.out.println(response);
    }
}
