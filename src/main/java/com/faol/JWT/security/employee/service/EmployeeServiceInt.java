package com.faol.JWT.security.employee.service;

import com.faol.JWT.security.employee.controller.dto.EmployeeDTO;
import com.faol.JWT.security.employee.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceInt {

    Optional<EmployeeDTO> findEmployeeById(Long employee_id);
    Optional<EmployeeDTO> findEmployeeByName(String name);
    Optional<List<EmployeeDTO>> findAllEmployees();
    EmployeeDTO saveNewEmployee(EmployeeDTO employeeDTO);
    Optional<EmployeeDTO> deleteEmployeeById(Long employee_id);
    void deleteAllEmployees();
    Optional<EmployeeDTO> updateEmployee(Long employee_id, EmployeeDTO employeeDTO);

    List<Employee> getEmployeesByDepartmentId(Long department_id);

}
