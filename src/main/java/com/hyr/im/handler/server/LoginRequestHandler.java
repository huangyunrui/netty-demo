package com.hyr.im.handler.server;

import com.hyr.im.common.Session;
import com.hyr.im.common.SessionUtils;
import com.hyr.im.packet.request.LoginRequestPacket;
import com.hyr.im.packet.response.LoginResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登录逻辑出来器
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket request) throws Exception {
        LoginResponsePacket response = new LoginResponsePacket();
        if (valid(request)) {
            response.setSuccess(Boolean.TRUE);
            Session session = new Session();
            session.setUserId(String.valueOf(System.currentTimeMillis()));
            session.setUserName(request.getUsername());
            SessionUtils.bindSession(session, ctx.channel());
            System.out.println("["+session.getUserName()+"] 登录成功，用户id:"+session.getUserId());
        } else {
            response.setSuccess(Boolean.FALSE);
            response.setReason("login fair");
        }
        ctx.channel().writeAndFlush(response);
    }

    private boolean valid(LoginRequestPacket loginPacket) {
        return true;
    }
}
