package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.Person;
import com.vodafone.deal.SportyShoesPrototype.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping(value = "new",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        String result = service.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deletePerson(@RequestParam int id) {
        String result = service.deletePerson(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> result = service.getAllPersons();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping(value = "edit",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editPerson(@RequestBody Person person) {
        String result = service.editPerson(person);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
