package com.hyr.im.packet.response;

import com.hyr.im.common.RequestCodeEnums;
import com.hyr.im.common.Session;
import com.hyr.im.packet.AbstractPacket;
import lombok.Data;

import java.util.List;

@Data
public class ListGroupMemberResponsePacket extends AbstractPacket {
    private List<Session> members;
    private Integer groupId;

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.LIST_GROUP_MEMBER_RESPONSE.getCode();
    }

}
