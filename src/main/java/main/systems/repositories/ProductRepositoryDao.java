package main.systems.repositories;

import main.systems.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryDao extends JpaRepository<Product,Long> {
}
