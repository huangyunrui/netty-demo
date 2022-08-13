package com.hyr.im.packet.response;

import com.hyr.im.common.RequestCodeEnums;
import com.hyr.im.packet.AbstractPacket;
import lombok.Data;

@Data
public class MessageResponsePacket extends AbstractPacket {
    private String message;
    private String fromUserId;
    private String fromUserName;

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.MESSAGE_RESPONSE.getCode();
    }

}
