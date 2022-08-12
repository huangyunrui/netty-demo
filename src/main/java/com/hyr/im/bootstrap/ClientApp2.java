package com.hyr.im.bootstrap;

import com.hyr.im.handler.PacketDecoder;
import com.hyr.im.handler.PacketEncoder;
import com.hyr.im.handler.Spliter;
import com.hyr.im.handler.client.LoginResponseHandler;
import com.hyr.im.handler.client.MessageResponseHandler;
import com.hyr.im.packet.LoginRequestPacket;
import com.hyr.im.packet.MessageRequestPacket;
import com.hyr.im.utils.LoginUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        new Thread(()->{
            while (!Thread.interrupted()){
                if (!LoginUtils.hasLogin(channel)){
                    System.out.println("请输入用用户名:");
                    String username = scanner.nextLine();
                    LoginRequestPacket loginRequestPacket = new LoginRequestPacket(username,"pwd");
                    channel.writeAndFlush(loginRequestPacket);
                }else {
                    System.out.println("请输入接收者:");
                    String toUserId = scanner.nextLine();
                    System.out.println("请输入消息:");
                    String message = scanner.nextLine();
                    MessageRequestPacket requestPacket = new MessageRequestPacket();
                    requestPacket.setMessage(message);
                    requestPacket.setToUserId(toUserId);
                    channel.writeAndFlush(requestPacket);
                }
            }
        }).start();
    }
}
