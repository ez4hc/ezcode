package com.ezcode.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 操作日志表
 * @TableName log_info
 */
@TableName(value ="log_info")
@Data
public class LogInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long logId;

    /**
     * 
     */
    private Long qryUser;

    /**
     * 
     */
    private String qryPath;

    /**
     * 
     */
    private String qryParam;

    /**
     * 
     */
    private Date qryDate;

    /**
     * 
     */
    private String result;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}