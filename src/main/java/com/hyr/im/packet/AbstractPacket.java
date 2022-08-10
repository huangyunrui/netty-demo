package com.hyr.im.packet;


public abstract class AbstractPacket {
    private Byte version = 1;
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
