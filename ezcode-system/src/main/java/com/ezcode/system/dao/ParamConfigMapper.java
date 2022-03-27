package com.ezcode.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ezcode.system.entity.ParamConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author qq102
 * @description 针对表【param_config(参数配置表)】的数据库操作Mapper
 * @createDate 2022-03-26 16:47:46
 * @Entity com.ezcode.server.entity.ParamConfig
 */
@Mapper
@Component
public interface ParamConfigMapper extends BaseMapper<ParamConfig> {

}




