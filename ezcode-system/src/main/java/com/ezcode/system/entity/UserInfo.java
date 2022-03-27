package com.ezcode.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息表
 *
 * @TableName user_info
 */
@TableName(value = "user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    private String userName;

    private String userPwd;

    private Integer roleId;
    /**
     * 用户状态 1：有效 2：失效 3：屏蔽
     */
    private Integer state;

    private String createDate;

    private String modifyDate;

    private Integer loginCount;

    private String lastLoginDate;

    private String remark;

    private String userNbr;

    private String userMail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}