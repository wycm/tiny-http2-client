package com.github.wycm.http2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by wycm on 2019-12-19.
 */
public class Http2Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("139.162.123.134", 80);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        // HTTP2 Magic
        out.write(new Magic().getBytes());
        out.flush();

        // HTTP2 SETTINGS
        out.write(new Settings().getBytes());
        out.flush();

        byte[] buf = new byte[256];
        int size = 0;
        while ((size = in.read(buf,0,buf.length)) != -1) {
            System.out.println("size:" + size);
            System.out.println(buf);
        }



        out.close();
        in.close();
        socket.close();
    }
}
