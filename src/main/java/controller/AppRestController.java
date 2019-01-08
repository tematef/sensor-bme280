package controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@EnableAutoConfiguration
public class AppRestController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}