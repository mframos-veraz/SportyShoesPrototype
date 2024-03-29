package com.vodafone.deal.SportyShoesPrototype.service;

import com.vodafone.deal.SportyShoesPrototype.domain.Shoe;
import com.vodafone.deal.SportyShoesPrototype.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoeService {

    @Autowired
    private ShoeRepository repository;

    public String createShoe(Shoe shoe) {
        Optional<Shoe> record = repository.findByName(shoe.getName());
        if (record.isPresent()) {
            return "Cannot create two products with the same name";
        }
        repository.save(shoe);
        return "Product created";
    }

    public String deleteShoe(int id) {
        Optional<Shoe> record = repository.findById(id);
        if (record.isPresent()) {
            repository.deleteById(id);
            return "Product deleted";
        }
        return "No product found";
    }

    public List<Shoe> getAllShoes() { return repository.findAll(); }

    public String editShoe(Shoe shoe) {
        Optional<Shoe> record = repository.findById(shoe.getId());
        if (record.isPresent()) {
            Shoe shoeRecord = record.get();
            shoeRecord.setDescription(shoe.getDescription());
            shoeRecord.setMaterial(shoe.getMaterial());
            shoeRecord.setName(shoe.getName());
            shoeRecord.setSize(shoe.getSize());
            shoeRecord.setPrice(shoe.getPrice());
            repository.saveAndFlush(shoeRecord);
            return "Product edited";
        }
        return "No product found";
    }
}
