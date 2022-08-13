package com.hyr.im.app;

import com.hyr.im.bootstrap.NettyClient;
import com.hyr.im.bootstrap.NettyServer;

public class Client1 {
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect("localhost",8888);
    }
}
