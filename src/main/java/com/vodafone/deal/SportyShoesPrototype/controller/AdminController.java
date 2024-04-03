package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.domain.Shoe;
import com.vodafone.deal.SportyShoesPrototype.domain.User;
import com.vodafone.deal.SportyShoesPrototype.service.ShoeService;
import com.vodafone.deal.SportyShoesPrototype.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShoeService productService;

    @GetMapping
    public String index(Model model, List<User> users, List<Shoe> products, User user, Shoe shoe) {

        model.addAttribute("product", shoe);
        model.addAttribute("user", user);
        model.addAttribute("users", users);
        model.addAttribute("products", products);
        return "admin";
    }

    @PostMapping("createUser")
    public String createUser(Model model, User user) {

        String message = userService.createUser(user);
        model.addAttribute("message", message);

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        List<Shoe> products = productService.getAllShoes();
        model.addAttribute("products", products);
        model.addAttribute("product", new Shoe());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("editUser")
    public String editUser(Model model, User user) {

        String message = userService.editUser(user);
        model.addAttribute("message", message);

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        List<Shoe> products = productService.getAllShoes();
        model.addAttribute("products", products);
        model.addAttribute("product", new Shoe());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("deleteUser")
    public String deleteUser(Model model, User user) {

        String message = userService.deleteUser(user.getId());
        model.addAttribute("message", message);

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        List<Shoe> products = productService.getAllShoes();
        model.addAttribute("products", products);
        model.addAttribute("product", new Shoe());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("createProduct")
    public String createProduct(Model model, Shoe shoe) {

        String message = productService.createShoe(shoe);
        model.addAttribute("message", message);

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        List<Shoe> products = productService.getAllShoes();
        model.addAttribute("products", products);
        model.addAttribute("product", new Shoe());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("editProduct")
    public String editProduct(Model model, Shoe product) {

        String message = productService.editShoe(product);
        model.addAttribute("message", message);

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        List<Shoe> products = productService.getAllShoes();
        model.addAttribute("products", products);
        model.addAttribute("product", new Shoe());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("deleteProduct")
    public String deleteProduct(Model model, Shoe product) {

        String message = productService.deleteShoe(product.getId());
        model.addAttribute("message", message);

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        List<Shoe> products = productService.getAllShoes();
        model.addAttribute("products", products);
        model.addAttribute("product", new Shoe());
        model.addAttribute("user", new User());
        return "admin";
    }
}
