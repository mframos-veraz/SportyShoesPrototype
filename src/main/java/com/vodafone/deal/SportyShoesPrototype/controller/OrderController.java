package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.Order;
import com.vodafone.deal.SportyShoesPrototype.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping(value = "new",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        String result = service.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(result.toString());
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteOrder(@RequestParam int id) {
        String result = service.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> result = service.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping(value = "edit",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editOrder(@RequestBody Order order) {
        String result = service.editOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
