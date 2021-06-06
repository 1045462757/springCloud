package cn.itcast.eureka.config;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Eureka服务上下线监控
 *
 * @author huangding
 * @version 1.0
 * @date 2020/11/10
 */
@Component
@Slf4j
public class EurekaStateChangeListener {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        log.debug(event.getServerId() + "\t" + event.getAppName() + " 服务下线");
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        log.debug(instanceInfo.getAppName() + "进行注册");
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        log.debug(event.getServerId() + "\t" + event.getAppName() + " 服务进行续约");
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        log.debug("Eureka Server 可用");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        log.debug("Eureka Server 启动");
    }
}
