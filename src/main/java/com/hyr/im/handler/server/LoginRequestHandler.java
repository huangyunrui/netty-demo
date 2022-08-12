package com.hyr.im.handler.server;

import com.hyr.im.common.Session;
import com.hyr.im.common.SessionUtils;
import com.hyr.im.packet.LoginRequestPacket;
import com.hyr.im.packet.LoginResponsePacket;
import com.hyr.im.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登录逻辑出来器
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket request) throws Exception {
        LoginResponsePacket response = new LoginResponsePacket();
        if (valid(request)) {
            response.setSuccess(Boolean.TRUE);
            Session session = new Session();
            session.setUserId(String.valueOf(System.currentTimeMillis()));
            session.setUserName(request.getUsername());
            SessionUtils.bindSession(session, ctx.channel());
            System.out.println(session);
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
