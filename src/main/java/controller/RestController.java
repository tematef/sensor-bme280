package controller;

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@EnableAutoConfiguration
public class RestController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}
