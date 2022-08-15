package com.hyr.im.handler.server;

import com.hyr.im.common.Session;
import com.hyr.im.common.SessionUtils;
import com.hyr.im.packet.request.HeartBeatRequestPacket;
import com.hyr.im.packet.request.MessageRequestPacket;
import com.hyr.im.packet.response.HeartBeatResponsePacket;
import com.hyr.im.packet.response.MessageResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 消息发送处理器
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket packet) throws Exception {
        System.out.println("收到心跳请求");
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }

}
