package com.securityaws.apirest.config;

import com.securityaws.apirest.model.Person;
import com.securityaws.apirest.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public TesteConfig(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Person person1 = new Person(null, "John", "Doe", "123 Main St", "Male");
        Person person2 = new Person(null, "Alice", "Smith", "456 Elm St", "Female");
        Person person3 = new Person(null, "Bob", "Johnson", "789 Oak St", "Male");
        Person person4 = new Person(null, "Eva", "Williams", "101 Pine St", "Female");

        personRepository.saveAll(Arrays.asList(person1, person2, person3, person4));
    }
}
