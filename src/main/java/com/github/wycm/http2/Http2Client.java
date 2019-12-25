package com.github.wycm.http2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by wycm on 2019-12-19.
 */
public class Http2Client {
    private ConnectionContext connectionContext = new ConnectionContext(new HeaderEncoder(true, new DynamicStaticTable()));

    public Http2Client() {

    }

    public String execute(String url) throws IOException {
        String prefix = "http://";
        String sub = url.substring(prefix.length());
        int i = sub.indexOf("/");
        String domain = sub.substring(0, i);
        String path = sub.substring(i);
        Socket socket = new Socket(domain, 80);
        socket.setSoTimeout(10 * 1000);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        // HTTP2 Magic
        out.write(new Magic().getBytes());
        out.flush();

        // HTTP2 SETTINGS
        out.write(new Settings().getBytes());
        out.flush();

        // HTTP2 HEADERS
        Headers headers = new Headers(connectionContext);
        headers.addHeader(new Header(":method", "GET"));
        headers.addHeader(new Header(":scheme", "http"));
        headers.addHeader(new Header(":authority", domain));
        headers.addHeader(new Header(":path", path));
        headers.addHeader(new Header("user-agent", "tiny-http-client"));
        out.write(headers.getBytes());
        out.flush();

        byte[] buf = new byte[256];
        int size = 0;
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer();
        while ((size = in.read(buf,0,buf.length)) != -1) {
            //todo Decode response data
            byteArrayBuffer.append(buf, size);
        }
        out.close();
        in.close();
        socket.close();
        return new String(byteArrayBuffer.getBytes());
    }
}
