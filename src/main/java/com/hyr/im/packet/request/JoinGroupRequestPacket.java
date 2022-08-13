package com.hyr.im.packet.request;

import com.hyr.im.common.RequestCodeEnums;
import com.hyr.im.packet.AbstractPacket;
import lombok.Data;

@Data
public class JoinGroupRequestPacket extends AbstractPacket {
    private Integer groupId;


    @Override
    public Byte getCommand() {
        return RequestCodeEnums.JOIN_GROUP_REQUEST.getCode();
    }

}
