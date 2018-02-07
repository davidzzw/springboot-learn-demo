package com.zzw.vo;

/**
 * @author zzw
 * @see
 * @since 2018/2/7
 */
public class ResponseBean {
    private Integer code;
    private String msg;
    private Exception exception;

    public static ResponseBean error(Exception exception) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setException(exception);
        return responseBean;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
