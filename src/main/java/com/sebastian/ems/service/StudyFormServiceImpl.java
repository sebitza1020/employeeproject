package com.sebastian.ems.service;

import com.sebastian.ems.model.StudyForm;
import com.sebastian.ems.repository.StudyFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyFormServiceImpl implements ItemStorageService<StudyForm> {

    @Autowired
    private StudyFormRepository studyFormRepository;

    @Override
    public List<StudyForm> getAllItems() {
        return studyFormRepository.findAll();
    }

    @Override
    public void saveItem(StudyForm item) {

    }

    @Override
    public StudyForm findItemById(long id) {
        return null;
    }

    @Override
    public void deleteItemById(long id) {

    }

    @Override
    public Page<StudyForm> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        return null;
    }
}
