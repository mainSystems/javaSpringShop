package main.systems.shop.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("main.systems.shop.api")
public class ShopCoreWebApp {
    public static void main(String[] args) {
        SpringApplication.run(ShopCoreWebApp.class, args);
    }
}
