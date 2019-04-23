package org.nlb.springboot03;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"org.nlb.springboot03"})
@RestController
@MapperScan("org.nlb.springboot03.dao")
public class Springboot03Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03Application.class, args);
    }

}
