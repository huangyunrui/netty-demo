package com.hyr.im.packet.response;

import com.hyr.im.common.RequestCodeEnums;
import com.hyr.im.packet.AbstractPacket;
import lombok.Data;

import java.util.List;

@Data
public class CreateGroupResponsePacket extends AbstractPacket {
    private Boolean success;
    private Integer groupId;
    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.CREATE_GROUP_RESPONSE.getCode();
    }

}
