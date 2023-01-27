package com.sebastian.ems.service;

import com.sebastian.ems.model.User;
import com.sebastian.ems.web.dto.UserRegDto;

public interface UserService {
    User save(UserRegDto regDto);
}
