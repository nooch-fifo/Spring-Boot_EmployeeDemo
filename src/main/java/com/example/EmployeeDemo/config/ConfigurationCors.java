package com.example.EmployeeDemo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ConfigurationCors {
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter(){
        // uses URL path patterns to select the CorsConfiguration for a request
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // container for CORS configuration along with methods to check against the actual origin, HTTP methods, and headers of a given request.
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000/");
        // allowed pre-flight request headers
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        // register mapping with params of mapping pattern and config to be used
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
