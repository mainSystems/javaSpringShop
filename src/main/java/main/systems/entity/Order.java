package main.systems.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "product_id")
    private Long product_id;
    @Column(name = "customer_id")
    private Long customer_id;
    @Column(name = "date")
    private String date;
    @Column(name = "cost")
    private double cost;
    @Column(name = "quantity")
    private Integer quantity;

    public Order () {}
}
