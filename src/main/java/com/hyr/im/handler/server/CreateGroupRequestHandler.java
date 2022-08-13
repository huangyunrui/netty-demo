package com.hyr.im.handler.server;


import com.hyr.im.common.SessionUtils;
import com.hyr.im.packet.request.CreateGroupRequestPacket;

import com.hyr.im.packet.response.CreateGroupResponsePacket;
import com.hyr.im.utils.IDUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建群聊
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket packet) throws Exception {
        //获取创建人
        List<String> userNameList = new ArrayList<>();
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        List<String> userIds = packet.getUserIds();

        //添加用户到组中
        for (String userId : userIds) {
            Channel channel = SessionUtils.getChannel(userId);
            if (channel != null){
                channelGroup.add(channel);
                userNameList.add(SessionUtils.getSession(channel).getUserName());
            }
        }

        //回写数据
        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setGroupId(IDUtils.genItemId());
        responsePacket.setSuccess(true);
        responsePacket.setUserNameList(userNameList);
        channelGroup.writeAndFlush(responsePacket);

        SessionUtils.addChannelGroup(responsePacket.getGroupId(), channelGroup);

        System.out.print("创建群聊成功 id:"+ responsePacket.getGroupId()+" 群成员为: "+responsePacket.getUserNameList());
    }

}
