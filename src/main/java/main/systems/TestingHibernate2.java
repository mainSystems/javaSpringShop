package main.systems;

import main.systems.entity.Customer;
import main.systems.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class TestingHibernate2 {


    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();


        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
//            Product product = session.get(Product.class, 1L);
//            Customer customerTest = session.get(Customer.class, 1L);
            System.out.println("1==================================================================================================================");
//            System.out.println("customer = " + customerTest);
//            System.out.println("product = " + product);
            System.out.println("2==================================================================================================================");
            session.getTransaction().commit();
        }
        factory.close();
    }

}
