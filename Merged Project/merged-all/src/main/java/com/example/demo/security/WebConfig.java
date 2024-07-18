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
        // Handling questionbank directory
        Path quesUploadDir = Paths.get("./questionbank");
        String quesUploadPath = quesUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/questionbank/**")
                .addResourceLocations("file:/" + quesUploadPath + "/");

        // Handling documentbank directory in src/main/resources/static
        Path docUploadDir = Paths.get("./src/main/resources/static/documentbank");
        String docUploadPath = docUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/documentbank/**")
                .addResourceLocations("file:/" + docUploadPath + "/");
    }
}
