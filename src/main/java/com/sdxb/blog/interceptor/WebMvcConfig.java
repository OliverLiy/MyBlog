package com.sdxb.blog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author by: Liyu
 * @ClassName: WebMvcConfig
 * @Description: TODO
 * @Date: 2021/9/21 17:28
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private UserInterceptor userInterceptor;

    @Value("${exclude.url}")
    private String[] excludeUrl;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludeUrl);
    }
}
