package main.systems.shop.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("main.systems.shop.api")
public class ShopOrderWebApp {
    public static void main(String[] args) {
        SpringApplication.run(ShopOrderWebApp.class, args);
    }
}
