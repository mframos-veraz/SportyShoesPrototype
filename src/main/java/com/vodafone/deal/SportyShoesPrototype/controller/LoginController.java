package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.Login;
import com.vodafone.deal.SportyShoesPrototype.domain.Order;
import com.vodafone.deal.SportyShoesPrototype.domain.Shoe;
import com.vodafone.deal.SportyShoesPrototype.domain.User;
import com.vodafone.deal.SportyShoesPrototype.service.ShoeService;
import com.vodafone.deal.SportyShoesPrototype.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShoeService productService;

    @GetMapping
    public String index(Model model, Login login) {

        model.addAttribute("login", login);
        return "login";
    }

    @PostMapping("login")
    public String login(Model model, Login login) {

        User user = userService.getUserByEmail(login.getUsername());
        if (user == null || !user.getPassword().equals(login.getPassword())) {
            model.addAttribute("message", "Wrong username or password.");
            model.addAttribute("login", login);
            return "login";
        }

        switch (user.getType()) {
            case "admin":
                return "admin";
            case "customer":
                List<Order> orders = user.getOrdersList();
                model.addAttribute("orders", orders);

                List<Shoe> products = productService.getAllShoes();
                model.addAttribute("products", products);

                model.addAttribute("user", user);
                return "customer";
            default:
                model.addAttribute("message", "Invalid user type.");
                model.addAttribute("login", login);
                return "login";
        }
    }

    @GetMapping("newUser")
    public String newUser(Model model, User user) {

        model.addAttribute("user", user);
        return "newUser";
    }

    @PostMapping("createUser")
    public String createUser(Model model, User user, Login login) {

        user.setType("customer");
        userService.createUser(user);

        model.addAttribute("message", "User registered!");
        model.addAttribute("login", login);
        return "login";
    }
}
