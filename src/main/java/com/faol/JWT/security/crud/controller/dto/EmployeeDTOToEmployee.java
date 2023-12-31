package com.faol.JWT.security.crud.controller.dto;

import com.faol.JWT.security.crud.entity.Employee;

public class EmployeeDTOToEmployee {

    public Employee employeeDTOToEmployee(EmployeeDTO employeeDTO){

        Employee employee = Employee.builder()
                .email(employeeDTO.getEmail())
                .employee_id(employeeDTO.getEmployee_id())
                .lastname(employeeDTO.getLastname())
                .name(employeeDTO.getName())
                .phone_number(employeeDTO.getPhone_number())
                .build();

        return employee;

    }
}
