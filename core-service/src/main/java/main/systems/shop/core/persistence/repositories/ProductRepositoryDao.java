package main.systems.shop.core.persistence.repositories;

import main.systems.shop.api.entity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryDao extends JpaRepository<Product, Long> {
}
