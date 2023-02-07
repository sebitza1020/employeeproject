package com.sebastian.ems.service;

import com.sebastian.ems.model.Employee;
import com.sebastian.ems.model.User;
import com.sebastian.ems.dto.UserRegDto;

import java.util.List;

public interface UserService {
    void saveEmployee(UserRegDto userRegDto);
    User findUserByEmail(String email);
    List<UserRegDto> findAllUsers();

    User getEmployeeById(long id);
}
