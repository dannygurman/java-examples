package mockmvcexample.controlllers;

import mockmvcexample.exceptions.MyServiceClientException;
import mockmvcexample.exceptions.MyServiceServerException;
import mockmvcexample.model.Book;
import mockmvcexample.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@RestController
@RequestMapping(value = "${v1API}/books")
public class BookController {

    //NOTE!!! - call REST  with cookie  "mycookie=admin" (see spring security)

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

    //Example - GET http://127.0.0.1:8090/api/v1/books/exceptionserver
    @GetMapping("/exceptionserver")
    public void serverExceptionExample() throws MyServiceServerException{
        throw new MyServiceServerException("my msg1");
    }

    //Example - GET http://127.0.0.1:8090/api/v1/books/exceptionclient
    @GetMapping("/exceptionclient")
    public void clientExceptionExample() throws MyServiceClientException {
        throw new MyServiceClientException("my msg2");
    }
}
