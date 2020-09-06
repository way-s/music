package cn.huanhu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author m
 * @className WebMvcConfig
 * @description webMvcConfig
 * @date 2020/9/3
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加Cors映射 解决跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书
                .allowCredentials(true)
                //设置允许方法
                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
                //设置允许的header属性
                .allowedHeaders("*")
                //跨域允许时间
                .maxAge(3600);
    }
}
