package com.hyr.im.common;

public enum RequestCodeEnums {
    Login(new Byte((byte) 1),"lgoin");
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
