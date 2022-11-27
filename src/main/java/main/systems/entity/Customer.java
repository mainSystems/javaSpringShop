package main.systems.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;


    @OneToMany(mappedBy = "customersOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    List<Order> orders;
    private List <Order> orders = new ArrayList<>();
}
