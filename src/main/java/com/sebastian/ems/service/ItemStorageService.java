package com.sebastian.ems.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemStorageService<T> {
    List<T> getAllItems();
    void saveItem(T item);
    T findItemById(long id);
    void deleteItemById(long id);
    Page<T> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
