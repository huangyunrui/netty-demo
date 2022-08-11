package com.hyr.im.handler;

import com.hyr.im.packet.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        AbstractPacket packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginRequestPacket) {
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            if (valid((LoginRequestPacket) packet)) {
                loginResponsePacket.setSuccess(Boolean.TRUE);
            } else {
                loginResponsePacket.setSuccess(Boolean.FALSE);
                loginResponsePacket.setReason("login fair");
            }
            ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(buf);
        }
        if (packet instanceof MessageRequestPacket) {
            System.out.println("accept client message" + ((MessageRequestPacket) packet).getMessage());
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("this is from server" + ((MessageRequestPacket) packet).getMessage());
            ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(buf);
        }

    }

    private boolean valid(LoginRequestPacket loginPacket) {
        return true;
    }
}
