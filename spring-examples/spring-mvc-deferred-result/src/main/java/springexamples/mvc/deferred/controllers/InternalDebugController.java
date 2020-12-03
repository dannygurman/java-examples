package springexamples.mvc.deferred.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import springexamples.mvc.deferred.config.TaskExecutorsConfiguration;
import springexamples.mvc.deferred.dtos.ThreadPoolInfo;
import springexamples.mvc.deferred.dtos.User;
import springexamples.mvc.deferred.utils.ControllerUtils;
import springexamples.mvc.deferred.utils.SupplierWithException;

import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/debug")
public class InternalDebugController {

    @Autowired
    @Qualifier(TaskExecutorsConfiguration.BEAN_NAME_USER_ASYNC_API_EXECUTOR)
    private Executor userAsyncApiTaskExecutor;


    //Run multiple createUser in different browser/post man tab

    //Example - GET http://127.0.0.1:8091/debug/threadpool/user
    @GetMapping(value="/threadpool/user")
    public ThreadPoolInfo getUserThreadPoolDetails() throws Exception{
        if ( ! (userAsyncApiTaskExecutor instanceof ThreadPoolTaskScheduler) ){
            throw new Exception("Not ThreadPoolTaskScheduler");
        }
        ThreadPoolTaskScheduler threadPoolTaskScheduler = (ThreadPoolTaskScheduler) userAsyncApiTaskExecutor;
        int activeCount =  threadPoolTaskScheduler.getActiveCount();
        int poolSize = threadPoolTaskScheduler.getPoolSize();
        return ThreadPoolInfo.builder().activeCount(activeCount).poolSize(poolSize).build();

    }
}
