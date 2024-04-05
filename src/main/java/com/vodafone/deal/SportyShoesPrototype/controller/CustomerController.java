package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.Order;
import com.vodafone.deal.SportyShoesPrototype.domain.Shoe;
import com.vodafone.deal.SportyShoesPrototype.service.LoginService;
import com.vodafone.deal.SportyShoesPrototype.service.OrderService;
import com.vodafone.deal.SportyShoesPrototype.service.ShoeService;
import com.vodafone.deal.SportyShoesPrototype.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    private PersonService personService;
    @Autowired
    private LoginService loginService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal User user) {


        Integer userId = personService.getPersonIdByEmail(user.getUsername());
        List<Order> orders = orderService.getOrdersByUserId(userId);
        model.addAttribute("orders", orders);

        model.addAttribute("products", productService.getAllShoes());
        model.addAttribute("order", new Order());
        model.addAttribute("product", new Shoe());
        return "customer";
    }

//    @PostMapping("deleteOrder")
//    public String deleteOrder(Model model, Order order) {
//
//        String message = orderService.deleteOrder(order.getId());
//        return renderPage(model, user, message);
//    }

//    @PostMapping("orderProduct")
//    public String orderProduct(Model model, Shoe product) {
//
//        String message = orderService.createOrder(product, user.getId());
//        return renderPage(model, user, message);
//    }
}
