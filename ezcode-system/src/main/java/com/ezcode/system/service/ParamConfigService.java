package com.ezcode.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ezcode.system.entity.ParamConfig;

import java.util.List;

/**
 * @author qq102
 * @description 针对表【param_config(参数配置表)】的数据库操作Service
 * @createDate 2022-03-26 16:47:46
 */
public interface ParamConfigService extends IService<ParamConfig> {

    List<ParamConfig> finAllParam();

}
