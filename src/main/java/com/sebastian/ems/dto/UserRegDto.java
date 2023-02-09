package com.sebastian.ems.dto;

import com.sebastian.ems.model.*;
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
    private EmployeeType employeeType;

    @NotEmpty
    private String dateBirth;

    @NotEmpty
    private Position position;

    @NotEmpty
    private Department department;

    @NotEmpty
    private EmployeeContract employeeContract;

    @NotEmpty
    private String contractStart;

    @NotEmpty
    private String contractEnd;

    @NotEmpty
    private Address address;

    @NotEmpty
    private Passport passport;

    @NotEmpty
    private CivilStatus civilStatus;
}
