package com.sebastian.ems.service;

import com.sebastian.ems.model.Role;
import com.sebastian.ems.model.User;
import com.sebastian.ems.repository.RoleRepository;
import com.sebastian.ems.repository.UserRepository;
import com.sebastian.ems.dto.EmployeeDto;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements ItemStorageService<EmployeeDto>{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveItem(EmployeeDto employeeDto) {
        User user = new User();
        user.setName(employeeDto.getFirstName() + " " + employeeDto.getLastName());
        user.setEmail(employeeDto.getEmail());
        user.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = checkRoleExists();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<EmployeeDto> findAllItems() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserRegDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findItemById(long id) {
        Optional<User> optional = userRepository.findById(id);
        EmployeeDto employee = null;
        if (optional.isPresent()) {
            employee = optional.stream().map(this::mapToUserRegDto).toList().get(Math.toIntExact(optional.get().getId()));
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return employee;
    }

    @Override
    public List<EmployeeDto> getAllItems() {
        return userRepository.findAll().stream().map(this::mapToUserRegDto).toList();
    }

    @Override
    public void deleteItemById(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public Page<EmployeeDto> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<User> userPage = userRepository.findAll(pageable);
        return new PageImpl<>(userPage.getContent().stream().map(this::mapToUserRegDto)
                .collect(Collectors.toList()), pageable, userPage.getTotalElements());
    }

    private EmployeeDto mapToUserRegDto(User user) {
        EmployeeDto employeeDto = new EmployeeDto();
        String[] str = user.getName().split(" ");
        employeeDto.setId(user.getId());
        employeeDto.setFirstName(str[0]);
        employeeDto.setLastName(str[1]);
        employeeDto.setEmail(user.getEmail());
        employeeDto.setEmployeeType(user.getEmployeeType());
        employeeDto.setDateBirth(user.getDateBirth());
        employeeDto.setDepartment(user.getDepartment());
        employeeDto.setPosition(user.getPosition());
        employeeDto.setEmployeeContract(user.getEmployeeContract());
        employeeDto.setContractStart(user.getContractStart());
        employeeDto.setContractEnd(user.getContractEnd());
        employeeDto.setAddress(user.getAddress());
        employeeDto.setPassport(user.getPassport());
        employeeDto.setCivilStatus(user.getCivilStatus());
        return employeeDto;
    }

    private Role checkRoleExists() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
