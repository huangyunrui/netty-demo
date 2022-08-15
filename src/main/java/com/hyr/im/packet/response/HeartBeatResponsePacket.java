package com.hyr.im.packet.response;

import com.hyr.im.common.RequestCodeEnums;
import com.hyr.im.packet.AbstractPacket;
import lombok.Data;

@Data
public class HeartBeatResponsePacket extends AbstractPacket {

    @Override
    public Byte getCommand() {
        return RequestCodeEnums.HEART_BEAT_RESPONSE.getCode();
    }

}
