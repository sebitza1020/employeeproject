package com.sebastian.ems.controller;

import com.sebastian.ems.model.Employee;
import com.sebastian.ems.model.User;
import com.sebastian.ems.service.UserService;
import com.sebastian.ems.dto.UserRegDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserRegController {

    private UserService userService;

    public UserRegController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegDto userRegDto() {
        return new UserRegDto();
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserRegDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserRegDto user = new UserRegDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserRegDto userDto, BindingResult result, Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "Account already registered!");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
        }
        userService.saveEmployee(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/newEmployeeForm")
    public String newEmployeeForm(Model model) {
        // create model attribute to bind form data
        UserRegDto userRegDto = new UserRegDto();
        model.addAttribute("user", userRegDto);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") UserRegDto userDto, BindingResult result, Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "Account already registered!");
        }
        if (result.hasErrors()) {
            model.addAttribute("employee", userDto);
        }
        userService.saveEmployee(userDto);
        return "redirect:/users";
    }
}
