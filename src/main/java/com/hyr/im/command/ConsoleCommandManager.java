package com.hyr.im.command;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand{
    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager(){
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
    }
    public void exec(Scanner scanner, Channel channel){
        System.out.println("请输入指令:");
        String command = scanner.nextLine();
        final ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (null != consoleCommand){
            consoleCommand.exec(scanner,channel);
        }else {
            System.err.println("无法识别["+command+"]");
        }

    }


}
