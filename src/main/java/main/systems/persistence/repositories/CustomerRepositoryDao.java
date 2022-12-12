package main.systems.persistence.repositories;

import main.systems.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepositoryDao extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
}
