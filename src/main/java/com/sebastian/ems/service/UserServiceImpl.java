package com.sebastian.ems.service;

import com.sebastian.ems.model.Role;
import com.sebastian.ems.model.User;
import com.sebastian.ems.repository.RoleRepository;
import com.sebastian.ems.repository.UserRepository;
import com.sebastian.ems.dto.UserRegDto;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveEmployee(UserRegDto userRegDto) {
        User user = new User();
        user.setName(userRegDto.getFirstName() + " " + userRegDto.getLastName());
        user.setEmail(userRegDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = checkRoleExists();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserRegDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserRegDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserRegDto findUserById(long id) {
        Optional<User> optional = userRepository.findById(id);
        UserRegDto employee = null;
        if (optional.isPresent()) {
            employee = optional.stream().map(this::mapToUserRegDto).toList().get(Math.toIntExact(optional.get().getId()));
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return employee;
    }

    @Override
    public List<User> getAllEmployees() {
        return userRepository.findAll();
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public Page<UserRegDto> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<User> userPage = userRepository.findAll(pageable);
        return new PageImpl<>(userPage.getContent().stream().map(this::mapToUserRegDto)
                .collect(Collectors.toList()), pageable, userPage.getTotalElements());
    }

    private UserRegDto mapToUserRegDto(User user) {
        UserRegDto userRegDto = new UserRegDto();
        String[] str = user.getName().split(" ");
        userRegDto.setId(user.getId());
        userRegDto.setFirstName(str[0]);
        userRegDto.setLastName(str[1]);
        userRegDto.setEmail(user.getEmail());
        userRegDto.setDepartment(user.getDepartment());
        userRegDto.setPosition(user.getPosition());
        return userRegDto;
    }

    private Role checkRoleExists() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
