package main.systems.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customersOrder;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product products;
}
