package main.systems.repositories;

import main.systems.data.CartRepositoryDao;
import main.systems.data.HibernateUtils;
import main.systems.entity.Order;
import main.systems.entity.Product;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class CartRepository implements CartRepositoryDao {
    @Autowired
    private final HibernateUtils hibernateUtils;

    public CartRepository(HibernateUtils hibernateUtils) {
        this.hibernateUtils = hibernateUtils;
    }

    @Override
    public boolean isContainKey(Product product) {
        try (Session session = hibernateUtils.getFactory()) {
            session.beginTransaction();
            Order query = session.get(Order.class, product.getId());
//            System.out.println("!!!!!order.getId() = " + order.getId());
//            int query = session.createQuery("select orders from Order orders where product_id = :product_id")
//                    .setParameter("product_id",product.getId()).list().size();
            session.getTransaction().commit();
            return (query != null) ? true : false;
        }
    }

    @Override
    public Integer getCountProducts(Product product) {
        try (Session session = hibernateUtils.getFactory()) {
            session.beginTransaction();
            Order query = session.get(Order.class, product.getId());
            session.getTransaction().commit();
            if (query == null) {
                return 0;
            } else {
                return query.getQuantity();
            }
        }
    }

    @Override
    public void addProduct(Product product, Integer count) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.000");
        Date date = new Date(System.currentTimeMillis());

        try (Session session = hibernateUtils.getFactory()) {
            session.beginTransaction();
            Order query = session.get(Order.class, product.getId());
            if (query != null) {
                query.setQuantity(query.getQuantity() + count);
            } else {
                Order newOrder = new Order();

                newOrder.setDate(dateFormat.format(date));
                newOrder.setCost(70);
                newOrder.setQuantity(count);
                session.saveOrUpdate(newOrder);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteProduct(Product product, Integer count) {
        try (Session session = hibernateUtils.getFactory()) {
            session.beginTransaction();
            Order query = session.get(Order.class, product.getId());
            if (query != null) {
                query.setQuantity(query.getQuantity() - count);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void purgeProduct(Product product) {

    }

    @Override
    public List<Order> getCart() {
        List<Order> query = null;
        try (Session session = hibernateUtils.getFactory()) {
            session.beginTransaction();
//            Order query1 = session.get(Order.class, 1L);
//            Order query2 = session.get(Order.class, 2L);
//            query.
//            query.add(2,query2);
//            System.out.println("query.toString() = " + query.toString());
            query = session.createQuery("from Order", Order.class).getResultList();
            System.out.println("!!!!!!!!!!!!!!!!!productMap = " + query.toString());
            session.getTransaction().commit();
            return query;
        }
//        return query;
    }
}
