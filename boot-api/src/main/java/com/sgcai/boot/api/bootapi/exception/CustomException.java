package com.sgcai.boot.api.bootapi.exception;

public class CustomException extends Exception {
    private String msg;
    private String msgText;
    private String msgUrl;

    public CustomException(String msg, String msgText) {
        this.msg = msg;
        this.msgText = msgText;
    }

    public void setMsgUrl(String msgUrl) {
        this.msgUrl = msgUrl;
    }

    public String getMsg() {
        return msg;
    }

    public String getMsgText() {
        return msgText;
    }

    public String getMsgUrl() {
        return msgUrl;
    }
}
