package com.hyr.im.command;

import com.hyr.im.packet.request.JoinGroupRequestPacket;
import com.hyr.im.packet.request.SendToGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToGroupConsoleCommand implements ConsoleCommand{


    public void exec(Scanner scanner, Channel channel){
        SendToGroupRequestPacket requestPacket = new SendToGroupRequestPacket();
        System.out.println("请输入groupId空格消息体，发送群聊信息");
        String input = scanner.nextLine();
        String[] split = input.split(" ");

        requestPacket.setGroupId(Integer.valueOf(split[0]));
        requestPacket.setMessage(split[1]);
        channel.writeAndFlush(requestPacket);
    }


}
