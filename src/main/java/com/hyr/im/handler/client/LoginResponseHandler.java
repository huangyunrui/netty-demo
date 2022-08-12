package com.hyr.im.handler.client;

import com.hyr.im.packet.LoginRequestPacket;
import com.hyr.im.packet.LoginResponsePacket;
import com.hyr.im.packet.PacketCodeC;
import com.hyr.im.utils.LoginUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登录响应处理器
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket packet) throws Exception {
        if (packet.getSuccess()){
            LoginUtils.markAsLogin( ctx.channel());
            System.out.println("login Success");
        }else {
            System.out.println("login fair");
            System.out.println(("reason:" +  packet.getReason()));
        }
        ctx.pipeline().remove(this);
    }

}
