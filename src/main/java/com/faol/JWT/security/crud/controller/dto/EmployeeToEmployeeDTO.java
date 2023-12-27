package com.faol.JWT.security.crud.controller.dto;

import com.faol.JWT.security.crud.entity.Employee;

public class EmployeeToEmployeeDTO {

    public EmployeeDTO employeeToEmployeeDTO(Employee employee){

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .email(employee.getEmail())
                .employee_id(employee.getEmployee_id())
                .lastname(employee.getLastname())
                .name(employee.getName())
                .phone_number(employee.getPhone_number())
                .build();

        return employeeDTO;
    }
}
