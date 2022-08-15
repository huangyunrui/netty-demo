package com.hyr.im.packet.request;

import com.hyr.im.common.RequestCodeEnums;
import com.hyr.im.packet.AbstractPacket;
import lombok.Data;

@Data
public class SendToGroupRequestPacket extends AbstractPacket {
    private Integer groupId;
    private String message;


    @Override
    public Byte getCommand() {
        return RequestCodeEnums.SEND_TO_GROUP_REQUEST.getCode();
    }

}
