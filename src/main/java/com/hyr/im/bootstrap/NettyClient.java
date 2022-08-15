package com.hyr.im.bootstrap;

import com.hyr.im.handler.PacketCodecHandler;
import com.hyr.im.handler.PacketDecoder;
import com.hyr.im.handler.PacketEncoder;
import com.hyr.im.handler.Spliter;
import com.hyr.im.handler.client.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public void connect(String ip, int port) {
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
                    socketChannel.pipeline().addLast("packetCodec", PacketCodecHandler.INSTANCE);
                    socketChannel.pipeline().addLast("loginResponse", LoginResponseHandler.INSTANCE);
                    socketChannel.pipeline().addLast("createGroup",  CreateGroupResponseHandler.INSTANCE);
                    socketChannel.pipeline().addLast("joinGroup", JoinGroupResponseHandler.INSTANCE);
                    socketChannel.pipeline().addLast("listGroupMember", ListGroupMemberResponseHandler.INSTANCE);
                    socketChannel.pipeline().addLast("groupMessage", GroupMessageResponseHandler.INSTANCE);
                    socketChannel.pipeline().addLast("messageResponse", MessageResponseHandler.INSTANCE);
//                    socketChannel.pipeline().addLast("encoder", new PacketEncoder());
                }
            });

            final ChannelFuture localhost = bootstrap.connect(ip, port)
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
        new ConsoleThread(channel).start();
    }
}
