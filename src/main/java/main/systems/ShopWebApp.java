package main.systems;

import main.systems.data.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ShopWebApp {
    private static SessionFactory factory;
    private static final Logger logger = LogManager.getLogger(ShopWebApp.class);

    public static void init() {
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ShopWebApp.class, args);
        try {
            init();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            Product productDb = session.get(Product.class,1L);
            System.out.println("productDb = " + productDb);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
        logger.info("Starting app");
    }

}
