package web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dannyg on 12/27/2017.
 */
@RestController
@RequestMapping("/")

public class WebController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String hello() {
        return "test hello";
    }

    @RequestMapping(value =  "/test", method = RequestMethod.POST)
    public String performManualBackup (@RequestBody String request)  {
        return request;
    }
}
