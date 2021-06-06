package cn.itcast.gateway.filter;

import cn.itcast.gateway.config.BasicConf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ip过滤
 *
 * @author tiger
 * @version 1.0
 * @date 2021/3/14
 */
@Component
@Slf4j
public class IpFilter implements GlobalFilter, Ordered {

    @Resource
    private BasicConf basicConf;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        log.info("[IpFilter]-->ip:{},path:{}", exchange.getRequest().getRemoteAddress(), path);
        // TODO 记录访问日志

        // 白名单过滤
        String apiWhiteStr = basicConf.getApiWhiteStr();
        List<String> whiteApis = Arrays.asList(apiWhiteStr.split(","));
        if (whiteApis.contains(path)) {
            return chain.filter(exchange);
        }

        // path uri过滤
        for (String api : whiteApis) {
            if (api.contains("{") && api.contains("}")) {
                if (api.split("/").length == path.split("/").length) {
                    String reg = api.replaceAll("\\{.*}", ".*{1,}");
                    Pattern pattern = Pattern.compile(reg);
                    Matcher matcher = pattern.matcher(path);
                    if (matcher.find()) {
                        return chain.filter(exchange);
                    }
                }
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
