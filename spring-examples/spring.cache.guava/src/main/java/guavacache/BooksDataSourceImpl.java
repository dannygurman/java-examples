package guavacache;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dannyg on 1/3/2018.
 */
@Component
public class BooksDataSourceImpl implements  BooksDataSource {
    public Book getByIsbn(String isbn){
        simulateSlowService();
        return new Book(isbn , new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
    }
    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
