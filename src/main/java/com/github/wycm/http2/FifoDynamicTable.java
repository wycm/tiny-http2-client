package com.github.wycm.http2;

/**
 * Fifo Dynamic Table
 */
public class FifoDynamicTable {

    private int capacity = 16;

    private int size = 0;

    private int head = -1;

    private int tail = -1;

    private Header[] headerArray = new Header[capacity];


    public void addHeader(Header header) {
        if (size >= headerArray.length) {
            //resize
            Header[] newHeaderArray = new Header[capacity << 1];
            for (int i = tail; i <= head; i++) {
                newHeaderArray[i] = headerArray[i];
            }
            headerArray = newHeaderArray;
        }
        headerArray[++head] = header;
        size++;
        if (tail < 0) {
            tail = 0;
        }
    }

    public Header remove() {
        return null;
    }

    public int getExactMatchIndex(Header header) {
        if (size == 0) {
            return -1;
        }
        for (int i = head; i >= tail; i--) {
            Header h = headerArray[i];
            if (h.getName().equals(header.getName()) && h.getValue().equals(header.getValue())) {
                return head - i;
            }
        }
        return -1;
    }

    public int getNameMatchIndex(Header header) {
        for (int i = head; i >= tail; i--) {
            Header h = headerArray[i];
            if (h.getName().equals(header.getName())) {
                return head - i;
            }
        }
        return -1;
    }


}
