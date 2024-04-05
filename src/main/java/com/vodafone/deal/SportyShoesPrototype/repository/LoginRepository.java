package com.vodafone.deal.SportyShoesPrototype.repository;

import com.vodafone.deal.SportyShoesPrototype.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    @Query("select login from Login login where login.username = :username")
    Optional<Login> findByUsername(String username);

    @Query("select login from Login login where login.role = :role")
    List<Login> findLoginsByRole(String role);
}
