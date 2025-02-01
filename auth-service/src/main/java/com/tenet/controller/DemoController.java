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
    @GetMapping("/user")
    public ResponseEntity<String> getForUsers(){
        return ResponseEntity.ok("This is a user secured endpoint");

    }

    @Secured("ADMIN")
    @GetMapping("/admin")
    public ResponseEntity<String> getForAdmin(){
        return ResponseEntity.ok("This is a admin secured endpoint");

    }

    @Secured({ "ADMIN", "USER" })
    @GetMapping("/all")
    public ResponseEntity<String> getForAll(){
        return ResponseEntity.ok("This is a all secured endpoint");

    }
}
