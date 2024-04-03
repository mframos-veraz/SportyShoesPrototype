package com.vodafone.deal.SportyShoesPrototype.repository;

import com.vodafone.deal.SportyShoesPrototype.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer> {

    @Query("select order from Order order where order.userId = :userId")
    List<Order> getOrdersByUserId(int userId);
}
