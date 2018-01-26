package com.zzw.vo;

/**
 * @author zzw
 * @see
 * @since 2018/1/26
 */
public class UserVo {

    private String name;
    private String password;

    public UserVo(){

    }

    public UserVo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
