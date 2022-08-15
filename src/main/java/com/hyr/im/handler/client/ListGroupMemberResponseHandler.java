package com.hyr.im.handler.client;

import com.hyr.im.packet.response.ListGroupMemberResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 获取群成员
 */
@ChannelHandler.Sharable
public class ListGroupMemberResponseHandler extends SimpleChannelInboundHandler<ListGroupMemberResponsePacket> {
    public static final ListGroupMemberResponseHandler INSTANCE = new ListGroupMemberResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberResponsePacket packet) throws Exception {
            System.out.println("群id:"+packet.getGroupId()+" 群成员:"+packet.getMembers());
    }

}
