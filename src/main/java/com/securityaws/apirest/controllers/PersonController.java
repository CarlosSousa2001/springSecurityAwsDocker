package com.securityaws.apirest.controllers;

import com.securityaws.apirest.data.vo.v1.PersonVO;
import com.securityaws.apirest.data.vo.v2.PersonVOV2;
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
    public ResponseEntity<List<PersonVO>> getAllPerson(){
        List<PersonVO> personList = personService.findAllPerson();
        return ResponseEntity.ok().body(personList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonVO> getById(@PathVariable Long id){
        PersonVO person = personService.getByidPerson(id);
        return ResponseEntity.ok().body(person);
    }
    @PostMapping
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person){
        personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }
    @PostMapping("/v2")
    public ResponseEntity<PersonVOV2> createV2(@RequestBody PersonVOV2 person){
        personService.createPersonV2(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonVO> update(@PathVariable Long id, @RequestBody PersonVO person){
       PersonVO updatePerson =  personService.updatePerson(id, person);
        return ResponseEntity.ok().body(updatePerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
         personService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
