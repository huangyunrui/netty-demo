package com.hyr.im.command;

import com.hyr.im.packet.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand{


    public void exec(Scanner scanner, Channel channel){
        JoinGroupRequestPacket requestPacket = new JoinGroupRequestPacket();
        System.out.println("请输入groupId，加入群聊");
        String groupId = scanner.nextLine();

        requestPacket.setGroupId(Integer.valueOf(groupId));
        channel.writeAndFlush(requestPacket);
    }


}
