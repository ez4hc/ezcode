package com.ezcode.system.controller;

import com.ezcode.common.enums.SystemEnums;
import com.ezcode.system.entity.ParamConfig;
import com.ezcode.system.service.ParamConfigService;
import com.ezcode.system.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: hc
 * @Date: 2022/3/26 16:55
 */
@RestController
@Api(tags = "系统管理")
@RequestMapping("/sys")
@Slf4j
public class SysController {

    @Autowired
    private ParamConfigService paramConfigService;

    @ApiOperation("获取全部系统参数")
    @GetMapping("/param/all")
    public ResponseVo<List<ParamConfig>> finAllParam() {

        return new ResponseVo<>(SystemEnums.RESPONSE_SUCCESS.getCode(), "", paramConfigService.finAllParam());
    }

}
