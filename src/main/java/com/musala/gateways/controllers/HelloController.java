package com.musala.gateways.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @Value("${app.profile}")
    String profile;

    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello World! from: "+profile, HttpStatus.OK);
    }

    @GetMapping("/resources")
    public ResponseEntity<String> resources(){
        return new ResponseEntity<>("Resources", HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return new ResponseEntity<>("Admin", HttpStatus.OK);
    }
}
