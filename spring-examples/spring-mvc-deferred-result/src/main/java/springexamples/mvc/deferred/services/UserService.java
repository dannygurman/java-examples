package springexamples.mvc.deferred.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import springexamples.mvc.deferred.dtos.User;

@Service
public class UserService {

    public User createUserLongOperation(String userName) {
        //Do some relative long processing
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return User.builder().id("userId").name(userName).build();
    }

}
