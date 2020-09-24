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
     * 指定到D盘下的myFile文件夹
     * 注:如果是Linux的话，直接指定文件夹路径即可，不需要指定哪个盘(Linux就一个可用盘)
     * registry.addResourceHandler("/img/songPic/**").addResourceLocations("file:D:/img/singerPic/");
     * WebMvcConfigurer.super.addResourceHandlers(registry);
     *
     * 可以直接使用addResourceLocations 指定磁盘绝对路径，同样可以配置多个位置，注意路径写法需要加上file:
     *  registry.addResourceHandler("/myimgs/**").addResourceLocations("file:H:/myimgs/");
     *
     *
     *  手动配置图片资源文件夹的位置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 歌手图片 资源路径
        registry.addResourceHandler("/singerPic/**").addResourceLocations("file:E:/music/img/singerPic/");
        // 歌曲图片文件夹
        registry.addResourceHandler("/songPic/**").addResourceLocations("file:E:/music/img/songPic/");
        // 歌单图片文件夹
        registry.addResourceHandler("/songListPic/**").addResourceLocations("file:E:/music/img/songListPic/");
        // 歌曲地址文件夹
        registry.addResourceHandler("/songAd/**").addResourceLocations("file:E:/music/img/songAd/");
        // 用户头像
        registry.addResourceHandler("/CHP/**").addResourceLocations("file:E:/music/img/CHP/");

//        // 歌手图片文件夹
//        registry.addResourceHandler("/music/img/singerPic/**").addResourceLocations(
//                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "music/img"
//                        + System.getProperty("file.separator") + "singerPic" + System.getProperty("file.separator")
//        );

//        // 歌曲图片文件夹
//        registry.addResourceHandler("/img/songPic/**").addResourceLocations(
//                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
//                        +System.getProperty("file.separator")+"singerPic"+System.getProperty("file.separator")
//        );
    }

    /**
     * 添加Cors映射 解决跨域
     * @param registry 注册表
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
