package com.ezcode.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezcode.system.dao.ParamConfigMapper;
import com.ezcode.system.entity.ParamConfig;
import com.ezcode.system.service.ParamConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qq102
 * @description 针对表【param_config(参数配置表)】的数据库操作Service实现
 * @createDate 2022-03-26 16:47:46
 */
@Service
public class ParamConfigServiceImpl extends ServiceImpl<ParamConfigMapper, ParamConfig>
        implements ParamConfigService {

    @Autowired
    private ParamConfigMapper paramConfigMapper;

    @Override
    public List<ParamConfig> finAllParam() {
        return paramConfigMapper.selectList(new QueryWrapper<>());
    }
}




