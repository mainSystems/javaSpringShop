package main.systems.shop.core.persistence.repositories;

import main.systems.shop.api.entity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryDao extends JpaRepository<Role, Long> {
}
