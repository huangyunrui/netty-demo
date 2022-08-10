package com.hyr.im.packet;

import com.hyr.im.common.RequestCodeEnums;

public class LoginPacket extends AbstractPacket{
    private Integer userId;
    private String password;
    private String username;

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.Login.getCode();
    }

    public LoginPacket(Integer userId, String password, String username) {
        this.userId = userId;
        this.password = password;
        this.username = username;
    }
}
