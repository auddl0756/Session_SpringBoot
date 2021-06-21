package com.example.demo.web;

import com.example.demo.web.dto.HelloResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
       log.info("/hello로 get 요청이 들어왔습니다.");
       return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDTO helloDTO(@RequestParam("name") String name,@RequestParam("amount") int amount){
        return new HelloResponseDTO(name,amount);
    }
}
