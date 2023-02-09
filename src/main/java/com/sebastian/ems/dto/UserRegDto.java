package com.sebastian.ems.dto;

import com.sebastian.ems.model.Department;
import com.sebastian.ems.model.EmployeeContract;
import com.sebastian.ems.model.Position;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegDto
{
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password shouldn't be empty")
    private String password;

    @NotEmpty
    private Date dateBirth;

    @NotEmpty
    private Position position;

    @NotEmpty
    private Department department;

    @NotEmpty
    private EmployeeContract employeeContract;
}
