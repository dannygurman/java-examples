package jparepositoryexample.repository;

import jparepositoryexample.entity.Book;
import jparepositoryexample.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void it_can_find_the_book_after_save_it() {
        Long id = 1L;
        String author = "x";
        String title = "t";

        Book book = new Book();
        book.setId(id);
        book.setAuthor(author);
        book.setTitle(title);

        bookService.save(book);
        List<Book> matchingBooks = bookService.query(author,title);
        assertEquals(1, matchingBooks.size());
    }


}
