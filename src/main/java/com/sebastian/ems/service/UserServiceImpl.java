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
        user.setEmployeeType(employeeDto.getEmployeeType());
        user.setDepartment(employeeDto.getDepartment());
        user.setPosition(employeeDto.getPosition());
        user.setDateBirth(employeeDto.getDateBirth());
        user.setEmployeeContracts(employeeDto.getEmployeeContracts());
        user.setContractStart(employeeDto.getContractStart());
        user.setContractEnd(employeeDto.getContractEnd());
        user.setSalary(employeeDto.getSalary());
        user.setAddress(employeeDto.getAddress());
        user.setPassport(employeeDto.getPassport());
        user.setCivilStatus(employeeDto.getCivilStatus());

        userRepository.save(user);
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
        return optional.stream().map(this::mapToUserRegDto).findFirst()
                .orElseThrow(() -> new RuntimeException("Employee not found for id :: " + id));
    }

    @Override
    public List<EmployeeDto> getAllItems() {
        return userRepository.findAll().stream().map(this::mapToUserRegDto).toList();
    }

    @Override
    public void deleteItemById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        user.getRoles().clear();
        this.userRepository.delete(user);
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
        employeeDto.setPassword(user.getPassword());
        employeeDto.setEmployeeType(user.getEmployeeType());
        employeeDto.setDateBirth(user.getDateBirth());
        employeeDto.setDepartment(user.getDepartment());
        employeeDto.setPosition(user.getPosition());
        employeeDto.setEmployeeContracts(user.getEmployeeContracts());
        employeeDto.setContractStart(user.getContractStart());
        employeeDto.setContractEnd(user.getContractEnd());
        employeeDto.setSalary(user.getSalary());
        employeeDto.setAddress(user.getAddress());
        employeeDto.setPassport(user.getPassport());
        employeeDto.setCivilStatus(user.getCivilStatus());
        return employeeDto;
    }

    private Role checkRoleExists() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}
