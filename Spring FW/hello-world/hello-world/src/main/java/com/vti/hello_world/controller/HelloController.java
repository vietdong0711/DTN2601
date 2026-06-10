package com.vti.hello_world.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/hello")
public class HelloController {

    @GetMapping
    public ResponseEntity<?> hello(@RequestParam(name = "id") Integer id
            , @RequestParam(name = "fullName") String fullName) {
        return new ResponseEntity<>("Hello World "+ id + fullName, HttpStatus.OK);
    }
}
