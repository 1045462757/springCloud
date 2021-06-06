package cn.itcast.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * BasicConf
 *
 * @author tiger
 * @version 1.0
 * @date 2021/3/14
 */
@Data
@Configuration
public class BasicConf {

    /**
     * api接口白名单
     */
    @Value("${apiWhiteStr:/auth/login}")
    private String apiWhiteStr;
}
