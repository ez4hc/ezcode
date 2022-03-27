package com.ezcode;

import com.ezcode.common.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: hc
 * @Date: 2022/1/17 10:54
 */
@SpringBootTest
public class MainTest {
    @Autowired
    RedisUtil util;

    @Test
    public void add() {
        util.set("test", 1234);
    }
}
