package main.systems.data;

import main.systems.entity.Order;

import java.util.List;

public interface OrderRepositoryDao {
    List<Order> getOrders();
}
