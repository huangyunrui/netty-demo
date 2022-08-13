package com.hyr.im.command;

import com.hyr.im.packet.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {
    private static final String USER_ID_SPLITER = ",";
    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket requestPacket = new CreateGroupRequestPacket();
        System.out.println("【创建群聊】 输入userId列表, userId用逗号分隔");
        String userIds = scanner.nextLine();
        requestPacket.setUserIds(Arrays.asList(userIds.split(USER_ID_SPLITER)));
        channel.writeAndFlush(requestPacket);
    }
}
