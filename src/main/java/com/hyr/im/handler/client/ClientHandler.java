package com.hyr.im.handler.client;

import com.hyr.im.packet.*;
import com.hyr.im.utils.LoginUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

            System.out.println("client start login");
            LoginRequestPacket loginPacket = new LoginRequestPacket(1, "zhangsan","zhangsan");
            ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginPacket);
            ctx.channel().writeAndFlush(buf);


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        AbstractPacket packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket){
            if (((LoginResponsePacket) packet).getSuccess()){

                LoginUtils.markAsLogin( ctx.channel());
                System.out.println("login Success");
            }else {
                System.out.println("login fair");
                System.out.println(("reason:" + ((LoginResponsePacket) packet).getReason()));
            }
        }
        if (packet instanceof MessageResponsePacket){
            System.out.println(("message response:" + ((MessageResponsePacket) packet).getMessage()));
        }
    }
}
