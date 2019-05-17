package guavacache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dannyg on 1/3/2018.
 */

@Configuration
public class AppConfig {

    private final static int DURATION = 5;

    @Autowired
    BooksDataSource booksDataSource;

    @Bean
    public AsyncTaskExecutor getTaskThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(0);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return threadPoolTaskExecutor;
    }

    @Bean
    BookCacheLoader getBookCacheLoader () {
        AsyncTaskExecutor asyncTaskExecutor = getTaskThreadPool();
        return new BookCacheLoader( booksDataSource,  asyncTaskExecutor);
    }


    @Bean ()
    public LoadingCache  <String, Book> getBookCache() {
        LoadingCache loadingCache = CacheBuilder.newBuilder().refreshAfterWrite(DURATION, TimeUnit.SECONDS).build(getBookCacheLoader());
        return loadingCache;
    }


}
