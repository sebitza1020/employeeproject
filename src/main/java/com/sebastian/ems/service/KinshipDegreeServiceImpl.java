package com.sebastian.ems.service;

import com.sebastian.ems.model.KinshipDegree;
import com.sebastian.ems.repository.KinshipDegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class KinshipDegreeServiceImpl implements ItemStorageService<KinshipDegree> {

    @Autowired
    private KinshipDegreeRepository kinshipDegreeRepository;

    @Override
    public List<KinshipDegree> getAllItems() {
        return kinshipDegreeRepository.findAll();
    }

    @Override
    public void saveItem(KinshipDegree item) {
        this.kinshipDegreeRepository.save(item);
    }

    @Override
    public KinshipDegree findItemById(long id) {
        Optional<KinshipDegree> optional = kinshipDegreeRepository.findById(id);
        KinshipDegree kinshipDegree;
        if (optional.isPresent()) {
            kinshipDegree = optional.get();
        } else {
            throw new RuntimeException(" KinshipDegree not found for id :: " + id);
        }

        return kinshipDegree;
    }

    @Override
    public void deleteItemById(long id) {
        this.kinshipDegreeRepository.deleteById(id);
    }

    @Override
    public Page<KinshipDegree> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.kinshipDegreeRepository.findAll(pageable);
    }
}
