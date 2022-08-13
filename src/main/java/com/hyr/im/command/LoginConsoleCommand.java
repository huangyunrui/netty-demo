package com.hyr.im.command;

import com.hyr.im.packet.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入用用户名:");
        String username = scanner.nextLine();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket("pwd",username);
        channel.writeAndFlush(loginRequestPacket);
    }
}
