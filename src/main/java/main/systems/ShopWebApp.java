package main.systems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopWebApp {
    private static final Logger logger = LogManager.getLogger(ShopWebApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ShopWebApp.class, args);
        logger.info("Starting app");
    }

}
