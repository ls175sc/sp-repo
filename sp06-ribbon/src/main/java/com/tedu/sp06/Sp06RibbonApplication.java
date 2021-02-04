package com.tedu.sp06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class Sp06RibbonApplication {

    @LoadBalanced       //生成一個動態代理對象，切入負載均衡代碼
    @Bean               //創建RestTemplate實例，並存入spring容器
    public RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(1000);
        factory.setReadTimeout(1000);
        return new RestTemplate(factory);
        /*return new RestTemplate();*/
    }
    //RestTemplate中默認的Factory實例中，兩個超時屬性默認是-1
    //未啟用超時，也不會觸發重試

    public static void main(String[] args) {
        SpringApplication.run(Sp06RibbonApplication.class, args);
    }

}
