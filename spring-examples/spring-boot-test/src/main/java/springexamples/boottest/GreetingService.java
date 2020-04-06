package springexamples.boottest;


import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public final static String  RESPONSE_TEXT = "Hello World!";

    public String greet() {
        return RESPONSE_TEXT;
    }
}
