package kr.coding.lets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.coding.lets.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email); // 이미 email을 통해 생성된 사용자인지 체크
    Boolean existsByEmail(String email);
    Optional<User> findByPhone(String phone);
    
}