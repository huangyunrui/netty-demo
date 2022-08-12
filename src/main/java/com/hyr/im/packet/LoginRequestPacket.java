package com.hyr.im.packet;

import com.hyr.im.common.RequestCodeEnums;
import lombok.Data;

@Data
public class LoginRequestPacket extends AbstractPacket{
    private Integer userId;
    private String password;
    private String username;

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.LOGIN_REQUEST.getCode();
    }

    public LoginRequestPacket(String password, String username) {
        this.password = password;
        this.username = username;
    }
}
