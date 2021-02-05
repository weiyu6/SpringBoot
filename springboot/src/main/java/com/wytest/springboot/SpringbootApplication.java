package com.wytest.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/config/applicationContext.xml")
public class SpringbootApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringbootApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
        logger.info("服务启动成功@@@@@@@@@@@@@@@@@@@");
    }

}
