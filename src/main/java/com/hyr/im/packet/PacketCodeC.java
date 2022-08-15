package com.hyr.im.packet;

import com.hyr.im.packet.request.*;
import com.hyr.im.packet.response.*;
import com.hyr.im.serializer.JSONSerializer;
import com.hyr.im.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class PacketCodeC {
    public static final int MAGIC_NUMBER = 0x12345678;
    public static PacketCodeC INSTANCE = new PacketCodeC();
    public ByteBuf encode(ByteBufAllocator alloc, AbstractPacket packet){
        ByteBuf byteBuf = alloc.buffer();
        byte[] data = Serializer.DEFAULT.serializer(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.JSON_SERIALIZER);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);

        return byteBuf;
    }

    public ByteBuf encode(ByteBuf byteBuf, AbstractPacket packet){
        //序列化Java对象
        byte[] data = Serializer.DEFAULT.serializer(packet);
        //编码
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
        switch (serializerAlgorithm){
            case 1:
                return JSONSerializer.INSTANCE;
        }
        return JSONSerializer.INSTANCE;
    }

    private Class<? extends AbstractPacket> getRequestType(byte command) {
        switch (command){
            case 1:
                return LoginRequestPacket.class;
            case 2:
                return LoginResponsePacket.class;
            case 3:
                return MessageRequestPacket.class;
            case 4:
                return MessageResponsePacket.class;
            case 5:
                return CreateGroupRequestPacket.class;
            case 6:
                return CreateGroupResponsePacket.class;
            case 7:
                return JoinGroupRequestPacket.class;
            case 8:
                return JoinGroupResponsePacket.class;
            case 9:
                return ListGroupMemberRequestPacket.class;
            case 10:
                return ListGroupMemberResponsePacket.class;
            case 11:
                return SendToGroupRequestPacket.class;
            case 12:
                return SendToGroupResponsePacket.class;
            case 13:
                return HeartBeatRequestPacket.class;
            case 14:
                return HeartBeatResponsePacket.class;
        }
        return null;
    }
}
