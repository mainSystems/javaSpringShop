package main.systems.persistence.repositories;

import main.systems.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryDao extends JpaRepository<Role, Long> {
}
