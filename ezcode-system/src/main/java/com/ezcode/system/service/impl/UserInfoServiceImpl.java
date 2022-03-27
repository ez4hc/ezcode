package com.ezcode.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezcode.common.utils.DateUtil;
import com.ezcode.system.dao.UserInfoMapper;
import com.ezcode.system.entity.UserInfo;
import com.ezcode.system.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qq102
 * @description 针对表【user_info(用户信息表)】的数据库操作Service实现
 * @createDate 2022-03-26 18:44:41
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int insertUser(UserInfo userInfo) {

        return userInfoMapper.insert(userInfo);
    }

    @Override
    public UserInfo findByUserName(String userName) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        return userInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public int updateLogInfo(UserInfo userInfo) {
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userInfo.getUserId())
                .set("login_count", userInfo.getLoginCount() + 1)
                .set("last_login_date", DateUtil.getCurrentTime());

        return userInfoMapper.update(null, updateWrapper);
    }
}




