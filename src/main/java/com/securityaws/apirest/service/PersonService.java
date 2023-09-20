package com.securityaws.apirest.service;

import com.securityaws.apirest.exception.PersonNotFoundException;
import com.securityaws.apirest.model.Person;
import com.securityaws.apirest.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAllPerson(){
        return personRepository.findAll();
    }
    public Person getByidPerson(Long id){
        Optional<Person> personOptional =  personRepository.findById(id);
        if (personOptional.isPresent()){
            return personOptional.get();
        } else {
            throw new PersonNotFoundException(String.format("Person com id: '%s' não encontrado", id));
        }
    }
    public Person createPerson(Person person){
        return personRepository.save(person);
    }
    public Person updatePerson(Long id, Person person){
        Person existingPerson = getByidPerson(id);

        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setAddress(person.getAddress());
        existingPerson.setGender(person.getGender());

       return personRepository.save(existingPerson);
    }

    public void deleteById(Long id) {
        getByidPerson(id);
        personRepository.deleteById(id);
    }
}
