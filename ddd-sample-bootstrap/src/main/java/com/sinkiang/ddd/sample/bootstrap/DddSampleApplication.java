package com.sinkiang.ddd.sample.bootstrap;

import com.sinkiang.ddd.sample.common.id.snowball.SnowBallGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author dengxj
 * @date 2022/7/20 10:10
 */
@SpringBootApplication(scanBasePackages = {"com.sinkiang.ddd.sample"})
@MapperScan("com.sinkiang.ddd.sample.infrastructure.repository.mapper")
public class DddSampleApplication {

    @Bean
    public void snowBallGenerator() {
        SnowBallGenerator.init(1);
    }

    public static void main(String[] args) {
        SpringApplication.run(DddSampleApplication.class, args);
    }

}
