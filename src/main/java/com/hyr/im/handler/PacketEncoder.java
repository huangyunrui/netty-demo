package com.hyr.im.handler;

import com.hyr.im.packet.AbstractPacket;
import com.hyr.im.packet.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 对象转换二进制数据
 */
public class PacketEncoder extends MessageToByteEncoder<AbstractPacket> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, AbstractPacket packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.INSTANCE.encode(byteBuf, packet);
    }
}
