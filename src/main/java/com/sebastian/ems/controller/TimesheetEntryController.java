package com.sebastian.ems.controller;

import com.sebastian.ems.model.TimesheetEntry;
import com.sebastian.ems.service.ItemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TimesheetEntryController {

    @Autowired
    private ItemStorageService<TimesheetEntry> entryService;

    @GetMapping("/entries")
    public String entries(Model model) {
        return findPaginatedEntries(1,"entryDate", "asc", model);
    }

    @GetMapping("/newEntryForm")
    public String newEntryForm(Model model) {
        TimesheetEntry timesheetEntry = new TimesheetEntry();
        model.addAttribute("entry", timesheetEntry);
        return "new_entry";
    }

    @PostMapping("/saveEntry")
    public String saveEntry(@ModelAttribute("entry") TimesheetEntry timesheetEntry) {
        entryService.saveItem(timesheetEntry);
        return "redirect:/entries";
    }

    @GetMapping("/entries/page/{pageNo}")
    public String findPaginatedEntries(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
                                           @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 5;

        Page<TimesheetEntry> page = entryService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<TimesheetEntry> entryList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEntries", entryList);
        return "entries";
    }

}
