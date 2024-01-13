package com.faol.JWT.security.employee.service;

import com.faol.JWT.security.employee.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceInt {

    Optional<Employee> findEmployeeById(Long employee_id);
    Optional<Employee> findEmployeeByName(String name);
    List<Employee> findAllEmployees();
    Employee saveNewEmployee(Employee employee);
    Employee deleteEmployeeById(Long employee_id);
    void deleteAllEmployees();
    Employee updateEmployee(Long employee_id, Employee employee);

    List<Employee> getEmployeesByDepartmentId(Long department_id);

}
