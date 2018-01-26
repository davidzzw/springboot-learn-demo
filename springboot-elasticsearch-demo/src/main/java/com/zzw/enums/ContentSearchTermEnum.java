package com.zzw.enums;

public enum ContentSearchTermEnum {

    // 标题
    TITLE("title"),
    // 内容
    CONTENT("content");

    ContentSearchTermEnum(String name){
        this.name = name;
    }
    /**
     * 搜索字段
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
