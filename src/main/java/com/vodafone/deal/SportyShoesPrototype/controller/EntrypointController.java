package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.Person;
import com.vodafone.deal.SportyShoesPrototype.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class EntrypointController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String login() {
        return "login";
    }

    @GetMapping("newUser")
    public String newUser(Model model, Person person) {

        model.addAttribute("person", person);
        return "newUser";
    }

    @PostMapping("newUser")
    public String createUser(Model model, Person person) {

        person.setRole("USER");
        personService.createPerson(person);

        model.addAttribute("message", "User registered!");
        return "login";
    }
}
