package me.kimchidev.springsecondapilogin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {

    @PostMapping("/login")
    public ResponseEntity login() throws Exception{

        return new ResponseEntity<>("login page", HttpStatus.OK);
    }
}
