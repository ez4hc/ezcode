package com.ezcode.common.config;

import com.ezcode.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: hc
 * @Date: 2022/3/26 19:40
 */
@Configuration
public class RequestInterceptor implements WebMvcConfigurer {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

                        String token = request.getHeader("user-token");

                        if (token != null && redisUtil.get(token) != null) {
                            redisUtil.set(token);
                            return true;
                        }
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return false;
                    }
                }).excludePathPatterns("/user/register", "/user/login")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }
}
