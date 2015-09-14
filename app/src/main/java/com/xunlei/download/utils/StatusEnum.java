package com.xunlei.download.utils;

public enum StatusEnum {
    PENDING("未开始", 1), RUNNING("下载中", 2), PAUSED("已暂停", 4), SUCCESSFUL("下载完成", 8), FAILED("下载失败", 16);

    private String name;
    private int value;

    private StatusEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getName(int value) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.getValue() == value) {
                return status.name;
            }
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
