package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.Shoe;
import com.vodafone.deal.SportyShoesPrototype.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ShoeController {

    @Autowired
    private ShoeService service;

    @PostMapping(value = "new",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createProduct(@RequestBody Shoe shoe) {
        String result = service.createShoe(shoe);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteProduct(@RequestParam int id) {
        String result = service.deleteShoe(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Shoe>> getAllProducts() {
        List<Shoe> result = service.getAllShoes();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping(value = "edit",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editProduct(@RequestBody Shoe shoe) {
        String result = service.editShoe(shoe);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
