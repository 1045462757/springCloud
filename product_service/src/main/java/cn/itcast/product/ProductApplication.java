package cn.itcast.product;

import cn.itcast.product.config.StartCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * EnableDiscoveryClient 激活EurekaClient,新版本可不写
 *
 * @author huangding
 * @version 1.0
 * @date 2020/12/5
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class ProductApplication {

    public static void main(String[] args) {
        new StartCommand(args);
        SpringApplication.run(ProductApplication.class, args);
        log.info("ProductApplication Is Running");
    }
}
