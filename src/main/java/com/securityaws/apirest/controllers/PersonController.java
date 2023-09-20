package com.securityaws.apirest.controllers;

import com.securityaws.apirest.model.Person;
import com.securityaws.apirest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id){
        Person person = personService.getByidPerson(id);
        return ResponseEntity.ok().body(person);
    }
    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person){
        personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person){
       Person updatePerson =  personService.updatePerson(id, person);
        return ResponseEntity.ok().body(updatePerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
         personService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
