package com.sebastian.ems.service;

import com.sebastian.ems.model.User;
import com.sebastian.ems.web.dto.UserRegDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegDto regDto);
}
