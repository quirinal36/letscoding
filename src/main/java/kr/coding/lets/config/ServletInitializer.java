package kr.coding.lets.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.coding.lets.LetsApplication;

@Configuration
@ComponentScan(basePackages = {"kr.coding.lets"})
public class ServletInitializer extends SpringBootServletInitializer implements WebMvcConfigurer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LetsApplication.class);
    }
}
