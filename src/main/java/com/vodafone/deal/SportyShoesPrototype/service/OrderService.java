package com.vodafone.deal.SportyShoesPrototype.service;

import com.vodafone.deal.SportyShoesPrototype.domain.Order;
import com.vodafone.deal.SportyShoesPrototype.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public String createOrder(Order order) {
        Order record = new Order(
                order.getProductId(),
                LocalDateTime.now());
        repository.save(record);
        return "Order created";
    }

    public String deleteOrder(int id) {
        Optional<Order> record = repository.findById(id);
        if (record.isPresent()) {
            repository.deleteById(id);
            return "Order deleted";
        }
        return "No order found";
    }

    public String editOrder(Order order) {
        Optional<Order> record = repository.findById(order.getId());
        if (record.isPresent()) {
            Order orderRecord = record.get();
            orderRecord.setProductId(order.getProductId());
            orderRecord.setDate(order.getDate());
            repository.saveAndFlush(orderRecord);
            return "Order edited";
        }
        return "No order found";
    }

    public List<Order> getAllOrders() { return repository.findAll(); }
}
