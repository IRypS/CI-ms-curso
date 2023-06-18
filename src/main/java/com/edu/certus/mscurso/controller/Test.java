package com.edu.certus.mscurso.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    
    @GetMapping( "/gg" )
    public ResponseEntity<?> some() {
        return ResponseEntity.status(200).body("1");
    }

    @GetMapping( "/hola" )
    public ResponseEntity<?> somea() {
        return ResponseEntity.status(200).body("2");
    } 

    @GetMapping( "/hola" )
    public ResponseEntity<?> somea3() {
        return ResponseEntity.status(200).body("3");
    } 
}
