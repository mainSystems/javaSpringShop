package main.systems.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "cost")
    private double cost;

//    @OneToMany(mappedBy = "products", fetch=FetchType.LAZY)
//    List<Order> orders;

    public Product(Long id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
}
