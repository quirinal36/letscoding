package kr.coding.lets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.coding.lets.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    
}
