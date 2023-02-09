package com.sebastian.ems.controller;

import com.sebastian.ems.model.Position;
import com.sebastian.ems.service.ItemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PositionController {

    @Autowired
    private ItemStorageService<Position> positionService;

    @GetMapping("/positions")
    public String positions(Model model) {
        return findPaginatedWorkingPositions(1,"positionName", "asc", model);
    }

    @GetMapping("/newPositionForm")
    public String newPositionForm(Model model) {
        Position workingPosition = new Position();
        model.addAttribute("position", workingPosition);
        return "new_position";
    }

    @PostMapping("/savePosition")
    public String savePosition(@ModelAttribute("workingPosition") Position workingPosition) {
        positionService.saveItem(workingPosition);
        return "redirect:/positions";
    }

    @GetMapping("/updatePositionForm/{id}")
    public String updatePositionForm(@PathVariable( value = "id") long id, Model model) {
        Position workingPosition = positionService.findItemById(id);

        model.addAttribute("position", workingPosition);
        return "update_position";
    }

    @GetMapping("/deletePosition/{id}")
    public String deletePosition(@PathVariable (value = "id") long id) {
        this.positionService.deleteItemById(id);
        return "redirect:/positions";
    }

    @GetMapping("/positions/page/{pageNo}")
    public String findPaginatedWorkingPositions(@PathVariable (value = "pageNo") int pageNo,
                                               @RequestParam("sortField") String sortField,
                                               @RequestParam("sortDir") String sortDir,
                                               Model model) {
        int pageSize = 5;

        Page<Position> page = positionService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Position> listWorkingPositions = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listPositions", listWorkingPositions);
        return "positions";
    }
}
