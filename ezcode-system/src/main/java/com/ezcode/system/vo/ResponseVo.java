package com.ezcode.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hc
 * @Date: 2022/1/17 20:27
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseVo<D> {
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 操作的提示信息
     */
    private String msg;
    /**
     * 获取数据
     */
    private D data;
}
