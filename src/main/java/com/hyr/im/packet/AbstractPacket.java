package com.hyr.im.packet;


import java.io.Serializable;

public abstract class AbstractPacket implements Serializable {
    private Byte version = 1;
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
