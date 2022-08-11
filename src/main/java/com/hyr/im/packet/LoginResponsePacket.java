package com.hyr.im.packet;

import com.hyr.im.common.RequestCodeEnums;
import lombok.Data;

@Data
public class LoginResponsePacket extends AbstractPacket{
    private Boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.LOGIN_RESPONSE.getCode();
    }

}
