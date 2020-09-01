package com.cloud.askwalking.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author niuzhiwei
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.cloud.askwalking.*"})
public class AskwalkingApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AskwalkingApplication.class);
        app.run(args);
    }

}
