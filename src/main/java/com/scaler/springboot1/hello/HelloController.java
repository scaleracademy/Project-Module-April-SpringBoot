package com.scaler.springboot1.hello;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/world")
        public String hello(){
            return "Hello World";
        }

    @GetMapping("/you")
    public String helloYou(@RequestParam("name") String name){
        return "Hello " + name;
    }

}
