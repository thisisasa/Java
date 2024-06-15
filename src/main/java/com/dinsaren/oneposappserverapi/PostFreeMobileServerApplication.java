package com.dinsaren.oneposappserverapi;

import com.dinsaren.oneposappserverapi.startup.Startup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
@Configuration
public class PostFreeMobileServerApplication extends SpringBootServletInitializer {
    private final Startup startup;

    public PostFreeMobileServerApplication(Startup startup) {
        this.startup = startup;
    }

    public static void main(String[] args) {
        SpringApplication.run(PostFreeMobileServerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PostFreeMobileServerApplication.class);
    }

    @PostConstruct
    private void init() {
        log.info("Initializing Application ...");
        log.info("Initializing Response Manager ...");
        startup.initApiMigrate();
    }
}
