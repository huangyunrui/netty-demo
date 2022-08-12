package com.hyr.im.handler.client;

import com.hyr.im.packet.CreateGroupResponsePacket;
import com.hyr.im.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 消息响应处理器
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket packet) throws Exception {
        if (packet.getSuccess()){
            System.out.println("创建群聊成功:"+packet.getGroupId()+", 成员列表:"+packet.getUserNameList());
        }else {
            System.out.println("创建群聊失败");
        }

    }


}