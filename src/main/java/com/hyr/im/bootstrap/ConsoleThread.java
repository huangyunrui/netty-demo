package com.hyr.im.bootstrap;

import com.hyr.im.command.ConsoleCommandManager;
import com.hyr.im.command.LoginConsoleCommand;
import com.hyr.im.utils.LoginUtils;
import io.netty.channel.Channel;

import java.util.Scanner;

public class ConsoleThread extends Thread{
    private Channel channel;
    Scanner scanner = new Scanner(System.in);
    ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
    LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();

    public ConsoleThread(Channel channel){
        this.channel = channel;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()){
            if (!LoginUtils.hasLogin(channel)){
                loginConsoleCommand.exec(scanner, channel);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                consoleCommandManager.exec(scanner, channel);
            }
        }
    }
}
