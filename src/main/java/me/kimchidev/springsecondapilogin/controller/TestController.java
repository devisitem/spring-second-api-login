package me.kimchidev.springsecondapilogin.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(value = "/hello",produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    @GetMapping
    public ResponseEntity testHello(HttpServletRequest req) throws Exception{


        return new ResponseEntity<>("{message=\"hello kimchi\"}",HttpStatus.OK);
    }
}
