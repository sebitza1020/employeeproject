package com.sebastian.ems.service;

import com.sebastian.ems.model.Department;
import com.sebastian.ems.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements ItemStorageService<Department> {

    @Autowired
    private DepartmentRepository deptRepository;

    @Override
    public List<Department> getAllItems() {
        return deptRepository.findAll();
    }

    @Override
    public void saveItem(Department department) {
        this.deptRepository.save(department);
    }

    @Override
    public Department findItemById(long id) {
        Optional<Department> optional = deptRepository.findById(id);
        Department department;
        if (optional.isPresent()) {
            department = optional.get();
        } else {
            throw new RuntimeException(" Department not found for id :: " + id);
        }
        return department;
    }

    @Override
    public void deleteItemById(long id) {
        this.deptRepository.deleteById(id);
    }

    @Override
    public Page<Department> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.deptRepository.findAll(pageable);
    }
}
