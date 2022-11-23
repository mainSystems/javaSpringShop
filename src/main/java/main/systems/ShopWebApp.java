package main.systems;

import main.systems.data.HibernateUtils;
import main.systems.data.Product;
import main.systems.data.ProductRepositoryDao;
import main.systems.repositories.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class ShopWebApp {
//    @Autowired
//    private HibernateUtils hibernateUtils;
    private static final Logger logger = LogManager.getLogger(ShopWebApp.class);
    public static void main(String[] args) {
        SpringApplication.run(ShopWebApp.class, args);

//        HibernateUtils hibernateUtils = new HibernateUtils();
//        hibernateUtils.init();

//        try {
//            ProductRepositoryDao productDao = new ProductRepository(hibernateUtils);
//            List<Product> products = productDao.getProducts();
//            Product product = productDao.getProductsId(3L);
//            System.out.println("productDb = " + product);
//            System.out.println("productsDb = " + products);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            hibernateUtils.shutdown();
//        }
        logger.info("Starting app");
    }

}
