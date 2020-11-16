package mockmvcexample.services;

import mockmvcexample.model.Book;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {

    private Map<String, Book> bookRepository = new HashMap<>();

    public Book findBookById( String id) {
        return bookRepository.get(id);
    }

    public Collection <Book>  getBooks ( ) {
        return bookRepository.values();
    }

    public Book  addBook( final Book book) {
        return bookRepository.put(book.getId(), book);
    }
}
