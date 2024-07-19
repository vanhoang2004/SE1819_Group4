package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path quesUploadDir = Paths.get("./questionbank");
        String quesUploadPath = quesUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/questionbank/**")
                .addResourceLocations("file:/" + quesUploadPath + "/");

        Path materialsUploadDir = Paths.get("./materials");
        String materialUploadPath = materialsUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/materials/**")
                .addResourceLocations("file:/" + materialUploadPath + "/");
    }
}


