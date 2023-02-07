package com.sebastian.ems.service;

import com.sebastian.ems.model.User;
import com.sebastian.ems.dto.UserRegDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void saveEmployee(UserRegDto userRegDto);
    User findUserByEmail(String email);
    List<UserRegDto> findAllUsers();
    User getEmployeeById(long id);
    List<User> getAllEmployees();
    void deleteEmployeeById(long id);
    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
