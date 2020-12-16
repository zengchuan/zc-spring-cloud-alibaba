package com.zengc.user;

import com.zengc.ZCApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName TestLauncher
 * @Description:
 * @Author baixa
 * @Date 2019/11/7
 * @Version V1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZCApplication.class, args);
    }
}
