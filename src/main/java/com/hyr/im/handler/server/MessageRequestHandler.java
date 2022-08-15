package com.hyr.im.handler.server;

import com.hyr.im.common.Session;
import com.hyr.im.common.SessionUtils;
import com.hyr.im.packet.request.MessageRequestPacket;
import com.hyr.im.packet.response.MessageResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 消息发送处理器
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) throws Exception {
        //获取发送方
        Session session = SessionUtils.getSession(ctx.channel());

        //封装消息
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setMessage(packet.getMessage());
        responsePacket.setFromUserId(session.getUserId());
        responsePacket.setFromUserName(session.getUserName());

        //获取消息接受方
        Channel toUserChannel = SessionUtils.getChannel(packet.getToUserId());
        if (null != toUserChannel && SessionUtils.hasLogin(ctx.channel())){
            toUserChannel.writeAndFlush(responsePacket);
        }else {
            System.out.println("["+packet.getToUserId()+"不在线");
        }
    }

    private MessageResponsePacket receiveMessage(MessageRequestPacket request){
        System.out.println("accept client message" + request.getMessage());
        MessageResponsePacket response = new MessageResponsePacket();
        response.setMessage("receive message is 哈哈哈, your message is:" + request.getMessage());
        return response;
    }
}
