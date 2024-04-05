package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.service.ShoeService;
import com.vodafone.deal.SportyShoesPrototype.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private PersonService personService;
    @Autowired
    private ShoeService productService;

    @GetMapping
    public String index(Model model, ) {

        model.addAttribute("product", shoe);
        model.addAttribute("user", user);
        model.addAttribute("users", users);
        model.addAttribute("products", products);
        return "admin";
    }

//    @PostMapping("createUser")
//    public String createUser(Model model, User user) {
//
//        String message = userService.createUser(user);
//        return renderPage(model, message);
//    }
//
//    @PostMapping("editUser")
//    public String editUser(Model model, User user) {
//
//        String message = userService.editUser(user);
//        return renderPage(model, message);
//    }
//
//    @PostMapping("deleteUser")
//    public String deleteUser(Model model, User user) {
//
//        String message = userService.deleteUser(user.getId());
//        return renderPage(model, message);
//    }
//
//    @PostMapping("createProduct")
//    public String createProduct(Model model, Shoe shoe) {
//
//        String message = productService.createShoe(shoe);
//        return renderPage(model, message);
//    }
//
//    @PostMapping("editProduct")
//    public String editProduct(Model model, Shoe product) {
//
//        String message = productService.editShoe(product);
//        return renderPage(model, message);
//    }
//
//    @PostMapping("deleteProduct")
//    public String deleteProduct(Model model, Shoe product) {
//
//        String message = productService.deleteShoe(product.getId());
//        return renderPage(model, message);
//    }
//
//    private String renderPage(Model model, String message) {
//        model.addAttribute("message", message);
//
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("users", users);
//        List<Shoe> products = productService.getAllShoes();
//        model.addAttribute("products", products);
//        model.addAttribute("product", new Shoe());
//        model.addAttribute("user", new User());
//        return "admin";
//    }
}
