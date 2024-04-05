package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.Login;
import com.vodafone.deal.SportyShoesPrototype.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("logins")
public class LoginController {

    @Autowired
    private LoginService service;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("new")
    public ResponseEntity<String> createLogin(@RequestBody Login login) {
        login.setPassword(encoder.encode(login.getPassword()));
        String result = service.createLogin(login);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(value = "list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Login>> getAllLogins() {
        List<Login> result = service.getAllLogins();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteLogin(@RequestParam int id) {
        String result = service.deleteLogin(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping(value = "edit",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editLogin(@RequestBody Login login) {
        String result = service.editLogin(login);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
