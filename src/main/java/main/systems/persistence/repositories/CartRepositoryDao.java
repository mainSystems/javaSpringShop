package main.systems.persistence.repositories;

import main.systems.persistence.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepositoryDao extends JpaRepository<Order, Long> {

//    @Query(value = "select o.id from Order o where o.product_id = :productId")
//    Order findUniqOrderByProductId(Long productId);

    @Query(nativeQuery = true, value = "select * from orders where product_id = :productId")
    List<Order> findOrderId(Long productId);

    @Query(nativeQuery = true, value = "select quantity from orders where product_id = :productId")
    Long findQuantityByProductId(Long productId);

    @Query(nativeQuery = true, value = "select count(*) from orders where product_id = :productId")
    int findCountOrdersByProductId(Long productId);

}
