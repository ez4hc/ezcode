package com.ezcode.system.controller;

import com.ezcode.common.utils.AesUtil;
import com.ezcode.common.utils.RedisUtil;
import com.ezcode.common.utils.ValidatorUtil;
import com.ezcode.common.enums.SystemEnums;
import com.ezcode.system.entity.UserInfo;
import com.ezcode.system.service.UserInfoService;
import com.ezcode.system.vo.ResponseVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Author: hc
 * @Date: 2022/3/26 18:55
 */
@RestController
@Api(tags = "登录管理")
@RequestMapping("/user")
@Slf4j
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AesUtil aesUtil;

    @PostMapping("/register")
    public ResponseVo registerUser(@RequestBody UserInfo userInfo) {

        String msg = valiParam(userInfo);

        if (userInfo.getUserNbr() == null && userInfo.getUserMail() == null) {
            msg = "电话号码或邮箱二选一必填";
        }

        if (userInfo.getUserNbr() != null && !ValidatorUtil.isMobile(userInfo.getUserNbr())) {
            msg = "号码输入有误!";
        }
        if (userInfo.getUserMail() != null && !ValidatorUtil.isMobile(userInfo.getUserMail())) {
            msg = "邮箱输入有误!";
        }

        if (!"".equals(msg)) {
            return new ResponseVo(SystemEnums.RESPONSE_FAILE.getCode(), msg, null);
        }

        UserInfo userInfo1 = userInfoService.findByUserName(userInfo.getUserName());
        if (userInfo1 != null) {
            return new ResponseVo(SystemEnums.RESPONSE_FAILE.getCode(), "用户名已注册", null);
        }
        try {
            userInfo.setUserPwd(aesUtil.encrypt(userInfo.getUserPwd()));
            userInfo.setRoleId(101);
            userInfo.setState(1);
            userInfo.setLoginCount(0);

            userInfoService.insertUser(userInfo);
        }
        catch (Exception e) {
            log.error("用户注册出错：", e);
            return new ResponseVo(SystemEnums.RESPONSE_FAILE.getCode(), "用户注册出错:" + e.getMessage(), null);
        }

        return new ResponseVo(SystemEnums.RESPONSE_SUCCESS.getCode(), "注册成功", null);
    }

    @PostMapping("/login")
    public ResponseVo login(@RequestBody UserInfo userInfo) {

        String msg = valiParam(userInfo);
        if (!"".equals(msg)) {
            return new ResponseVo(SystemEnums.RESPONSE_FAILE.getCode(), msg, null);
        }
        try {
            UserInfo info = userInfoService.findByUserName(userInfo.getUserName());
            if (info == null) {
                return new ResponseVo(SystemEnums.RESPONSE_FAILE.getCode(), "输入的用户名有误", null);
            }

            if (aesUtil.encrypt(userInfo.getUserPwd()).equals(info.getUserPwd())) {
                String token = UUID.randomUUID().toString().replace("-", "");
                // 更新上次登录时间以及 登录次数
                userInfoService.updateLogInfo(info);

                redisUtil.set(token, info.getUserId() + ":" + info.getUserName(), true);

                return new ResponseVo(SystemEnums.RESPONSE_SUCCESS.getCode(), "登陆成功", "user-token: " + token);
            }
        }
        catch (Exception e) {
            log.error("用户登录出错：", e);
            return new ResponseVo(SystemEnums.RESPONSE_FAILE.getCode(), "用户登录出错：" + e.getMessage(), null);
        }


        return new ResponseVo(SystemEnums.RESPONSE_FAILE.getCode(), "密码输入有误", null);
    }

    @PostMapping("/logout")
    public ResponseVo logout(HttpServletRequest request) {

        try {
            redisUtil.remove(request.getHeader("user-token"));
            return new ResponseVo(SystemEnums.RESPONSE_SUCCESS.getCode(), "注销成功", null);
        }

        catch (Exception e) {
            log.error("用户注销出错：", e);
            return new ResponseVo(SystemEnums.RESPONSE_FAILE.getCode(), "用户登录出错：" + e.getMessage(), null);
        }
    }

    private String valiParam(UserInfo userInfo) {
        String msg = "";
        if (userInfo.getUserName() == null) {
            msg = "用户名不能为空";
        }
        if (!ValidatorUtil.isUsername(userInfo.getUserName())) {
            msg = "用户名输入错误，需输入5-20位数字字母组合";
        }
        if (userInfo.getUserPwd() == null) {
            msg = "密码不能为空";
        }
        if (!ValidatorUtil.isPassword(userInfo.getUserPwd())) {
            msg = "密码输入有误，需输入5-20位数字字母或其他特殊字符组合";

        }
        return msg;
    }


}
