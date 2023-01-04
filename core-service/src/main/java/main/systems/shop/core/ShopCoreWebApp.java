package main.systems.shop.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class ShopCoreWebApp {
    public static void main(String[] args) {
        SpringApplication.run(ShopCoreWebApp.class, args);
        log.info("Starting app");
    }

}
