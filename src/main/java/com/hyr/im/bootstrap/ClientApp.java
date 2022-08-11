package com.hyr.im.bootstrap;

import com.hyr.im.handler.ClientHandler;
import com.hyr.im.packet.MessageRequestPacket;
import com.hyr.im.packet.PacketCodeC;
import com.hyr.im.utils.LoginUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientApp {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE,true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ClientHandler());
                }
            });
            final ChannelFuture localhost = bootstrap.connect("localhost", 8888)
                    .addListener(future -> {
                        if (future.isSuccess()){
                            Channel channel = ((ChannelFuture) future).channel();
                            startConsoleThread(channel);
                        }
                    });
            localhost.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(()->{
            while (!Thread.interrupted()){
                if (LoginUtils.hasLogin(channel)){
                    System.out.println("send message");
                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setMessage("hello");
                    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), messageRequestPacket);
                    channel.writeAndFlush(byteBuf);
                }
            }
        }).start();
    }
}
