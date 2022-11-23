package main.systems.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtils {
    private SessionFactory factory;
    @Bean(name="init")
    public void init() {
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

//    @Bean(name="shutdown")
    public void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }

    public Session getFactory() {
        return factory.getCurrentSession();
    }
}
