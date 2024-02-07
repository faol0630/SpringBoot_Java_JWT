package com.faol.JWT.security.employee.controller.dto;


import com.faol.JWT.security.employee.entity.Employee;

public class EmployeeDTOToEmployee {

    public static Employee employeeDTOToEmployee(EmployeeDTO employeeDTO){


        Employee employee = Employee.builder()
                .email(employeeDTO.getEmail())
                .employee_id(employeeDTO.getEmployee_id())
                .lastname(employeeDTO.getLastname())
                .name(employeeDTO.getName())
                .phone_number(employeeDTO.getPhone_number())
                .department(employeeDTO.getDepartment())
                .address(employeeDTO.getAddress())
                .build();

        return employee;

    }
}
