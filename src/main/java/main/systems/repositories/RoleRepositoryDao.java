package main.systems.repositories;

import main.systems.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryDao extends JpaRepository<Role,Long> {
}
