package main.systems.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class HibernateUtils {
    private SessionFactory factory;

//    @Bean(name = "init")
    @PostConstruct
    public void init() {
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @PreDestroy
    public void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }

    public Session getFactory() {
        return factory.getCurrentSession();
    }
}
