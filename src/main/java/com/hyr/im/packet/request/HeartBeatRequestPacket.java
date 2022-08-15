package com.hyr.im.packet.request;

import com.hyr.im.common.RequestCodeEnums;
import com.hyr.im.packet.AbstractPacket;
import lombok.Data;

@Data
public class HeartBeatRequestPacket extends AbstractPacket {

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.HEART_BEAT_REQUEST.getCode();
    }

}
