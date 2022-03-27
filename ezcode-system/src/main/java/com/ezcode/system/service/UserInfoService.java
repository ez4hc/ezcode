package com.ezcode.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ezcode.system.entity.UserInfo;

/**
 * @author qq102
 * @description 针对表【user_info(用户信息表)】的数据库操作Service
 * @createDate 2022-03-26 18:44:41
 */
public interface UserInfoService extends IService<UserInfo> {

    boolean insertUser(UserInfo userInfo);

    UserInfo findByUserName(String userName);

}
