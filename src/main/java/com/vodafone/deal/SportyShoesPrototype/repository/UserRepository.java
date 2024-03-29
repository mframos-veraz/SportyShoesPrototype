package com.vodafone.deal.SportyShoesPrototype.repository;

import com.vodafone.deal.SportyShoesPrototype.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select user from User user where user.name = :name")
    public Optional<User> findByName(String name);
}
