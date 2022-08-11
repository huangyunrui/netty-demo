package com.hyr.im.packet;

import com.hyr.im.common.RequestCodeEnums;
import lombok.Data;

@Data
public class MessageResponsePacket extends AbstractPacket{
    private String message;

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.MESSAGE_RESPONSE.getCode();
    }

}
