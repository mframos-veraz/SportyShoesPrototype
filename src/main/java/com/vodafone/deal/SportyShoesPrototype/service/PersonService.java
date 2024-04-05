package com.vodafone.deal.SportyShoesPrototype.service;

import com.vodafone.deal.SportyShoesPrototype.domain.Person;
import com.vodafone.deal.SportyShoesPrototype.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;
    @Autowired
    private LoginService loginService;
    @Autowired
    private PasswordEncoder encoder;

    public String createPerson(Person person) {
        Optional<Person> record = repository.findByEmail(person.getLogin().getUsername());
        if (record.isPresent()) {
            return "User already registered";
        }

        person.getLogin().setPassword(encoder.encode(person.getLogin().getPassword()));
        loginService.createLogin(person.getLogin());

        repository.save(person);
        return "User created successfully";
    }

    public String deletePerson(int id) {
        Optional<Person> record = repository.findById(id);
        if (record.isPresent()) {
            repository.deleteById(id);
            return "User deleted";
        }
        return "User does not exist";
    }

    public List<Person> getAllPersons() { return repository.findAll(); }

    public String editPerson(Person person) {
        Optional<Person> record = repository.findById(person.getId());
        if (record.isPresent()) {
            Person personRecord = record.get();
            personRecord.setFirstName(person.getFirstName());
            personRecord.setLastName(person.getLastName());
            person.setPassword(encoder.encode(person.getLogin().getPassword()));
            personRecord.setLogin(person.getLogin());
            repository.saveAndFlush(personRecord);
            return "User edited";
        }
        return "User does not exist";
    }

    public Person getPersonByEmail(String email) {
        Optional<Person> user = repository.findByEmail(email);
        return user.orElse(null);
    }

    public Person getPersonById(int id) {
        Optional<Person> user = repository.findById(id);
        return user.orElse(null);
    }

    public Integer getPersonIdByEmail(String email) {
        return repository.findIdByEmail(email);
    }
}
