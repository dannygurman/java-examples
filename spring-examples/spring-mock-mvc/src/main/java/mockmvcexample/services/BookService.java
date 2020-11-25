package mockmvcexample.services;

import mockmvcexample.dao.BookRepository;
import mockmvcexample.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {

    @Autowired private  BookRepository bookRepository;

    public Book findBookById( String id) {
        return bookRepository.findBookById(id);
    }

    public Collection <Book>  getBooks ( ) {
        return bookRepository.getBooks();
    }

    public void addBook( final Book book) {
        bookRepository.addBook(book);
    }
}
