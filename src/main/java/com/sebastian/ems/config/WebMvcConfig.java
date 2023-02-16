package com.sebastian.ems.config;

import com.sebastian.ems.component.StringToEmployeeDtoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final StringToEmployeeDtoConverter stringToEmployeeDtoConverter;

    public WebMvcConfig(StringToEmployeeDtoConverter stringToEmployeeDtoConverter) {
        this.stringToEmployeeDtoConverter = stringToEmployeeDtoConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToEmployeeDtoConverter);
    }
}
