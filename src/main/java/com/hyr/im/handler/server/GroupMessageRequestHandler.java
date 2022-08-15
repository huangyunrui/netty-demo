package com.hyr.im.handler.server;



import com.hyr.im.common.Session;
import com.hyr.im.common.SessionUtils;
import com.hyr.im.packet.request.SendToGroupRequestPacket;
import com.hyr.im.packet.response.SendToGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;




/**
 * 发送群聊消息
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<SendToGroupRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToGroupRequestPacket packet) throws Exception {

        //获取群聊信息
        Integer groupId = packet.getGroupId();
        SendToGroupResponsePacket responsePacket = new SendToGroupResponsePacket();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        Session session = SessionUtils.getSession(ctx.channel());

        responsePacket.setGroupId(groupId);
        responsePacket.setMessage(packet.getMessage());
        responsePacket.setSuccess(true);
        responsePacket.setFromUser(session.getUserName());

        channelGroup.writeAndFlush(responsePacket);
    }

}
