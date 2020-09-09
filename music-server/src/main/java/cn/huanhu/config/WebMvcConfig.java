package cn.huanhu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
     *  手动配置图片资源文件夹的位置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 指定到D盘下的myFile文件夹
        // 注:如果是Linux的话，直接指定文件夹路径即可，不需要指定哪个盘(Linux就一个可用盘)
//        registry.addResourceHandler("/img/songPic/**").addResourceLocations("D:/IDEA/project/testspring/music/img/singerPic/");
//        WebMvcConfigurer.super.addResourceHandlers(registry);

        // 歌手图片文件夹 D:\IDEA\project\testspring\music\img\singerPic
        registry.addResourceHandler("/img/singerPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"singerPic"+System.getProperty("file.separator")
        );
        // 歌曲图片文件夹
        registry.addResourceHandler("/img/songPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"singerPic"+System.getProperty("file.separator")
        );
        // 歌曲头像文件夹
        registry.addResourceHandler("/img/songPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"singerPic"+System.getProperty("file.separator")
        );
        // 歌曲地址文件夹
        registry.addResourceHandler("/img/songPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"singerPic"+System.getProperty("file.separator")
        );
    }

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
