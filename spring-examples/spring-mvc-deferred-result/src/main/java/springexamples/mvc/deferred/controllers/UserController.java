package springexamples.mvc.deferred.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import springexamples.mvc.deferred.config.TaskExecutorsConfiguration;
import springexamples.mvc.deferred.dtos.User;
import springexamples.mvc.deferred.services.UserService;
import springexamples.mvc.deferred.utils.ControllerUtils;
import springexamples.mvc.deferred.utils.SupplierWithException;

import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/users")
@Slf4j
public class UserController {

/*    Asynchronous support was introduced in Servlet 3.0 and, simply put,
    it allows processing an HTTP request in another thread than the request receiver thread.

    DeferredResult, available from Spring 3.2 onwards, assists in offloading a long-running computation
     from an http-worker thread to a separate thread.

    Although the other thread will take some resources for computation, the worker threads are
    not blocked in the meantime and can handle incoming client requests.

    The async request processing model is very useful as it helps scale an application
     well during high loads, especially for IO intensive operations.

     It could be also used as rate limiting - [rate-limiting-throttling ]
     */


    private static final String API_PARAM_USER_NAME = "name";
    private final static String USER_CREATION_OPERATION_DESCRIPTION = "createUser";

    @Autowired UserService userService;

    @Autowired
    @Qualifier(TaskExecutorsConfiguration.BEAN_NAME_USER_ASYNC_API_EXECUTOR)
    private Executor UserAsyncApiTaskExecutor;


    //Note the API is NOT Async - it sync!!!. it will still  return only after supplier will finish but it
    //will not use HTTP resources


    //Example - POST http://127.0.0.1:8091/users/danny
    @PostMapping(value="/{" + API_PARAM_USER_NAME + ":.+}")
    public DeferredResult<User> createUser( @PathVariable(API_PARAM_USER_NAME) String userName) {
        SupplierWithException<User> supplier = () -> userService.createUserLongOperation(userName);
        return ControllerUtils.performDeferredOperation(UserAsyncApiTaskExecutor,
            supplier,
            USER_CREATION_OPERATION_DESCRIPTION);
    }
}
