package com.hyr.im.packet.response;

import com.hyr.im.common.RequestCodeEnums;
import com.hyr.im.packet.AbstractPacket;
import lombok.Data;

@Data
public class JoinGroupResponsePacket extends AbstractPacket {
    private String message;
    private Boolean success;
    private Integer groupId;

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.JOIN_GROUP_RESPONSE.getCode();
    }

}
