package com.ezcode.common.enums;

import java.text.MessageFormat;

/**
 * @Author: hc
 * @Date: 2022/3/26 17:07
 */
public enum SystemEnums {

    /**
     * 接口调用结果编码枚举值
     */
    RESPONSE_SUCCESS(0, "系统调用成功"),
    RESPONSE_FAILE(-1, "系统调用失败");

    private int code;
    private String msg;

    SystemEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg(Object... args) {
        return new MessageFormat(this.msg).format(args);
    }
}
