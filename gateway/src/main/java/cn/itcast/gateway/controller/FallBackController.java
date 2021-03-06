package cn.itcast.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 熔断降级
 *
 * @author tiger
 * @version 1.0
 * @date 2021/3/6
 */
@RestController
public class FallBackController {

    @GetMapping("/fallback")
    public String fallback() {
        return "服务暂时不可用，请稍后再试！";
    }
}
