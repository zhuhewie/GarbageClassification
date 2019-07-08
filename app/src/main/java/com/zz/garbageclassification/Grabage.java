package com.zz.garbageclassification;

public enum Grabage {

    recy(1,"可回收垃圾"),
    harmful(2,"有害垃圾"),
    dry(3,"干垃圾"),
    wet(4,"湿垃圾"),
    other(5,"未知类型垃圾"),
    ;

    Grabage(int code,String name) {
    }
}
