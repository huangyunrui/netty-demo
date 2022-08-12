package com.hyr.im.handler.server;

import com.hyr.im.common.SessionUtils;
import com.hyr.im.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtils.hasLogin(ctx.channel())){
            ctx.channel().close();
        }else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (SessionUtils.hasLogin(ctx.channel())){
            System.out.println("当前链接登录验证完成，无需再次校验 AuthHandler移除");
        }else {
            System.out.println("无法登录验证， 关闭链接");
        }
    }
}
