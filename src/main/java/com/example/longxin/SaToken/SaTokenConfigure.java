package com.example.longxin.SaToken;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 制冷
 * @date 2023/7/13 16:55
 * @description SaTokenConfigure
 */
@Configuration
@Slf4j
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] str = {"/getClassifyByUser","/getUserClassifyByClassify","/SearchClassify","/Conversion_rate","/Conversion_rateByMonth","/calculateMonthlyChurnRateByMonth","/LossByMonth","/JumpOutByMonth","/UpData"};
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns(str);
    }

    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                // 拦截与排除
                .addInclude("/**").addExclude("/login","/index")

                // 全局认证函数
                .setAuth(obj -> {
                    // ...
                })

                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(obj -> {
                    // ---------- 设置跨域响应头 ----------
                    SaHolder.getResponse()
                            // 允许指定域访问跨域资源
                            .setHeader("Access-Control-Allow-Origin", "*")
                            // 允许所有请求方式
                            .setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                            // 有效时间
                            .setHeader("Access-Control-Max-Age", "3600")
                            // 允许的header参数
                            .setHeader("Access-Control-Allow-Headers", "*");

                    // 如果是预检请求，则立即返回到前端
                    SaRouter.match(SaHttpMethod.OPTIONS)
                            .back();
                })
                ;
    }
}
