package com.zz.garbageclassification.model.bean.enums;

public enum Grabage {

    RECY(1,"可回收垃圾"),
    HARMFUL(2,"有害垃圾"),
    DRY(3,"干垃圾"),
    WET(4,"湿垃圾"),
    OTHER(5,"未知类型垃圾"),

    ;
    public final int code;
    public final String gName;
    Grabage(int code,String gName) {
        this.code = code;
        this.gName = gName;
    }
}
