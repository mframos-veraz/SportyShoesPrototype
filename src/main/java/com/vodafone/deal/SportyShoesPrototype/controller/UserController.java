package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.User;
import com.vodafone.deal.SportyShoesPrototype.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "new",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String result = service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteUser(@RequestParam int id) {
        String result = service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> result = service.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping(value = "edit",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editUser(@RequestBody User user) {
        String result = service.editUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
