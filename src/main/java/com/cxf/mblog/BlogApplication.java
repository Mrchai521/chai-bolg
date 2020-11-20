package com.cxf.mblog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

/**
 * @author xfchai
 * @ClassName BlogApplication.java
 * @Description TODO
 * @createTime 2020/11/19 09:11:00
 */
@Slf4j
@SpringBootApplication
@EnableCaching
public class BlogApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BlogApplication.class, args);
        String port = context.getEnvironment().getProperty("server.port");
        log.info("mblog is start at http://localhost:" + port);
    }
}
