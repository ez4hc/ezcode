package com.ezcode.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezcode.system.dao.LogInfoMapper;
import com.ezcode.system.entity.LogInfo;
import com.ezcode.system.service.LogInfoService;
import org.springframework.stereotype.Service;

/**
 * @author qq102
 * @description 针对表【log_info(操作日志表)】的数据库操作Service实现
 * @createDate 2022-03-26 18:44:34
 */
@Service
public class LogInfoServiceImpl extends ServiceImpl<LogInfoMapper, LogInfo>
        implements LogInfoService {

}




