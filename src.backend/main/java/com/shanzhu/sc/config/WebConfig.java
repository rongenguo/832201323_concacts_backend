package com.shanzhu.sc.config;

import com.shanzhu.sc.utils.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //在windows环境下的图片存放资源路径
        String winPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\";
        //在Linux环境下的图片存放资源路径
        String linuxPath = "/usr/local/my_project/files/";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //windows系统
            //第一个方法设置访问路径前缀，第二个方法设置资源路径
            registry.addResourceHandler("/files/**").
                    addResourceLocations("file:" + winPath);
        } else {//linux系统
            registry.addResourceHandler("/files/**").
                    addResourceLocations("file:" + linuxPath);
        }
        super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**").allowedOriginPatterns("*")
          .allowCredentials(true)
          .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS")
          .maxAge(3600 * 24);
    }
  // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    //添加自定义拦截器和拦截路径，此处对所有请求进行拦截，除了登录界面和登录接口
    registry.addInterceptor(appInterceptor())
        .addPathPatterns("/api/sms/**")//添加拦截路径,拦截所有
        .excludePathPatterns("/login"); // 排除的拦截路径
    super.addInterceptors(registry);
  }

  @Bean
  public HandlerInterceptor appInterceptor(){
    return new AuthenticationInterceptor();
  }
}
