package com.hyr.im.bootstrap;

import com.hyr.im.handler.PacketDecoder;
import com.hyr.im.handler.PacketEncoder;
import com.hyr.im.handler.Spliter;
import com.hyr.im.handler.client.LoginResponseHandler;
import com.hyr.im.handler.client.MessageResponseHandler;
import com.hyr.im.packet.MessageRequestPacket;
import com.hyr.im.utils.LoginUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientApp2 {
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
                    socketChannel.pipeline().addLast("spiter",new Spliter());
                    socketChannel.pipeline().addLast("decoder", new PacketDecoder());
                    socketChannel.pipeline().addLast("loginResponseHandler", new LoginResponseHandler());
                    socketChannel.pipeline().addLast("messageResponse", new MessageResponseHandler());
                    socketChannel.pipeline().addLast("encoder", new PacketEncoder());                }
            });

            final ChannelFuture localhost = bootstrap.connect("localhost", 8888)
                    .addListener(future -> {
                        if (future.isSuccess()){
                            Channel channel = ((ChannelFuture) future).channel();
//                            startConsoleThread(channel);

                            MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                            messageRequestPacket.setMessage("hello");
                            messageRequestPacket.setToUserId("1660291953645");
                            channel.writeAndFlush(messageRequestPacket);
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
                    messageRequestPacket.setToUserId("1660290585698");
                    channel.writeAndFlush(messageRequestPacket);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
