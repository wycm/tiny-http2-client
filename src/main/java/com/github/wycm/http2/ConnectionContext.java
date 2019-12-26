package com.github.wycm.http2;

/**
 * Created by wycm on 2019-12-25.
 */
public class ConnectionContext {

    private HeaderEncoder reqHeaderEncoder;

    private HeaderEncoder resHeaderEncoder;



    private int currentStreamId = 0;

    public ConnectionContext() {
        this.reqHeaderEncoder = new HeaderEncoder(true, new DynamicStaticTable());
        this.resHeaderEncoder = new HeaderEncoder(true, new DynamicStaticTable());
    }

    public int generateStreamId() {
        if (currentStreamId == 0) {
            currentStreamId++;
        } else {
            currentStreamId += 2;
        }
        return currentStreamId;
    }

    public HeaderEncoder getReqHeaderEncoder() {
        return reqHeaderEncoder;
    }

    public void setReqHeaderEncoder(HeaderEncoder reqHeaderEncoder) {
        this.reqHeaderEncoder = reqHeaderEncoder;
    }

    public int getCurrentStreamId() {
        return currentStreamId;
    }

    public void setCurrentStreamId(int currentStreamId) {
        this.currentStreamId = currentStreamId;
    }


    public HeaderEncoder getResHeaderEncoder() {
        return resHeaderEncoder;
    }

    public void setResHeaderEncoder(HeaderEncoder resHeaderEncoder) {
        this.resHeaderEncoder = resHeaderEncoder;
    }
}
