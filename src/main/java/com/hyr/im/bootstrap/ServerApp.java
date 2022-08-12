package com.hyr.im.bootstrap;

import com.hyr.im.handler.*;
import com.hyr.im.handler.server.AuthHandler;
import com.hyr.im.handler.server.LoginRequestHandler;
import com.hyr.im.handler.server.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerApp {
    public static void main(String[] args) {
        EventLoopGroup acceptGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(acceptGroup, workGroup);
            bootstrap.channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //包偏移量， 报文长度， 包最大长度

//                            socketChannel.pipeline().addLast("life",new LifeCycleTestHandler());
                            socketChannel.pipeline().addLast("spiter",new Spliter());
                            socketChannel.pipeline().addLast("decoder", new PacketDecoder());
                            socketChannel.pipeline().addLast("loginHandler", new LoginRequestHandler());
                            socketChannel.pipeline().addLast("authHandler", new AuthHandler());
                            socketChannel.pipeline().addLast("message", new MessageRequestHandler());
                            socketChannel.pipeline().addLast("encoder", new PacketEncoder());
                        }
                    });
            ChannelFuture future = bootstrap.bind(8888).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            acceptGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
