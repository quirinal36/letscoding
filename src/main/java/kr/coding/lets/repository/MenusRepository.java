package kr.coding.lets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.coding.lets.model.Menus;

public interface MenusRepository extends JpaRepository<Menus, Long>{
    Optional<Menus> findByName(String name);
}
