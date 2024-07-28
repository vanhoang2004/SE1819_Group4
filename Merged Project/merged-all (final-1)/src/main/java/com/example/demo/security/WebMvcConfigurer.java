package com.example.demo.security;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;

public interface WebMvcConfigurer {
    void addResourceHandlers(ResourceHandlerRegistration registry);
}
