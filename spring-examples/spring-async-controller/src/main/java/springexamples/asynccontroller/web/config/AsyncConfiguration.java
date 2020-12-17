package springexamples.asynccontroller.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/*Spring comes with @EnableAsync annotation
     and can be applied on application classes for asynchronous behavior.
      This annotation will look for methods marked with @Async annotation and
      run in background thread pools.
      The @Async annotated methods can return CompletableFuture
      to hold the result of an asynchronous computation.
        */
@Configuration
@EnableAsync //- NOTE
public class AsyncConfiguration {

    public static final String ASYNC_EXECUTOR = "asyncExecutor";
    public static final String THREAD_NAME_PREFIX = "AsynchThread-";

    @Bean(name = ASYNC_EXECUTOR)
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        executor.initialize();
        return executor;
    }

}
