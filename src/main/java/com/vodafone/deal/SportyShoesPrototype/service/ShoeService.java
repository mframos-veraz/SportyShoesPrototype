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
        Optional<Shoe> product = repository.findByName(shoe.getName());
        if (product.isPresent()) {
            return "Cannot create two products with the same name";
        }
        repository.save(shoe);
        return "Product created";
    }

    public String deleteShoe(int id) {
        Optional<Shoe> product = repository.findById(id);
        if (product.isPresent()) {
            repository.deleteById(id);
            return "Product deleted";
        }
        return "No product found";
    }

    public List<Shoe> getAllShoes() {
        return repository.findAll();
    }

    public String editShoe(Shoe shoe) {
        Optional<Shoe> product = repository.findById(shoe.getId());
        if (product.isPresent()) {
            Shoe shoeRecord = product.get();
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
