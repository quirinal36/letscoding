package kr.coding.lets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.coding.lets.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long>{
    Optional<Roles> findByName(String name);
}
