package com.hyr.im.handler.client;

import com.hyr.im.common.SessionUtils;
import com.hyr.im.packet.response.ListGroupMemberResponsePacket;
import com.hyr.im.packet.response.SendToGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 获取群成员
 */
@ChannelHandler.Sharable
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<SendToGroupResponsePacket> {
    public static final GroupMessageResponseHandler INSTANCE = new GroupMessageResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToGroupResponsePacket packet) throws Exception {
        SessionUtils.getSession(ctx.channel());
        System.out.println("【收到群聊消息】 "+ packet.getFromUser() + " : " + packet.getMessage());
    }

}
