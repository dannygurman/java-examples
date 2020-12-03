package springexamples.mvc.deferred.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private static final String API_PARAM_USER_ID = "id";
    private final static String GET_UNUSED_RULES_OPERATION_DESCRIPTION = "get unused rules";

    @Autowired UserService userService;

    @Autowired
    @Qualifier(TaskExecutorsConfiguration.BEAN_NAME_UNUSED_RULES_ASYNC_API_EXECUTOR)
    private Executor UserAsyncApiTaskExecutor;

    @GetMapping(value="/{" + API_PARAM_USER_ID + ":.+}")
    public DeferredResult<User> getUserById(
          @PathVariable(API_PARAM_USER_ID) String userId) {

        SupplierWithException<User> supplier = () -> userService.getUserById(userId);
        return ControllerUtils.performDeferredOperation(UserAsyncApiTaskExecutor, supplier,  GET_UNUSED_RULES_OPERATION_DESCRIPTION);
    }
}
