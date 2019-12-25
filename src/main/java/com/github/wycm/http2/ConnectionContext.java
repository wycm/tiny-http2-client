package com.github.wycm.http2;

/**
 * Created by wycm on 2019-12-25.
 */
public class ConnectionContext {
    private HeaderEncoder headerEncoder;

    private int currentStreamId = 0;

    public ConnectionContext(HeaderEncoder headerEncoder) {
        this.headerEncoder = headerEncoder;
    }

    public int generateStreamId() {
        if (currentStreamId == 0) {
            currentStreamId++;
        } else {
            currentStreamId += 2;
        }
        return currentStreamId;
    }

    public HeaderEncoder getHeaderEncoder() {
        return headerEncoder;
    }

    public void setHeaderEncoder(HeaderEncoder headerEncoder) {
        this.headerEncoder = headerEncoder;
    }

    public int getCurrentStreamId() {
        return currentStreamId;
    }

    public void setCurrentStreamId(int currentStreamId) {
        this.currentStreamId = currentStreamId;
    }
}
