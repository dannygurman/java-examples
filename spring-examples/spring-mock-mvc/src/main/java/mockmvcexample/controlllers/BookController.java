package mockmvcexample.controlllers;

import mockmvcexample.model.Book;
import mockmvcexample.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@RestController
@RequestMapping(value = "${v1API}/books")
public class BookController {

    @Autowired
    private BookService bookService;

    //Example - GET http://127.0.0.1:8090/api/v1/books/1 8090 - in application.properties
    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id) {
        return bookService.findBookById(id);
    }

    //Example - GET http://127.0.0.1:8090/api/v1/books/
    @GetMapping()
    public Collection<Book> getBooks() {
        return bookService.getBooks();
    }

    //Example - POST http://127.0.0.1:8090/api/v1/books/
    //Body {"id":"id1","name":"name1" }
    @PostMapping()
    public void createBook(@RequestBody  @NotEmpty final Book book) {
        bookService.addBook(book);
    }

}
