package cn.itcast.order;

import cn.itcast.order.config.StartCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * EnableFeignClients 激活FeignClient
 *
 * @author huangding
 * @version 1.0
 * @date 2020/12/5
 */
@SpringBootApplication
@EnableFeignClients
@Slf4j
public class OrderApplication {

    public static void main(String[] args) {
        new StartCommand(args);
        SpringApplication.run(OrderApplication.class, args);
        log.info("OrderApplication Is Running");
    }
}
