package com.vodafone.deal.SportyShoesPrototype.service;

import com.vodafone.deal.SportyShoesPrototype.domain.Login;
import com.vodafone.deal.SportyShoesPrototype.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private LoginRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> record = repository.findByUsername(username);
        if (record.isPresent()) {
            Login login = record.get();
            return User.builder()
                    .username(login.getUsername())
                    .password(login.getPassword())
                    .roles(login.getRole())
                    .build();
        } else throw new UsernameNotFoundException(username);
    }

    public String createLogin(Login login) {

        Optional<Login> record = repository.findByUsername(login.getUsername());
        if (record.isPresent()) {
            return "User already exists";
        }

        if (Objects.equals(login.getRole(), "ADMIN")) {
            List<Login> adminLogins = repository.findAll().stream()
                    .filter(loginRecord -> Objects.equals(loginRecord.getRole(), "ADMIN"))
                    .toList();
            if (adminLogins.size() >= 2) {
                return "Cannot have more than 2 administrators";
            }
        }

        repository.save(login);
        return "Account created successfully";
    }

    public List<Login> getAllLogins() {
        return repository.findAll();
    }

    public String editLogin(Login login) {
        Optional<Login> record = repository.findById(login.getId());
        if (record.isEmpty()) {
            return "No such credentials exist";
        }
        Login loginRecord = record.get();
        loginRecord.setUsername(login.getUsername());
        loginRecord.setPassword(login.getPassword());
        loginRecord.setRole(login.getRole());
        repository.saveAndFlush(loginRecord);
        return "Edited credentials successfully";
    }

    public String deleteLogin(int id) {
        Optional<Login> record = repository.findById(id);
        if (record.isEmpty()) {
            return "No such credentials exist";
        }
        repository.deleteById(id);
        return "Credentials deleted";
    }
}
