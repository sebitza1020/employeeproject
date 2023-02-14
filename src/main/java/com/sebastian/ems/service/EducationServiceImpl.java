package com.sebastian.ems.service;

import com.sebastian.ems.model.Education;
import com.sebastian.ems.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationServiceImpl implements ItemStorageService<Education> {

    @Autowired
    private EducationRepository educationRepository;

    @Override
    public List<Education> getAllItems() {
        return educationRepository.findAll();
    }

    @Override
    public void saveItem(Education item) {
        this.educationRepository.save(item);
    }

    @Override
    public Education findItemById(long id) {
        Optional<Education> optional = educationRepository.findById(id);
        Education education;
        if (optional.isPresent()) {
            education = optional.get();
        } else {
            throw new RuntimeException(" Education not found for id :: " + id);
        }

        return education;
    }

    @Override
    public void deleteItemById(long id) {
        this.educationRepository.deleteById(id);
    }

    @Override
    public Page<Education> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.educationRepository.findAll(pageable);
    }
}
