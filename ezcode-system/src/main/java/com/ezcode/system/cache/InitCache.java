package com.ezcode.system.cache;

import com.ezcode.common.utils.RedisUtil;
import com.ezcode.system.entity.ParamConfig;
import com.ezcode.system.service.ParamConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: hc
 * @Date: 2022/1/18 10:54
 */
@Component
@Slf4j
public class InitCache implements ApplicationRunner {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ParamConfigService paramConfigService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("开始加载缓存.....");
        try {
            List<ParamConfig> paramConfigs = paramConfigService.finAllParam();
            for (ParamConfig paramConfig : paramConfigs) {
                redisUtil.set(paramConfig.getType() + ":" + paramConfig.getParamName(), paramConfig.getParamValue());
            }
        }
        catch (Exception e) {
            log.error("加载缓存出错：", e);
        }

        log.info("缓存加载结束.....");
    }
}
