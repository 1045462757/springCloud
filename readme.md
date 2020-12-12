

## 1.Eureka

### 1.1 EurekaServer搭建

~~~properties
# 1.导入依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>

# 2.配置文件
spring:
  application:
    name: eureka-server

server:
  port: 9001

# 其他EurekaServer的端口
other:
  port: 9002

# 配置EurekaServer
eureka:
  instance:
    hostname: 127.0.0.1
  client:
    # 是否将自己注册到注册中心,默认为true
    register-with-eureka: true
    # 是否从Eureka中获取注册信息,默认为true
    fetch-registry: true
    # EurekaClient的请求地址
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${other.port}/eureka/
  server:
    # 自我保护,默认为开启,开发阶段关闭
    enable-self-preservation: false
    # 剔除服务间隔,默认60s
    eviction-interval-timer-in-ms: 5000
    
# 3.@EnableEurekaServer 激活EurekaServer
~~~

### 1.2 EurekaClinet搭建

~~~properties
# 1.导入依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

# 2.配置文件
spring:
  application:
    name: service-product
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=utf8
    username: root
    password: 614213
  jpa:
    database: MySQL
    show-sql: true
    open-in-view: true
    hibernate:
      ddl-auto: update

server:
  port: 9101

# 配置EurekaClient
eureka:
  instance:
    # 使用ip地址注册,默认为false
    prefer-ip-address: true
    # 向注册中心中注册服务id
    instance-id: ${spring.cloud.client.ip-address}:${server.port}
    # 心跳间隔,默认30s
    lease-renewal-interval-in-seconds: 5
    # 续约到期的时间,默认90s
    lease-expiration-duration-in-seconds: 10
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      # 多个EurekaServer之间用,隔开
      defaultZone: http://127.0.0.1:9001/eureka/,http://127.0.0.1:9002/eureka/
      
# 3.@EnableDiscoveryClient 激活EurekaClient,新版本可不写
~~~



### 1.3 Eureka源码解析

~~~

~~~





## 2.Consul

~~~

~~~







## 3.Ribbon

~~~
一个负载均衡器，Eureka一般配合Ribbon使用，Ribbon提供了客户端负载均衡的功能，Ribbon利用从Eureka中读到的服务信息，在调用服务节点提供的服务时，会合理的进行负载均衡。
~~~



### 3.1 Ribbon环境搭建

~~~properties
# 1.引入重试
<dependency>
    <groupId>org.springframework.retry</groupId>
    <artifactId>spring-retry</artifactId>
</dependency>

# 2.修改ribbon的负载均衡策略   服务名 -  ribbon - NFLoadBalancerRuleClassName : 策略
service-product:
  ribbon:
    # RoundRobinRule 轮询
    # RandomRule 随机
    # RetryRule 重试
    # WeightedResponseTimeRule 权重
    # BestAvailableRule,最佳
    # BestAvailabilityFilteringRule 可用
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RoundRobinRule
    # Ribbon的连接超时时间
    ConnectTimeout: 250
    # Ribbon的数据读取超时时间
    ReadTimeout: 1000
    # 是否对所有操作都进行重试
    OkToRetryOnAllOperations: true
    # 切换实例的重试次数
    MaxAutoRetriesNextServer: 1
    # 对当前实例的重试次数
    MaxAutoRetries: 1
~~~



### 3.2 Ribbon源码解析

~~~

~~~





## 4.Feign

~~~properties
# 1.引入依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

# 2.@EnableFeignClients 激活FeignClient

# 3.实现Feign接口
@FeignClient(name = "service-product", fallback = ProductFeignClientCallBack.class)
public interface ProductFeignClient {

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    Product findById(@PathVariable Long id);
}

# 4.fallback熔断降级
@Component
public class ProductFeignClientCallBack implements ProductFeignClient {
    @Override
    public Product findById(Long id) {
        Product product = new Product();
        product.setProductName("熔断降级");
        return product;
    }
}

# 5.开启熔断
feign:
  # 开启熔断
  hystrix:
    enabled: true
~~~





## 4.Sentinel





## 5.GateWay

~~~properties
# 环境搭建
spring:
  application:
    name: api-gate-way
  cloud:
    gateway:
      routes:
        - id: product-service
          uri: lb://service-product
          predicates:
            #- Path=/product/**
            - Path=/product-service/**
          filters:
            - RewritePath=/product-service/(?<segment>.*),/$\{segment} #路径重写过滤器

        - id: order-service
          uri: lb://service-order
          predicates:
            #- Path=/order/**
            - Path=/order-service/**
          filters:
            - RewritePath=/order-service/(?<segment>.*),/$\{segment}

      discovery:
        locator:
          # 开启根据服务名称自动转发
          enabled: true
          # 微服务名称小写
          lower-case-service-id: true

server:
  port: 8080

eureka:
  instance:
    prefer-ip-address: true
    instance-id: ${spring.cloud.client.ip-address}:${server.port}
    lease-renewal-interval-in-seconds: 5
    lease-expiration-duration-in-seconds: 10
  client:
    service-url:
      defaultZone: http://127.0.0.1:9001/eureka/,http://127.0.0.1:9002/eureka/
      # 获取服务列表的周期：5s
      registry-fetch-interval-seconds: 5
~~~

### 5.1 网关限流

~~~properties
spring:
  application:
    name: api-gate-way
  redis:
    host: 127.0.0.1
    port: 6379
    database: 0
  cloud:
    gateway:
      routes:
        - id: product-service
          uri: lb://service-product
          predicates:
            - Path=/product-service/**
          filters:
#            - name: RequestRateLimiter
#              args:
#                key-resolver: '#{@pathKeyResolver}'
#                # 令牌桶每秒填充平均速率
#                redis-rate-limiter.replenishRate: 1
#                # 令牌桶上限
#                redis-rate-limiter.burstCapacity: 3
            # 路径重写过滤器
            - RewritePath=/product-service/(?<segment>.*),/$\{segment}

        - id: order-service
          uri: lb://service-order
          predicates:
            - Path=/order-service/**
          filters:
            - name: RequestRateLimiter
              args:
                key-resolver: '#{@userKeyResolver}'
                # 令牌桶每秒填充平均速率
                redis-rate-limiter.replenishRate: 1
                # 令牌桶上限
                redis-rate-limiter.burstCapacity: 3
            - RewritePath=/order-service/(?<segment>.*),/$\{segment}

      discovery:
        locator:
          # 开启根据服务名称自动转发
          enabled: true
          # 微服务名称小写
          lower-case-service-id: true

server:
  port: 8080

eureka:
  instance:
    prefer-ip-address: true
    instance-id: ${spring.cloud.client.ip-address}:${server.port}
    lease-renewal-interval-in-seconds: 5
    lease-expiration-duration-in-seconds: 10
  client:
    service-url:
      defaultZone: http://127.0.0.1:9001/eureka/,http://127.0.0.1:9002/eureka/
      # 获取服务列表的周期：5s
      registry-fetch-interval-seconds: 5
~~~



### 5.2 nginx负载均衡网关

~~~

~~~







## 6.链路追踪 Sleuth Zipkin

~~~properties
# 引入依赖
 <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>


# 配置
logging:
  level:
    root: info
    org.springframework.web.servlet.DispatcherServlet: DEBUG
    org.springframework.cloud.sleuth: DEBUG

spring: 
  zipkin:
    base-url: http://127.0.0.1:9411/
    sender:
      # 数据的传输方式，以http请求向server发送数据
      type: web
  sleuth:
    sampler:
      # 采样比
      probability: 1
      
# zipkin数据存入数据库，zipkinServer启动命令
java -jar zipkin-server-2.12.9-exec.jar --STORAGE_TYPE=mysql --MYSQL_HOST=127.0.0.1 --MYSQL_TCP_PORT=3306 --MYSQL_USER=root --MYSQL_PASS=614213 --MYSQL_DB=zipkin

# zipkin客户端与服务端采用rabbitmq交互
# 引入依赖
 <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.amqp</groupId>
    <artifactId>spring-rabbit</artifactId>
</dependency>

# 配置 不需要base-url 将type改为rabbit
  rabbitmq:
    host: 127.0.0.1
    port: 5672
    username: guest
    password: guest
    # 重试
    listener:
      direct:
        retry:
          enabled: true
      simple:
        retry:
          enabled: true

# zipkin服务端启动命令
java -jar zipkin-server-2.12.9-exec.jar --RABBIT_ADDRESSES=127.0.0.1:5672 

~~~





## 7.SpringCloudStream





## 8.SpringCloudConfig





## 9.Apollo

