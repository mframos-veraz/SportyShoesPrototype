package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.Person;
import com.vodafone.deal.SportyShoesPrototype.domain.Shoe;
import com.vodafone.deal.SportyShoesPrototype.service.PersonService;
import com.vodafone.deal.SportyShoesPrototype.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private PersonService personService;
    @Autowired
    private ShoeService productService;

    @GetMapping
    public String index(Model model) {

        return renderPage(model);
    }

    private String renderPage(Model model) {
        List<Person> persons = personService.getAllPersons();
        model.addAttribute("persons", persons);
        List<Shoe> products = productService.getAllShoes();
        model.addAttribute("products", products);
        model.addAttribute("person", new Person());
        model.addAttribute("product", new Shoe());
        return "admin";
    }

    @PostMapping("createPerson")
    public String createPerson(Model model, Person person) {

        String message = personService.createPerson(person);
        model.addAttribute("message", message);

        return renderPage(model);
    }

    @PostMapping("editPerson")
    public String editUser(Model model, Person person) {

        String message = personService.editPerson(person);
        model.addAttribute("message", message);

        return renderPage(model);
    }

    @PostMapping("deletePerson")
    public String deleteUser(Model model, Person person) {

        String message = personService.deletePerson(person.getId());
        model.addAttribute("message", message);

        return renderPage(model);
    }

    @PostMapping("createProduct")
    public String createProduct(Model model, Shoe product) {

        String message = productService.createShoe(product);
        model.addAttribute("message", message);

        return renderPage(model);
    }

    @PostMapping("editProduct")
    public String editProduct(Model model, Shoe product) {

        String message = productService.editShoe(product);
        model.addAttribute("message", message);

        return renderPage(model);
    }

    @PostMapping("deleteProduct")
    public String deleteProduct(Model model, Shoe product) {

        String message = productService.deleteShoe(product.getId());
        model.addAttribute("message", message);

        return renderPage(model);
    }
}
