package com.vodafone.deal.SportyShoesPrototype.controller;

import com.vodafone.deal.SportyShoesPrototype.service.LoginService;
import com.vodafone.deal.SportyShoesPrototype.service.ShoeService;
import com.vodafone.deal.SportyShoesPrototype.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class EntrypointController {

    @Autowired
    private CustomerService userService;
    @Autowired
    private ShoeService productService;
    @Autowired
    private LoginService loginService;

    @GetMapping
    public String login() {
        return "login";
    }

//    @PostMapping
//    public String login(Model model, Login login) {
//
//        UserDetails userDetails;
//        try {
//            userDetails = loginService.loadUserByUsername(login.getUsername());
//        } catch (UsernameNotFoundException exception) {
//            model.addAttribute("message", "Wrong username or password.");
//            model.addAttribute("login", login);
//            return "login";
//        }
//        if (encoder.matches(login.getPassword(), userDetails.getPassword())) {
//            model.addAttribute("message", "Wrong username or password.");
//            model.addAttribute("login", login);
//            return "login";
//        }
//
//        List<Shoe> products = productService.getAllShoes();
//        model.addAttribute("products", products);
//
//        switch (userDetails.getAuthorities().toString()) {
//            case "ADMIN":
//                List<User> users = userService.getAllUsers();
//                model.addAttribute("users", users);
//
//                model.addAttribute("product", new Shoe());
//                model.addAttribute("user", new User());
//                return "admin";
//            case "USER":
//                User user = userService.getPersonByEmail(userDetails.getUsername());
//
//                List<Order> orders = user.getOrdersList();
//                model.addAttribute("orders", orders);
//
//                model.addAttribute("user", user);
//                model.addAttribute("order", new Order());
//                model.addAttribute("product", new Shoe());
//                return "customer";
//            default:
//                model.addAttribute("message", "Invalid user type.");
//                model.addAttribute("login", login);
//                return "login";
//        }
//    }
//
//    @GetMapping("newUser")
//    public String newUser(Model model, User user) {
//
//        model.addAttribute("user", user);
//        return "newUser";
//    }
//
//    @PostMapping("newUser")
//    public String createUser(Model model, User user, Login login) {
//
//        user.setRole("USER");
//        userService.createUser(user);
//
//        model.addAttribute("message", "User registered!");
//        model.addAttribute("login", login);
//        return "login";
//    }
}
