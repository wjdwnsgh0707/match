package com.shinhan.match.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadPath = System.getProperty("user.dir") + "/uploads/";

        // http://localhost:8080/images/파일명 요청이 오면 서버의 uploads/ 폴더에서 파일을 찾아 보여줌
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + uploadPath);
    }
}