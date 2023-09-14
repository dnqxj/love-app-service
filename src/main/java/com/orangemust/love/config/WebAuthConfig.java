package com.orangemust.love.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class WebAuthConfig extends WebMvcConfigurationSupport {

        @Resource
        AuthInterceptor authInterceptor;

        @Override
        protected void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(authInterceptor)
                                .addPathPatterns("/**")
                                .excludePathPatterns("/hello")
                                .excludePathPatterns("/upload/**")
                                .excludePathPatterns("/user/login")
                                .excludePathPatterns("/user/register")
                                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**",
                                                "/swagger-ui.html/**");
        }

        /**
         * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
         *
         * @param registry
         */
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**").addResourceLocations(
                                "classpath:/static/");
                registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                                "classpath:/META-INF/resources/");
                registry.addResourceHandler("/webjars/**").addResourceLocations(
                                "classpath:/META-INF/resources/webjars/");
                super.addResourceHandlers(registry);
        }

}