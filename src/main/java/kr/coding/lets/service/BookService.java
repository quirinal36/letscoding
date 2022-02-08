package kr.coding.lets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.coding.lets.model.Book;
import kr.coding.lets.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book insert(Book book){
        return bookRepository.save(book);
    }
    public int getTotalCount(){
        return bookRepository.findAll().size();
    }
    public List<Book> findBooksByPageRequest(Integer page, Integer size){
        page = (page-1) * size;
        PageRequest pageRequest = PageRequest.of(page, size);
        return bookRepository.findAll(pageRequest).getContent();
    }
}
