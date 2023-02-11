package com.sebastian.ems.controller;

import com.sebastian.ems.dto.EmployeeDto;
import com.sebastian.ems.model.FamilyMember;
import com.sebastian.ems.model.KinshipDegree;
import com.sebastian.ems.service.ItemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FamilyMemberController {

    @Autowired
    private ItemStorageService<EmployeeDto> employeeService;

    @Autowired
    private ItemStorageService<KinshipDegree> kinshipDegreeService;

    @Autowired
    private ItemStorageService<FamilyMember> familyMemberService;

    @GetMapping("/familyMembers")
    public String familyMembers(Model model) {
        return findPaginatedFamilyMembers(1,"firstName", "asc", model);
    }

    @GetMapping("/showNewFamilyMemberForm")
    public String showNewFamilyMemberForm(Model model) {
        List<EmployeeDto> listEmployees = this.employeeService.getAllItems();
        List<KinshipDegree> listKinshipDegree = this.kinshipDegreeService.getAllItems();

        // create model attribute to bind form data
        FamilyMember familyMember = new FamilyMember();
        model.addAttribute("familyMember", familyMember);
        model.addAttribute("listEmployees", listEmployees);
        model.addAttribute("listKinshipDegree", listKinshipDegree);
        return "new_family_member";
    }

    @PostMapping("/saveFamilyMember")
    public String saveFamilyMember(@ModelAttribute("familyMember") FamilyMember familyMember) {
        familyMemberService.saveItem(familyMember);
        return "redirect:/familyMembers";
    }

    @GetMapping("/deleteFamilyMember/{id}")
    public String deleteFamilyMember(@PathVariable(value = "id") long id) {
        this.familyMemberService.deleteItemById(id);
        return "redirect:/familyMembers";
    }

    @GetMapping("/familyMembers/page/{pageNo}")
    public String findPaginatedFamilyMembers(@PathVariable (value = "pageNo") int pageNo,
                                             @RequestParam("sortField") String sortField,
                                             @RequestParam("sortDir") String sortDir,
                                             Model model) {
        int pageSize = 5;

        Page<FamilyMember> page = familyMemberService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<FamilyMember> listFamilyMembers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listFamilyMembers", listFamilyMembers);
        return "family_members";
    }
}
