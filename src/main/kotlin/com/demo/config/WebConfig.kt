package com.demo.config

import com.demo.filter.AuthFilter
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@EnableWebMvc
@Configuration
class WebConfig(val authFilter: AuthFilter) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/resources/**", "/error", "/login", "/sign-up")
    }

}
