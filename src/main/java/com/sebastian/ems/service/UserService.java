package com.sebastian.ems.service;

import com.sebastian.ems.model.User;
import com.sebastian.ems.dto.UserRegDto;

import java.util.List;

public interface UserService {
    void saveuser(UserRegDto userRegDto);
    User findUserByEmail(String email);
    List<UserRegDto> findAllUsers();
}
