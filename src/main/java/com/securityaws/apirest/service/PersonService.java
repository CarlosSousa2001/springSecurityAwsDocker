package com.securityaws.apirest.service;

import com.securityaws.apirest.data.vo.v1.PersonVO;
import com.securityaws.apirest.data.vo.v1.dozermapper.DozerMapper;
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

    public List<PersonVO> findAllPerson(){
        return DozerMapper.parseListObject(personRepository.findAll(), PersonVO.class);
    }
    public PersonVO getByidPerson(Long id){
        Optional<Person> personOptional =  personRepository.findById(id);
        if (personOptional.isPresent()){
            return DozerMapper.parseObject(personOptional.get(), PersonVO.class);
        } else {
            throw new PersonNotFoundException(String.format("Person com id: '%s' não encontrado", id));
        }
    }
    public PersonVO createPerson(PersonVO personvo){

        var entity = DozerMapper.parseObject(personvo, Person.class);

        var vo =DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);

        return vo;
    }
    public PersonVO updatePerson(Long id, PersonVO person){

        Person existingPerson = personRepository.findById(person.getId())
                        .orElseThrow(() -> new PersonNotFoundException("id inválido"));

        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setAddress(person.getAddress());
        existingPerson.setGender(person.getGender());

        var vo =DozerMapper.parseObject(personRepository.save(existingPerson), PersonVO.class);

        return vo;
    }

    public void deleteById(Long id) {
        getByidPerson(id);
        personRepository.deleteById(id);
    }
}
