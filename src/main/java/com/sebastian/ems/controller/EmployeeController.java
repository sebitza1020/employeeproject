package com.sebastian.ems.controller;

import com.sebastian.ems.model.*;
import com.sebastian.ems.repository.UserRepository;
import com.sebastian.ems.service.*;
import com.sebastian.ems.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private ItemStorageService<EmployeeDto> userService;

    @Autowired
    private ItemStorageService<Department> departmentService;

    @Autowired
    private ItemStorageService<Position> positionService;

    @Autowired
    private ItemStorageService<EmployeeContracts> employeeContractService;

    @Autowired
    private ItemStorageService<CivilStatus> civilStatusService;

    @Autowired
    private ItemStorageService<EmployeeType> employeeTypesService;

    @Autowired
    private ItemStorageService<FamilyMember> familyMemberService;

    @Autowired
    private ItemStorageService<KinshipDegree> kinshipDegreeService;

    @Autowired
    private ItemStorageService<StudyForm> studyFormService;

    @Autowired
    private UserRepository userRepository;

    public EmployeeController(ItemStorageService<EmployeeDto> userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public EmployeeDto employeeDto() {
        return new EmployeeDto();
    }

    @GetMapping("/newEmployeeForm")
    public String newEmployeeForm(Model model) {
        EmployeeDto employeeDto = new EmployeeDto();
        List<Department> departmentList = this.departmentService.getAllItems();
        List<Position> positionList = this.positionService.getAllItems();
        List<EmployeeContracts> employeeContractList = this.employeeContractService.getAllItems();
        List<EmployeeType> employeeTypeList = this.employeeTypesService.getAllItems();
        List<CivilStatus> civilStatusList = this.civilStatusService.getAllItems();

        model.addAttribute("user", employeeDto);
        model.addAttribute("departments", departmentList);
        model.addAttribute("positions", positionList);
        model.addAttribute("employeeContracts", employeeContractList);
        model.addAttribute("employeeTypes", employeeTypeList);
        model.addAttribute("listCivilStatus", civilStatusList);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("user") EmployeeDto employeeDto, Model model) {
        try {
            userService.saveItem(employeeDto);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Email address already in use. Please choose a different email address.");
            return "update_employee";
        }
        return "redirect:/users";
    }

    @GetMapping("/updateEmployeeForm/{id}")
    public String updateEmployeeForm(@PathVariable( value = "id") long id, Model model) {
        EmployeeDto employeeDto = userService.findItemById(id);
        List<Department> departmentList = this.departmentService.getAllItems();
        List<Position> positionList = this.positionService.getAllItems();
        List<EmployeeContracts> employeeContractList = this.employeeContractService.getAllItems();
        List<EmployeeType> employeeTypeList = this.employeeTypesService.getAllItems();
        List<CivilStatus> civilStatusList = this.civilStatusService.getAllItems();

        model.addAttribute("user", employeeDto);
        model.addAttribute("departments", departmentList);
        model.addAttribute("positions", positionList);
        model.addAttribute("employeeContracts", employeeContractList);
        model.addAttribute("employeeTypes", employeeTypeList);
        model.addAttribute("listCivilStatus", civilStatusList);
        return "update_employee";
    }

    @GetMapping("/users")
    public String viewHomePage(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") Long id) {
        this.userService.deleteItemById(id);
        return "redirect:/users";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<EmployeeDto> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<EmployeeDto> listEmployees = page.getContent();

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
