package com.vodafone.deal.SportyShoesPrototype.service;

import com.vodafone.deal.SportyShoesPrototype.domain.User;
import com.vodafone.deal.SportyShoesPrototype.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public String createUser(User user) {
        Optional<User> result = repository.findByName(user.getName());
        if (result.isPresent()) {
            return "User already registered";
        }
        repository.save(user);
        return "User created successfully";
    }

    public String deleteUser(int id) {
        Optional<User> result = repository.findById(id);
        if (result.isPresent()) {
            repository.deleteById(id);
            return "User deleted";
        }
        return "User does not exist";
    }

    public List<User> getAllUsers() { return repository.findAll(); }

    public String editUser(User user) {
        Optional<User> record = repository.findById(user.getId());
        if (record.isPresent()) {
            User userRecord = record.get();
            userRecord.setEmail(user.getEmail());
            userRecord.setName(user.getName());
            userRecord.setType(user.getType());
            userRecord.setPassword(user.getPassword());
            repository.saveAndFlush(userRecord);
            return "User edited";
        }
        return "User does not exist";
    }
}
