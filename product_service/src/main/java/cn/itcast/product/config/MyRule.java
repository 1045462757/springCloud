package cn.itcast.product.config;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 自定义负载均衡策略
 *
 * @author tiger
 * @version 1.0
 * @date 2021/3/6
 */
@Slf4j
public class MyRule implements IRule {

    private ILoadBalancer iLoadBalancer;

    @Override
    public Server choose(Object o) {
        List<Server> serverList = iLoadBalancer.getAllServers();
        for (Server server : serverList) {
            log.info(server.getHostPort());
        }
        return serverList.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.iLoadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return iLoadBalancer;
    }
}
