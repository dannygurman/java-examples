package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//https://spring.io/guides/gs/testing-web/

@Controller
public class HomeController {

    //Call - http://127.0.0.1:8080/
    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello World";
    }

}