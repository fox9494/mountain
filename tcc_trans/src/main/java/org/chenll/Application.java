package org.chenll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by chenlile on 17-3-14.
 */

//@EnableAutoConfiguration////这个注解告诉Spring Boot根据添加的jar依赖猜测你想如何配置Spring
@SpringBootApplication//@SpringBootApplication 注解等价于以默认属性使用 @Configuration ， @EnableAutoConfiguration 和 @ComponentScan
//@ComponentScan
//@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
