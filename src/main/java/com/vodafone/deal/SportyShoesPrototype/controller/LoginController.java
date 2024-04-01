package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.Login;
import com.vodafone.deal.SportyShoesPrototype.domain.User;
import com.vodafone.deal.SportyShoesPrototype.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private UserService service;

    @GetMapping
    public String index(Model model, Login login) {

        model.addAttribute("login", login);
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(Model model, Login login) {

        User user = service.getUserByEmail(login.getUsername());
        if (user == null || !user.getPassword().equals(login.getPassword())) {
            model.addAttribute("message", "Wrong username or password.");
            model.addAttribute("login", login);
            return "login";
        }

        switch (user.getType()) {
            case "admin":
                return "admin";
            case "customer":
                return "customer";
            default:
                model.addAttribute("message", "Invalid user type.");
                model.addAttribute("login", login);
                return "login";
        }
    }

    @RequestMapping(value = "newUser", method = RequestMethod.GET)
    public String newUser(Model model, User user) {

        model.addAttribute("user", user);
        return "newUser";
    }

    @RequestMapping(value = "createUser", method = RequestMethod.POST)
    public String createUser(Model model, User user, Login login) {

        user.setType("customer");
        service.createUser(user);

        model.addAttribute("message", "User registered!");
        model.addAttribute("login", login);
        return "login";
    }
}
