package com.sebastian.ems.controller;

import com.sebastian.ems.model.EmployeeContracts;
import com.sebastian.ems.service.ItemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeContractController {

    @Autowired
    private ItemStorageService<EmployeeContracts> employeeContractService;

    @GetMapping("/employeeContracts")
    public String employeeContracts(Model model) {
        return findPaginatedEmploymentContracts(1,"employeeContractName", "asc", model);
    }

    @GetMapping("/newContractForm")
    public String newContractForm(Model model) {
        EmployeeContracts employmentContracts = new EmployeeContracts();
        model.addAttribute("employeeContract", employmentContracts);
        return "new_contract";
    }

    @PostMapping("/saveContract")
    public String saveContract(@ModelAttribute("employeeContract") EmployeeContracts employmentContract) {
        employeeContractService.saveItem(employmentContract);
        return "redirect:/employeeContracts";
    }

    @GetMapping("/updateContractForm/{id}")
    public String updateContractForm(@PathVariable( value = "id") long id, Model model) {
        EmployeeContracts employmentContract = employeeContractService.findItemById(id);

        model.addAttribute("employeeContract", employmentContract);
        return "update_contract";
    }

    @GetMapping("/deleteContract/{id}")
    public String deleteContract(@PathVariable (value = "id") long id) {
        this.employeeContractService.deleteItemById(id);
        return "redirect:/employeeContracts";
    }

    @GetMapping("/employeeContracts/page/{pageNo}")
    public String findPaginatedEmploymentContracts(@PathVariable (value = "pageNo") int pageNo,
                                                   @RequestParam("sortField") String sortField,
                                                   @RequestParam("sortDir") String sortDir,
                                                   Model model) {
        int pageSize = 5;

        Page<EmployeeContracts> page = employeeContractService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<EmployeeContracts> listContracts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listContracts", listContracts);
        return "employee_contracts";
    }
}
