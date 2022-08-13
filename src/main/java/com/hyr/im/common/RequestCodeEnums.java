package com.hyr.im.common;

public enum RequestCodeEnums {
    LOGIN_REQUEST(new Byte((byte) 1),"login"),
    LOGIN_RESPONSE(new Byte((byte) 2),"login"),
    MESSAGE_REQUEST(new Byte((byte) 3),"login"),
    MESSAGE_RESPONSE(new Byte((byte) 4),"login"),
    CREATE_GROUP_REQUEST(new Byte((byte) 5),"login"),
    CREATE_GROUP_RESPONSE(new Byte((byte) 6),"login"),
    JOIN_GROUP_REQUEST(new Byte((byte) 7),"login"),
    JOIN_GROUP_RESPONSE(new Byte((byte) 8),"login"),
    LIST_GROUP_MEMBER_REQUEST(new Byte((byte) 9),"login"),
    LIST_GROUP_MEMBER_RESPONSE(new Byte((byte) 10),"login");
    private Byte code;
    private String describe;



    RequestCodeEnums(Byte code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
