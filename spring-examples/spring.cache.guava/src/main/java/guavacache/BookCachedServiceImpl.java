package guavacache; /**
 * Created by dannyg on 1/2/2018.
 */

import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class BookCachedServiceImpl implements BookCachedService {

    private static final Logger logger = LoggerFactory.getLogger(BookCachedServiceImpl.class);

    @Autowired
    LoadingCache<String, Book>  bookCache;

    public Book getByIsbn(String isbn) {
        try {
            return bookCache.get(isbn);
        } catch (ExecutionException e) {
            logger.warn(e.toString());
            return null;
        }
    }

    public void refresh (String isbn) {
        bookCache.refresh(isbn);
    }


}
