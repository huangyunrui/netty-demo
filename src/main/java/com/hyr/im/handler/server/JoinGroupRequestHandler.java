package com.hyr.im.handler.server;



import com.hyr.im.common.Session;
import com.hyr.im.common.SessionUtils;
import com.hyr.im.packet.request.JoinGroupRequestPacket;
import com.hyr.im.packet.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;


/**
 * 加入群聊
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket packet) throws Exception {

        //获取群聊信息
        Integer groupId = packet.getGroupId();
        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        Session session = SessionUtils.getSession(ctx.channel());

        if (null != channelGroup){
            channelGroup.add(ctx.channel());
            responsePacket.setGroupId(groupId);
            responsePacket.setSuccess(true);
            responsePacket.setMessage("ok");
            String userName = session.getUserName();
            responsePacket.setMessage(userName+" 加入群聊");
            //通知所有人
            channelGroup.writeAndFlush(responsePacket);
        }else {
            responsePacket.setGroupId(groupId);
            responsePacket.setSuccess(false);
            responsePacket.setMessage("未找到聊天群");
            ctx.channel().writeAndFlush(responsePacket);
        }

    }

}
