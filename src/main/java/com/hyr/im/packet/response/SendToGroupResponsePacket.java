package com.hyr.im.packet.response;

import com.hyr.im.common.RequestCodeEnums;
import com.hyr.im.packet.AbstractPacket;
import lombok.Data;

@Data
public class SendToGroupResponsePacket extends AbstractPacket {
    private Integer groupId;
    private Boolean success;
    private String message;
    private String fromUser;


    @Override
    public Byte getCommand() {
        return RequestCodeEnums.SEND_TO_GROUP_RESPONSE.getCode();
    }

}
