package com.hyr.im.app;

import com.hyr.im.bootstrap.NettyServer;

public class ServerApp {
    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(8888);
    }
}
