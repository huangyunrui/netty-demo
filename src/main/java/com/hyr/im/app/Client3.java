package com.hyr.im.app;

import com.hyr.im.bootstrap.NettyClient;

public class Client3 {
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect("localhost",8888);
    }
}
