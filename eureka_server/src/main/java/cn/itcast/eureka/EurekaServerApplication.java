package cn.itcast.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * EnableEurekaServer 激活EurekaServer
 *
 * @author huangding
 * @version 1.0
 * @date 2020/12/5
 */
@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
        log.info("EurekaServerApplication Is Running");
    }
}
