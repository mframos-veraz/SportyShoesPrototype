package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.Shoe;
import com.vodafone.deal.SportyShoesPrototype.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @GetMapping
    public String index(Model model, List<User> users, List<Shoe> products) {

        model.addAttribute("users", users);
        model.addAttribute("products", products);
        return "admin";
    }
}
