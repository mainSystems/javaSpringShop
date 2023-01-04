package main.systems.shop.core.persistence.entity.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Data
//@NoArgsConstructor
@DynamicUpdate
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    private Date date;
    @Column(name = "cost")
    private double cost;
    @Column(name = "quantity")
    private Integer quantity;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customersOrder;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product productsOrder;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomersOrder() {
        return customersOrder;
    }

    public void setCustomersOrder(Customer customersOrder) {
        this.customersOrder = customersOrder;
    }

    public Product getProductsOrder() {
        return productsOrder;
    }

    public void setProductsOrder(Product productsOrder) {
        this.productsOrder = productsOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
//                ", customersOrder=" + customersOrder +
//                ", products=" + productsOrder +
                '}';
    }
}
