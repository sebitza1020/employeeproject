package com.sebastian.ems.controller;

import com.sebastian.ems.dto.EmployeeDto;
import com.sebastian.ems.model.Education;
import com.sebastian.ems.model.StudyForm;
import com.sebastian.ems.service.ItemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EducationController {
    @Autowired
    private ItemStorageService<EmployeeDto> employeeService;

    @Autowired
    private ItemStorageService<StudyForm> studyFormService;

    @Autowired
    private ItemStorageService<Education> educationService;

    @GetMapping("/education")
    public String education(Model model) {
        return findPaginatedEducation(1,"educationalInstitution", "asc", model);
    }

    @GetMapping("/newEducationForm")
    public String newEducationForm(Model model) {
        List<EmployeeDto> listEmployees = this.employeeService.getAllItems();
        List<StudyForm> listFormsOfStudy = this.studyFormService.getAllItems();

        Education education = new Education();

        // create model attribute to bind form data
        model.addAttribute("education", education);
        model.addAttribute("listEmployees", listEmployees);
        model.addAttribute("listFormsOfStudy", listFormsOfStudy);
        return "new_education";
    }

    @PostMapping("/saveEducation")
    public String saveEducation(@ModelAttribute("education") Education education) {
        educationService.saveItem(education);
        return "redirect:/education";
    }

    @GetMapping("/deleteEducation/{id}")
    public String deleteEducation(@PathVariable (value = "id") long id) {
        this.educationService.deleteItemById(id);
        return "redirect:/education";
    }

    @GetMapping("/education/page/{pageNo}")
    public String findPaginatedEducation(@PathVariable(value = "pageNo") int pageNo,
                                         @RequestParam("sortField") String sortField,
                                         @RequestParam("sortDir") String sortDir,
                                         Model model) {
        int pageSize = 5;

        Page<Education> page = educationService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Education> listEducation = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEducation", listEducation);
        return "education";
    }
}
