package main.systems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Shop {
    //    private static Cart cart;
    private static int productId;
    private static int count;
    private static boolean status = true;
    private static final Logger logger = LogManager.getLogger(Shop.class);

    public static void main(String[] args) {
        SpringApplication.run(Shop.class, args);
        logger.info("Starting app");
//        shopViaCmd();
    }

//    private static void shopViaCmd() throws IOException {
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
//
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        commandsTo();
//
//        while (status) {
//            enterCommand();
//            String str = in.readLine();
//            String[] parts = str.split("\\s");
//            String command = parts[0];
//            if (parts.length == 3) {
//                productId = Integer.parseInt(parts[1]);
//                count = Integer.parseInt(parts[2]);
//            }
//            if (!str.isEmpty()) {
//                switch (command) {
//                    case "list_products":
//                        productRepository.listAvailableProducts();
//                        break;
//                    case "new":
//                        cart = new Cart();
//                        System.out.println("Создана новая корзина: " + cart);
//                        break;
//                    case "list_cart":
//                        if (cart != null) {
//                            if (cart.getCartSize() == 0) {
//                                System.out.println("Корзина пуста: " + cart);
//                            } else {
//                                System.out.println("Используемая корзина:  " + cart);
//                                cart.listCart();
//                            }
//                        } else {
//                            System.out.println("Create cart first");
//                        }
//                        break;
//                    case "add": {
//                        if (cart != null) {
//                            Product product = productRepository.getProductsId(productId);
//                            cart.addProducts(product, count);
//                        } else {
//                            System.out.println("Create cart first");
//                        }
//                        break;
//                    }
//                    case "del": {
//                        if (cart != null) {
//                            Product product = productRepository.getProductsId(productId);
//                            cart.delProduct(product, count);
//                        } else {
//                            System.out.println("Create cart first");
//                        }
//                        break;
//                    }
//                    case "exit": {
//                        status = false;
//                        System.out.println("exiting...");
//                        in.close();
//                        break;
//                    }
//                }
//            }
//        }
//        ctx.close();
//    }

//    private static void commandsTo() {
//        System.out.println("Commands: ");
//        System.out.println("Список продуктов: list_products");
//        System.out.println("Создать новую корзину: new");
//        System.out.println("Список продуктов в корзине: list_cart");
//        System.out.println("Удалить продукт: del [productId] [count]");
//        System.out.println("Добавить продукт в корзину: add [productId] [count]");
//        System.out.println("Закончить: exit");
//    }

//    private static void enterCommand () {
//        System.out.println("_______________________________________________");
//        System.out.print("Enter command: ");
//    }
}
