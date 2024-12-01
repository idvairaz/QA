package main.controller;

import main.service.KafkaMessageConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllApplicants {
    @Autowired
    KafkaMessageConsumerService service;

    @GetMapping("/findAll")
    public ResponseEntity<List<String>> register(){
        return ResponseEntity.ok(service.getAll());
    }
}
