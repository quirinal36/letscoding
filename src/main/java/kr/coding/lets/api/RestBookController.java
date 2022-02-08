package kr.coding.lets.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.coding.lets.model.Book;
import kr.coding.lets.model.Result;
import kr.coding.lets.repository.BookRepository;
import kr.coding.lets.service.BookService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequestMapping(value="/api/v1/book/")
@RestController
public class RestBookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value="list")
    public Result list(@RequestParam Integer page, @RequestParam Integer size){
        Result result = Result.successInstance();
        List<Book> bookList = bookService.findBooksByPageRequest(page, size);
        log.info("list size: " + bookList.size());
        result.setData(bookList);
        result.setTotalCount(bookService.getTotalCount());
        return result;
    }

    @GetMapping(value="insert")
    public Result insert(Book book){
        Result result = Result.successInstance();
        result.setData(bookService.insert(book));
        return result;
    }
}
