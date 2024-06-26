package com.vodafone.deal.SportyShoesPrototype.service;

import com.vodafone.deal.SportyShoesPrototype.domain.Order;
import com.vodafone.deal.SportyShoesPrototype.domain.Shoe;
import com.vodafone.deal.SportyShoesPrototype.repository.OrderRepository;
import com.vodafone.deal.SportyShoesPrototype.repository.ShoeRepository;
import com.vodafone.deal.SportyShoesPrototype.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ShoeRepository productRepository;

    public String createOrder(Shoe product, int userId) {

        if (personRepository.findById(userId).isEmpty()) {
            return "User does not exist";
        }

        Optional<Shoe> productRecord = productRepository.findById(product.getId());
        if (productRecord.isEmpty()) {
            return "Product does not exist";
        }

        product = productRecord.get();
        if (product.getStock() > 0) {
            product.setStock(product.getStock() - 1);
            productRepository.saveAndFlush(product);

            Order order = new Order(
                    product.getId(),
                    userId,
                    LocalDateTime.now());
            orderRepository.save(order);

            return "Order created";
        }
        return "No stock available";
    }

    public String deleteOrder(int id) {
        Optional<Order> orderRecord = orderRepository.findById(id);
        if (orderRecord.isPresent()) {
            orderRepository.deleteById(id);
            return "Order deleted";
        }
        return "No order found";
    }

    public String editOrder(Order order) {
        Optional<Order> record = orderRepository.findById(order.getId());
        if (record.isPresent()) {
            Order orderRecord = record.get();
            orderRecord.setProductId(order.getProductId());
            orderRecord.setDate(order.getDate());
            orderRepository.saveAndFlush(orderRecord);
            return "Order edited";
        }
        return "No order found";
    }

    public List<Order> getAllOrders() { return orderRepository.findAll(); }

    public List<Order> getOrdersByUserId(int userId) { return orderRepository.getOrdersByUserId(userId); };
}
