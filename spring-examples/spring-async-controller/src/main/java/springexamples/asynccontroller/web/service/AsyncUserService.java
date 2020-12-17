package springexamples.asynccontroller.web.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springexamples.asynccontroller.web.model.EmployeeAddresses;
import springexamples.asynccontroller.web.model.EmployeeNames;

import static springexamples.asynccontroller.web.config.AsyncConfiguration.ASYNC_EXECUTOR;

@Service
public class AsyncUserService {

    private static final String  EMPLOYEE_API_URL_PREFIX = "http://localhost:8080/";
    private static Logger log = LoggerFactory.getLogger(AsyncUserService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Async(ASYNC_EXECUTOR)   //Making this Async operation
    public CompletableFuture<EmployeeNames> getEmployeeNames() throws InterruptedException    {
        log.info("getEmployeeName starts");
        final String url = EMPLOYEE_API_URL_PREFIX + "names";
        EmployeeNames employeeNameData = restTemplate.getForObject(url, EmployeeNames.class);

        log.info("employeeNameData, {}", employeeNameData);
        Thread.sleep(1000L);    //Intentional delay
        log.info("employeeNameData completed");
        return CompletableFuture.completedFuture(employeeNameData);
    }

    @Async(ASYNC_EXECUTOR)
    public CompletableFuture<EmployeeAddresses> getEmployeeAddress() throws InterruptedException    {
        log.info("getEmployeeAddress starts");
        final String url = EMPLOYEE_API_URL_PREFIX + "addresses";
        EmployeeAddresses employeeAddressData = restTemplate.getForObject(url, EmployeeAddresses.class);

        log.info("employeeAddressData, {}", employeeAddressData);
        Thread.sleep(1000L);    //Intentional delay
        log.info("employeeAddressData completed");
        return CompletableFuture.completedFuture(employeeAddressData);
    }


}