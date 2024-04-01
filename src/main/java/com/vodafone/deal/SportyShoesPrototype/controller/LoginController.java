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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Login login) {

        User user = service.getUserByEmail(login.getUsername());
        if (user == null) {

            System.out.println("null");
            return "login";
        }

        switch (user.getType()) {
            case "admin":
                return "admin";
            case "customer":
                return "customer";
            default:
                return "login"; //TODO: Add custom error.

        }
    }


}
