package com.example.longxin;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 制冷
 * @date 2023/6/3 15:35
 * @description OpenApiConfig
 *   swagger配置类
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().info(new Info() //
                .title("基于龙芯平台电商用户大数据分析") //
                .description("api文档") //
                .version("1.0.1"));
    }

}
