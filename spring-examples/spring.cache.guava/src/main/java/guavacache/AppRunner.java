package guavacache; /**
 * Created by dannyg on 1/2/2018.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final BookCachedServiceImpl bookRepository;

    public AppRunner(BookCachedServiceImpl bookRepository) {
        this.bookRepository = bookRepository;
    }


    public void run(String... args) throws Exception {

        String isbn1 = "isbn-1234";
        String isbn2 = "isbn-4567";

        logger.info(".... Fetching books");
        printBook(isbn1);
        printBook(isbn2);

        Thread.sleep(5000);
      //  bookRepository.refresh(isbn1);

        printBook(isbn1);
        printBook(isbn2);


        printBook(isbn1);
        printBook(isbn2);
    }

    private void printBook(String isbn) {
        logger.info( isbn + "-->" + bookRepository.getByIsbn(isbn));
    }
}
