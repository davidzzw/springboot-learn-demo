package com.zzw.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ContentSearchTermEnum {

    // 标题
    TITLE("title"),
    // 内容
    CONTENT("content");

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
