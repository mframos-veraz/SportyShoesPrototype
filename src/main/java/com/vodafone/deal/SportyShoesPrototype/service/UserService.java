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
        Optional<User> record = repository.findByEmail(user.getEmail());
        if (record.isPresent()) {
            return "User already registered";
        }
        repository.save(user);
        return "User created successfully";
    }

    public String deleteUser(int id) {
        Optional<User> record = repository.findById(id);
        if (record.isPresent()) {
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

    public User getUserByEmail(String email) {
        Optional<User> user = repository.findByEmail(email);
        return user.orElse(null);
    }

    public User getUserById(int id) {
        Optional<User> user = repository.findById(id);
        return user.orElse(null);
    }
}
