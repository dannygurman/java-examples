package mockmvcexample.dao;

import mockmvcexample.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BookRepository {

    private Map<String, Book> bookIdToBooks= new HashMap<>();

    public Book findBookById( final String id) {
        return bookIdToBooks.get(id);
    }

    public Collection<Book> getBooks ( ) {
        return bookIdToBooks.values();
    }

    public void addBook( final Book book){
        bookIdToBooks.put(book.getId(), book);
    }
}
