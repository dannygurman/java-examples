package springexamples.boottest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GreetingController {

    public final static String GREETING_URL = "greeting";

    private final GreetingService service;

    public GreetingController(GreetingService service) {
        this.service = service;
    }

    @RequestMapping("/" + GREETING_URL)
    public @ResponseBody String greeting() {
        return service.greet();
    }


}
