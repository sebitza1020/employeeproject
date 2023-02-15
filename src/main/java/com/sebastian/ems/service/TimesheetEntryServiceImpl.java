package com.sebastian.ems.service;

import com.sebastian.ems.model.Department;
import com.sebastian.ems.model.TimesheetEntry;
import com.sebastian.ems.repository.TimesheetEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class TimesheetEntryServiceImpl implements ItemStorageService<TimesheetEntry> {

    @Autowired
    private TimesheetEntryRepository timesheetEntryRepository;

    @Override
    public List<TimesheetEntry> getAllItems() {
        return timesheetEntryRepository.findAll();
    }

    @Override
    public void saveItem(TimesheetEntry item) {
        this.timesheetEntryRepository.save(item);
    }

    @Override
    public TimesheetEntry findItemById(long id) {
        Optional<TimesheetEntry> optional = timesheetEntryRepository.findById(id);
        TimesheetEntry timesheetEntry;
        if (optional.isPresent()) {
            timesheetEntry = optional.get();
        } else {
            throw new RuntimeException(" Department not found for id :: " + id);
        }
        return timesheetEntry;
    }

    @Override
    public void deleteItemById(long id) {
        this.timesheetEntryRepository.deleteById(id);
    }

    @Override
    public Page<TimesheetEntry> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.timesheetEntryRepository.findAll(pageable);
    }
}
