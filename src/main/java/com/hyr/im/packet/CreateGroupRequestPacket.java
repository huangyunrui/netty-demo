package com.hyr.im.packet;

import com.hyr.im.common.RequestCodeEnums;
import lombok.Data;

import java.util.List;

@Data
public class CreateGroupRequestPacket extends AbstractPacket{
    private List<String> userIds;
    private String groupName;

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.CREATE_GROUP_REQUEST.getCode();
    }

}
