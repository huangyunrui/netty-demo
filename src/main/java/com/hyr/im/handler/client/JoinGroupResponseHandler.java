package com.hyr.im.handler.client;

import com.hyr.im.packet.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登录响应处理器
 */
@ChannelHandler.Sharable
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    public static final JoinGroupResponseHandler INSTANCE = new JoinGroupResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket packet) throws Exception {
        if (packet.getSuccess()){
            System.out.println("通知:"+packet.getMessage());
        }else {
            System.out.println("login fair reason:"+packet.getSuccess());
        }
    }

}
