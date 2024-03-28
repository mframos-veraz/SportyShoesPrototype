package com.vodafone.deal.SportyShoesPrototype.repository;

import com.vodafone.deal.SportyShoesPrototype.domain.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Integer> {

    @Query("select shoe from Shoe shoe where shoe.name = :name")
    public Optional<Shoe> findByName(String name);
}
