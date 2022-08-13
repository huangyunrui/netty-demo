package com.hyr.im.handler.client;

import com.hyr.im.packet.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 消息响应处理器
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket packet) throws Exception {
        String fromUserId = packet.getFromUserId();
        String message = packet.getMessage();
        System.out.println("fromUserId:"+fromUserId+", message:"+message);
    }


}
