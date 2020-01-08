package com.github.wycm.http2;

import com.github.wycm.http2.frame.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by wycm on 2019-12-19.
 */
public class Http2Client {
    private ConnectionContext connectionContext = new ConnectionContext();

    private volatile Socket socket;

    private DataInputStream in;

    private DataOutputStream out;

    public Http2Client() {

    }

    public String execute(String url) throws IOException {
        String prefix = "http://";
        String sub = url.substring(prefix.length());
        int i = sub.indexOf("/");
        String domain = sub.substring(0, i);
        String path = sub.substring(i);
        socket = new Socket(domain, 80);
        socket.setSoTimeout(10 * 1000);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
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
        headers.addHeader(new Header("user-agent", "tiny-http2-client"));
        out.write(headers.getBytes());
        out.flush();

        byte[] buf = new byte[1024];
        int size = 0;
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer();
        String response = null;
        while ((size = in.read(buf,0,buf.length)) != -1) {
            byteArrayBuffer.append(buf, size);
            while (frameEnd(byteArrayBuffer)) {
                Frame frame = decode(byteArrayBuffer);
                if (frame instanceof Data) {
                    response = ((Data) frame).getResponseStr();
                    return response;
                } else if (frame instanceof Headers) {
                    //todo
                }
                int frameLen = byteArrayBuffer.readInt(3) + Constants.FRAME_PREFIX_LEN;
                byte[] untreatedBytes = byteArrayBuffer.readBytesByPos(frameLen);
                byteArrayBuffer = new ByteArrayBuffer();
                byteArrayBuffer.append(untreatedBytes);
            }
        }
        return response;
    }

    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

    private Frame decode(ByteArrayBuffer frameBytes) {
        Frame result = null;
        byte type = frameBytes.readByte(3);
        switch (type) {
            case 0 : result = new Data(); break;
            case 1 : result = new Headers(connectionContext); break;
            case 4 : result = new Settings(); break;
            default: result = new Unknown(); break;
        }
        result.decode(frameBytes);
        return result;
    }

    /**
     * Whether a complete frame exists
     * @param byteArrayBuffer
     * @return
     */
    private boolean frameEnd(ByteArrayBuffer byteArrayBuffer) {
        if (byteArrayBuffer.getLen() <= 3) {
            return false;
        }
        int frameLen = byteArrayBuffer.readInt(3);
        if (byteArrayBuffer.getLen() >= (Constants.FRAME_PREFIX_LEN + frameLen)) {
            return true;
        } else {
            return false;
        }
    }

}
