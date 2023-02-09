package com.sebastian.ems.controller;

import com.sebastian.ems.model.Department;
import com.sebastian.ems.model.Position;
import com.sebastian.ems.model.User;
import com.sebastian.ems.service.DepartmentService;
import com.sebastian.ems.service.PositionService;
import com.sebastian.ems.service.UserService;
import com.sebastian.ems.dto.UserRegDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserRegController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    public UserRegController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegDto userRegDto() {
        return new UserRegDto();
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
        UserRegDto userRegDto = new UserRegDto();
        List<Department> departmentList = this.departmentService.getAllDepartments();
        List<Position> positionList = this.positionService.getAllPositions();

        model.addAttribute("user", userRegDto);
        model.addAttribute("departments", departmentList);
        model.addAttribute("positions", positionList);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("user") UserRegDto userDto, BindingResult result, Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "Account already registered!");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
        }
        userService.saveEmployee(userDto);
        return "redirect:/users";
    }

    @GetMapping("/updateEmployeeForm/{id}")
    public String updateEmployeeForm(@PathVariable( value = "id") long id, Model model) {

        // get employee from the service
        UserRegDto employee = userService.findUserById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("user", employee);
        return "update_employee";
    }

    @GetMapping("/users")
    public String viewHomePage(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id) {

        // call delete employee method
        this.userService.deleteEmployeeById(id);
        return "redirect:/users";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<UserRegDto> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<UserRegDto> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "users";
    }
}
