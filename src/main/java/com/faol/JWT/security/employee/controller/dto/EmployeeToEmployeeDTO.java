package com.faol.JWT.security.employee.controller.dto;

import com.faol.JWT.security.employee.entity.Employee;

public class EmployeeToEmployeeDTO {

    public static EmployeeDTO employeeToEmployeeDTO(Employee employee){

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .email(employee.getEmail())
                .employee_id(employee.getEmployee_id())
                .lastname(employee.getLastname())
                .name(employee.getName())
                .phone_number(employee.getPhone_number())
                .department(employee.getDepartment())
                .address(employee.getAddress())
                .build();

        return employeeDTO;
    }
}
