package com.vodafone.deal.SportyShoesPrototype.repository;

import com.vodafone.deal.SportyShoesPrototype.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("select person from Person person where person.login.username = :email")
    Optional<Person> findByEmail(String email);

    @Query("select person.id from Person person where person.login.username = :email")
    Integer findIdByEmail(String email);
}
