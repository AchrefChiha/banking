package com.achref.banking.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/demo")
@RestController
public class DemoController {

    @PostMapping("/")
    public ResponseEntity<String> sayHello(){
        return  ResponseEntity.ok("Hello from Secured point");
    }
}
