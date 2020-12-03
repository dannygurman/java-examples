package springexamples.mvc.deferred.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

@Slf4j
@Configuration
public class TaskExecutorsConfiguration {

public static final String BEAN_NAME_UNUSED_RULES_ASYNC_API_EXECUTOR = "unusedRulesAsyncApiExecutor";
    private static final String THREAD_NAME_PREFIX_UNUSED_RULES_ASYNC_API = "Unused-Rules-Async-Api-";

    private static final int NUMBER_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static final boolean WAIT_FOR_JOB_TO_COMPLETE_DEFAULT = false;

    @Value("${taskExecutor.waitForJobComplete.unusedRulesPersist:false}")
    private boolean waitForJobsToCompleteOnShutdownUnusedRulesPersist;

    @Value("${taskExecutor.awaitTerminationSeconds.unusedRulesPersist:1}")
    private int awaitTerminationSecondsUnusedRulesPersist;


    @Bean(name = BEAN_NAME_UNUSED_RULES_ASYNC_API_EXECUTOR)
    public Executor getUnusedRulesAsyncApiTaskExecutor() {
        return generateTaskExecutor(BEAN_NAME_UNUSED_RULES_ASYNC_API_EXECUTOR,
            THREAD_NAME_PREFIX_UNUSED_RULES_ASYNC_API,
            waitForJobsToCompleteOnShutdownUnusedRulesPersist,
            awaitTerminationSecondsUnusedRulesPersist );
    }

    private Executor generateTaskExecutor(String beanName, String threadPrefixName,
                                          boolean waitForJobsToCompleteOnShutdown, int awaitTerminationSeconds ){
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(NUMBER_OF_PROCESSORS);
        scheduler.setThreadNamePrefix(threadPrefixName);
        scheduler.setAwaitTerminationSeconds(awaitTerminationSeconds);
        scheduler.setWaitForTasksToCompleteOnShutdown(waitForJobsToCompleteOnShutdown);
        scheduler.setBeanName(beanName);
        return scheduler;
    }

}
