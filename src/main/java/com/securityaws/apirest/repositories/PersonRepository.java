package com.securityaws.apirest.repositories;

import com.securityaws.apirest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
