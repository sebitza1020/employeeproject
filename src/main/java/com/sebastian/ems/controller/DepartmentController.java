package com.sebastian.ems.controller;

import com.sebastian.ems.model.Department;
import com.sebastian.ems.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService deptService;

    @GetMapping("/departments")
    public String departments(org.springframework.ui.Model model) {
        return findPaginatedDepartments(1,"departmentName", "asc", model);
    }

    @GetMapping("/newDepartmentForm")
    public String newDepartmentForm(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        return "new_department";
    }

    @PostMapping("/saveDepartment")
    public String saveDepartment(@ModelAttribute("department") Department department) {
        deptService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/updateDepartmentForm/{id}")
    public String updateDepartmentForm(@PathVariable(value = "id") long id, Model model) {
        Department department = deptService.findDepartmentById(id);

        model.addAttribute("department", department);
        return "update_department";
    }

    @GetMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable (value = "id") long id) {
        this.deptService.deleteDepartmentById(id);
        return "redirect:/departments";
    }

    @GetMapping("/departments/page/{pageNo}")
    public String findPaginatedDepartments(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
                                           @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 5;

        Page<Department> page = deptService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Department> departmentList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listDepartments", departmentList);
        return "departments";
    }
}
