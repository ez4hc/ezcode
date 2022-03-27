package com.ezcode.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 参数配置表
 *
 * @TableName param_config
 */
@TableName(value = "param_config")
@Data
public class ParamConfig implements Serializable {
    /**
     * 参数ID
     */
    @TableId(type = IdType.AUTO)
    private Integer paramId;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 参数分组
     */
    private Integer type;

    /**
     * 参数状态
     */
    private Integer state;

    /**
     * 修改时间
     */
    private String modifyDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}