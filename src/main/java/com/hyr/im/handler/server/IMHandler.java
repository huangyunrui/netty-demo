package com.hyr.im.handler.server;

import com.hyr.im.common.RequestCodeEnums;
import com.hyr.im.packet.AbstractPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<AbstractPacket> {
    public static final IMHandler INSTANCE = new IMHandler();
    private Map<Byte, SimpleChannelInboundHandler<? extends AbstractPacket>> handlerMap;

    public IMHandler(){
        handlerMap = new HashMap<>();
        handlerMap.put(RequestCodeEnums.MESSAGE_REQUEST.getCode(), MessageRequestHandler.INSTANCE);
        handlerMap.put(RequestCodeEnums.CREATE_GROUP_REQUEST.getCode(), CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(RequestCodeEnums.LIST_GROUP_MEMBER_REQUEST.getCode(), ListGroupMemberRequestHandler.INSTANCE);
        handlerMap.put(RequestCodeEnums.SEND_TO_GROUP_REQUEST.getCode(), GroupMessageRequestHandler.INSTANCE);
        handlerMap.put(RequestCodeEnums.JOIN_GROUP_REQUEST.getCode(), JoinGroupRequestHandler.INSTANCE);
    }
    /**
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractPacket msg) throws Exception {
        handlerMap.get(msg.getCommand()).channelRead(ctx,msg);
    }
}
