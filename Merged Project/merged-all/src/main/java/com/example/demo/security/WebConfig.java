package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path quesUploadDir = Paths.get("./questionbank");
        String quesUploadPath= quesUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/questionbank/**")
                .addResourceLocations("file:/"+quesUploadPath+"/");

        // Handle PDF and Word files in the documentbank directory
        Path docUploadDir = Paths.get(".static/documentbank");
        String docUploadPath = docUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler(".static/documentbank/**")
                .addResourceLocations("file:/" + docUploadPath + "/");
    }


}
