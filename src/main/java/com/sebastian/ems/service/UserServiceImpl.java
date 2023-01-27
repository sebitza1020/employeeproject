package com.sebastian.ems.service;

import com.sebastian.ems.model.Role;
import com.sebastian.ems.model.User;
import com.sebastian.ems.repository.UserRepository;
import com.sebastian.ems.web.dto.UserRegDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegDto regDto) {
        User user = new User(regDto.getFirstName(), regDto.getLastName(), regDto.getEmail(), regDto.getPassword(),
                Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }
}
