package com.hyr.im.packet.request;

import com.hyr.im.common.RequestCodeEnums;
import com.hyr.im.packet.AbstractPacket;
import lombok.Data;

@Data
public class MessageRequestPacket extends AbstractPacket {
    private String message;
    private String toUserId;

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.MESSAGE_REQUEST.getCode();
    }



}
