package com.example.spring_boot.web.controller;

import com.example.spring_boot.web.model.User;
import com.example.spring_boot.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/create")
    public String showUsersCreationPage(ModelMap map) {
        final User user = new User();
        map.addAttribute("user", user);
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, ModelMap map) {
        userService.addUser(user);
        final String message = "User was added";
        map.addAttribute("user", new User());
        map.addAttribute("message", message);
        return "create";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String showUpdatePage(@RequestParam("id") Long id, ModelMap map) {
        map.addAttribute("user", userService.getUser(id));
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
