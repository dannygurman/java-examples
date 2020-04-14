package jparepositoryexample.service;

import static jparepositoryexample.service.BookSpecifications.hasAuthor;
import static jparepositoryexample.service.BookSpecifications.titleContains;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;

import jparepositoryexample.entity.Book;
import jparepositoryexample.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> query(String author, String title) {
        return bookRepository.findAll(where(hasAuthor(author)).and(titleContains(title)));
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }


}
