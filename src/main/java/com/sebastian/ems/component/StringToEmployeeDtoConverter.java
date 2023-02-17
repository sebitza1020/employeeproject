package com.sebastian.ems.component;

import com.sebastian.ems.dto.EmployeeDto;
import com.sebastian.ems.model.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEmployeeDtoConverter implements Converter<String, EmployeeDto> {
    @Override
    public EmployeeDto convert(String source) {
        String[] parts = source.split(",");
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(Long.parseLong(parts[0]));
        employeeDto.setFirstName(parts[1]);
        employeeDto.setLastName(parts[2]);
        employeeDto.setEmail(parts[3]);
        employeeDto.setPassword(parts[4]);
        EmployeeType employeeType = new EmployeeType();
        employeeType.setType(parts[5]);
        employeeDto.setEmployeeType(employeeType);
        employeeDto.setDateBirth(parts[6]);
        Department department = new Department();
        department.setDepartmentName(parts[7]);
        employeeDto.setDepartment(department);
        Position position = new Position();
        position.setPositionName(parts[8]);
        employeeDto.setPosition(position);
        EmployeeContracts employeeContracts = new EmployeeContracts();
        employeeContracts.setEmployeeContractName(parts[9]);
        employeeDto.setEmployeeContracts(employeeContracts);
        employeeDto.setContractStart(parts[10]);
        employeeDto.setContractEnd(parts[11]);
        employeeDto.setSalary(Integer.parseInt(parts[12]));
        Address address = new Address();
        address.setCountry(parts[12]);
        address.setCounty(parts[13]);
        address.setCity(parts[14]);
        address.setStreet(parts[15]);
        address.setApartmentNumber(Integer.parseInt(parts[16]));
        employeeDto.setAddress(address);
        Passport passport = new Passport();
        passport.setSeries(parts[17]);
        passport.setNumber(parts[18]);
        passport.setIssueDate(parts[19]);
        passport.setIssuePlace(parts[20]);
        passport.setRegistrationPlace(parts[21]);
        employeeDto.setPassport(passport);
        CivilStatus civilStatus = new CivilStatus();
        civilStatus.setStatus(parts[22]);
        employeeDto.setCivilStatus(civilStatus);
        return employeeDto;
    }
}
