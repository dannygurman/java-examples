package springexamples.mvc.deferred.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Executor;

@Slf4j
public class ControllerUtils {

    public static <T> DeferredResult<T> performDeferredOperation(Executor executor, SupplierWithException<T> supplier, String operationDescription) {
        final DeferredResult<T> deferredResult = new DeferredResult<>();
        Runnable getUnusedRuleResponseTask = () -> {
            log.debug("Processing {} operation in separate thread using DeferredResult", operationDescription);
            try {
                deferredResult.setResult(supplier.get());
            } catch (Exception e) {
                deferredResult.setErrorResult(e);
            }
        };
        executor.execute(getUnusedRuleResponseTask);
        return deferredResult;
    }
}
