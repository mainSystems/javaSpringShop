package main.systems.repositories;

import main.systems.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositoryDao extends JpaRepository<Customer,Long> {
}
