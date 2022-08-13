package com.hyr.im.command;

import com.hyr.im.packet.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
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
