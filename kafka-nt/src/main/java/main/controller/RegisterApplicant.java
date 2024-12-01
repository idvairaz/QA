package main.controller;

import main.service.KafkaMessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterApplicant {
    @Autowired
    KafkaMessageProducerService service;

    @GetMapping("/register")
    public ResponseEntity<String> register(@RequestBody String str){
        service.send(str);
        return ResponseEntity.ok("You registration has been acknowledged");
    }
}
