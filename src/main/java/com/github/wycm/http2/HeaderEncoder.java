package com.github.wycm.http2;

/**
 * Created by wycm on 2019-12-23.
 */
public class HeaderEncoder {
    private boolean huffman;

    private DynamicStaticTable dynamicStaticTable;

    private Representation representation = Representation.WITH_INDEXING;


    public HeaderEncoder(boolean huffman, DynamicStaticTable dynamicStaticTable) {
        this.huffman = huffman;
        this.dynamicStaticTable = dynamicStaticTable;
    }


    public byte[] encode(Header header) {
        int index;
        byte[] nameIndexBytes;
        byte[] valueLenBytes = null;
        byte[] valueLiteralBytes = null;
        byte[] literalBytes = null;
        int f = huffman ? 1 : 0;
        if ((index = dynamicStaticTable.getExactMatchIndex(header)) != -1){
            byte[] bytes = IntegerValueUtils.encode(index, 7, 1);
            return bytes;
        } else {
            // Literal Header Field with Incremental Indexing — New Name
            if (representation == Representation.WITH_INDEXING) {
                if ((index = dynamicStaticTable.getNameMatchIndex(header)) != -1) {
                    nameIndexBytes = IntegerValueUtils.encode(index, 6, 1);
                    valueLiteralBytes = Huffman.encode(header.getValue());
                    valueLenBytes = IntegerValueUtils.encode(valueLiteralBytes.length, 7, f);
                } else {
                    nameIndexBytes = new byte[]{0b01000000};
                    literalBytes = encodeLiteral(header, f);
                }
                //Question: How to ensure that the server has received the request? Guarantee the consistency of dynamic table
                dynamicStaticTable.addHeader(header);
            } else if (representation == Representation.WITHOUT_INDEXING){
                //Literal Header Field without Indexing
                if ((index = dynamicStaticTable.getNameMatchIndex(header)) != -1) {
                    nameIndexBytes = IntegerValueUtils.encode(index, 4, 0);
                    valueLiteralBytes = Huffman.encode(header.getValue());
                    valueLenBytes = IntegerValueUtils.encode(valueLiteralBytes.length, 7, f);
                } else {
                    nameIndexBytes = new byte[]{0};
                    literalBytes = encodeLiteral(header, f);
                }
            } else {
                if ((index = dynamicStaticTable.getNameMatchIndex(header)) != -1) {
                    nameIndexBytes = IntegerValueUtils.encode(index, 4, 1);
                    valueLiteralBytes = Huffman.encode(header.getValue());
                    valueLenBytes = IntegerValueUtils.encode(valueLiteralBytes.length, 7, f);
                } else {
                    nameIndexBytes = new byte[]{0x10};
                    literalBytes = encodeLiteral(header, f);
                }
            }
            if (valueLenBytes == null) {
                return ByteUtils.combine(nameIndexBytes, literalBytes);
            } else {
                return ByteUtils.combine(nameIndexBytes, valueLenBytes, valueLiteralBytes);
            }

        }
    }

    /**
     * header literal encoding
     * @param header
     * @param f
     * @return
     */
    private byte[] encodeLiteral(Header header, int f) {
        byte[] nameLenBytes = null;
        byte[] nameLiteralBytes = null;
        byte[] valueLenBytes = null;
        byte[] valueLiteralBytes = null;
        nameLiteralBytes = Huffman.encode(header.getName());
        nameLenBytes = IntegerValueUtils.encode(nameLiteralBytes.length, 7, f);
        valueLiteralBytes = Huffman.encode(header.getValue());
        valueLenBytes = IntegerValueUtils.encode(valueLiteralBytes.length, 7, f);
        return ByteUtils.combine(nameLenBytes, nameLiteralBytes, valueLenBytes, valueLiteralBytes);
    }
}
