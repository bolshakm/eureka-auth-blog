package com.tenet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
public class DemoController {

    @Secured("USER")
    @GetMapping
    public ResponseEntity<String> demo(){
        return ResponseEntity.ok("This is a secured endpoint");

    }

    @Secured("ADMIN")
    @GetMapping("/admin")
    public ResponseEntity<String> demo2(){
        return ResponseEntity.ok("This is a secured endpoint");

    }

    @Secured({ "ADMIN", "USER" })
    @GetMapping("/all")
    public ResponseEntity<String> demo3(){
        return ResponseEntity.ok("This is a secured endpoint");

    }
}
