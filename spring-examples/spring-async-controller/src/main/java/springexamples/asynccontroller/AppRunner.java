package springexamples.asynccontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springexamples.asynccontroller.web.model.EmployeeAddresses;
import springexamples.asynccontroller.web.model.EmployeeNames;
import springexamples.asynccontroller.web.service.AsyncUserService;

import java.util.concurrent.CompletableFuture;

@Component
public class AppRunner implements CommandLineRunner {


    private final AsyncUserService asyncUserService;

    public AppRunner(AsyncUserService asyncUserService) {
        this.asyncUserService = asyncUserService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture<EmployeeNames> names = asyncUserService.getEmployeeNames();
        CompletableFuture<EmployeeAddresses> addresses = asyncUserService.getEmployeeAddress();

        // Wait until they are all done
        CompletableFuture.allOf(names, addresses).join();

        // Print results, including elapsed time
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
        System.out.println("--> " + names.get());
        System.out.println("--> " + addresses.get());

    }
}
