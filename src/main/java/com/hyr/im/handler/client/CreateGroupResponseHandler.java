package com.hyr.im.handler.client;

import com.hyr.im.packet.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 消息响应处理器
 */
@ChannelHandler.Sharable
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    public static CreateGroupResponseHandler INSTANCE = new CreateGroupResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket packet) throws Exception {
        if (packet.getSuccess()){
            System.out.println("创建群聊成功:"+packet.getGroupId()+", 成员列表:"+packet.getUserNameList());
        }else {
            System.out.println("创建群聊失败");
        }
    }


}
