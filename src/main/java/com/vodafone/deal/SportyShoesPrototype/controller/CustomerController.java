package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.Order;
import com.vodafone.deal.SportyShoesPrototype.domain.Shoe;
import com.vodafone.deal.SportyShoesPrototype.domain.User;
import com.vodafone.deal.SportyShoesPrototype.service.OrderService;
import com.vodafone.deal.SportyShoesPrototype.service.ShoeService;
import com.vodafone.deal.SportyShoesPrototype.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private ShoeService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    private User user;

    @GetMapping
    public String index(Model model, User user, List<Order> orders, List<Shoe> products) {

        this.user = user;

        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        model.addAttribute("products", products);
        model.addAttribute("order", new Order());
        model.addAttribute("product", new Shoe());
        return "customer";
    }

    @PostMapping("deleteOrder")
    public String deleteOrder(Model model, Order order) {

        String message = orderService.deleteOrder(order.getId());
        return renderPage(model, user, message);
    }

    @PostMapping("orderProduct")
    public String orderProduct(Model model, Shoe product) {

        String message = orderService.createOrder(product, user.getId());
        return renderPage(model, user, message);
    }

    private String renderPage(Model model, User user, String message) {
        model.addAttribute("message", message);

        List<Order> orders = user.getOrdersList();
        model.addAttribute("orders", orders);
        List<Shoe> products = productService.getAllShoes();
        model.addAttribute("products", products);
        model.addAttribute("order", new Order());
        model.addAttribute("product", new Shoe());
        return "customer";
    }
}
