package guavacache;

import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncTaskExecutor;

import java.util.concurrent.Callable;

/**
 * Created by dannyg on 1/3/2018.
 */
public class BookCacheLoader  extends  CacheLoader <String , Book>  {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private BooksDataSource booksDataSource;
    private AsyncTaskExecutor asyncTaskExecutor;

    public BookCacheLoader(BooksDataSource booksDataSource, AsyncTaskExecutor asyncTaskExecutor) {
        this.booksDataSource = booksDataSource;
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    @Override
   // Computes or retrieves the value corresponding to key.
   // Parameters:
    //key - the non-null key whose value should be loaded
    //Returns:
    //the value associated with key; must not be null
    public Book load(String isbn) throws Exception {
        return booksDataSource.getByIsbn(isbn);
    }

    @Override
    public ListenableFuture <Book> reload(final String isbn, final Book oldValue) throws Exception {

        Callable bookCallable =  new Callable<Book>() {
            public Book call() throws Exception {
                try {
                    return booksDataSource.getByIsbn(isbn);
                } catch (Exception e) {
                    logger.error("Can't retrieve book. isbn=" + isbn + ". Message=" + e.getMessage(), e);
                    return oldValue;
                }
            }
        };
        ListenableFutureTask<Book> getBookTask = ListenableFutureTask.create(bookCallable);

        asyncTaskExecutor.execute(getBookTask);
        return getBookTask;
    }

}
