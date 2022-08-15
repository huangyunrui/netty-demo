package com.hyr.im.handler;

import com.hyr.im.packet.AbstractPacket;
import com.hyr.im.packet.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, AbstractPacket> {
    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();
    /**
     * @param ctx
     * @param msg
     * @param out
     * @see MessageToMessageEncoder#encode(ChannelHandlerContext, Object, List)
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodeC.INSTANCE.encode(byteBuf, msg);
        out.add(byteBuf);
    }

    /**
     * @param ctx
     * @param msg
     * @param out
     * @see MessageToMessageDecoder#decode(ChannelHandlerContext, Object, List)
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        out.add(PacketCodeC.INSTANCE.decode(msg));
    }
}
