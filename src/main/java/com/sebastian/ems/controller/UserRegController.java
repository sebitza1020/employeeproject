package com.sebastian.ems.controller;

import com.sebastian.ems.service.UserService;
import com.sebastian.ems.web.dto.UserRegDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegController {
    private UserService userService;

    public UserRegController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegDto userRegDto() {
        return new UserRegDto();
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")UserRegDto regDto) {
        userService.save(regDto);
        return "redirect:/registration?success";
    }
}
