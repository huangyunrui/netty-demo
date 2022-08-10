package com.hyr.im.packet;

import com.hyr.im.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class PacketCode {
    private static final int MAGIC_NUMBER = 0x12345678;
    public ByteBuf encode(AbstractPacket packet){
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] data = Serializer.DEFAULT.serializer(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.JSON_SERIALIZER);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);

        return byteBuf;
    }

    public AbstractPacket decode(ByteBuf byteBuf){
        //magic
        byteBuf.skipBytes(4);
        //vserion
        byteBuf.skipBytes(1);
        byte serializerAlgorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int dataLength = byteBuf.readInt();
        byte[] data = new byte[dataLength];
        byteBuf.readBytes(data);
        Class<? extends  AbstractPacket> requestType =  getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);
        if (null != requestType && null != serializer){
           return serializer.deserialize(requestType, data);
        }
        return null;
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        return null;
    }

    private Class<? extends AbstractPacket> getRequestType(byte command) {
        return null;
    }
}
