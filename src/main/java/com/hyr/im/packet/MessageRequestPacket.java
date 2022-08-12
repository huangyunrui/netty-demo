package com.hyr.im.packet;

import com.hyr.im.common.RequestCodeEnums;
import lombok.Data;

@Data
public class MessageRequestPacket extends AbstractPacket{
    private String message;
    private String toUserId;

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.MESSAGE_REQUEST.getCode();
    }



}
