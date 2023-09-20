package com.securityaws.apirest.controllers;

import com.securityaws.apirest.model.Person;
import com.securityaws.apirest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPerson(){
        List personList = personService.findAllPerson();
        return ResponseEntity.ok().body(personList);
    }
}
