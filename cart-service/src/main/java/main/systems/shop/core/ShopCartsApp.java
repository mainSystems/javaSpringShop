package main.systems.shop.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ShopCartsApp {
    public static void main(String[] args) {
        SpringApplication.run(ShopCartsApp.class, args);
        log.info("Starting app");
    }
}