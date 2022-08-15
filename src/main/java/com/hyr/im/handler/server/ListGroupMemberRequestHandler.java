package com.hyr.im.handler.server;



import com.hyr.im.common.Session;
import com.hyr.im.common.SessionUtils;
import com.hyr.im.packet.request.JoinGroupRequestPacket;
import com.hyr.im.packet.request.ListGroupMemberRequestPacket;
import com.hyr.im.packet.response.ListGroupMemberResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * 加入群聊
 */
@ChannelHandler.Sharable
public class ListGroupMemberRequestHandler extends SimpleChannelInboundHandler<ListGroupMemberRequestPacket> {

    public static final ListGroupMemberRequestHandler INSTANCE = new ListGroupMemberRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberRequestPacket packet) throws Exception {

        //获取群聊信息
        Integer groupId = packet.getGroupId();
        ListGroupMemberResponsePacket responsePacket = new ListGroupMemberResponsePacket();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        responsePacket.setGroupId(groupId);

        //遍历
        List<Session> members = new ArrayList<>(channelGroup.size());
        if (null != channelGroup){
            for (Channel channel : channelGroup) {
                Session session = SessionUtils.getSession(channel);
                members.add(session);
            }
        }
        responsePacket.setMembers(members);
        ctx.channel().writeAndFlush(responsePacket);
    }

}
