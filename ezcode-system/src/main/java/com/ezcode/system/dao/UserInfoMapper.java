package com.ezcode.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ezcode.system.entity.UserInfo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author qq102
 * @description 针对表【user_info(用户信息表)】的数据库操作Mapper
 * @createDate 2022-03-26 18:44:41
 * @Entity com.ezcode.server.entity.UserInfo
 */
@Component
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}




