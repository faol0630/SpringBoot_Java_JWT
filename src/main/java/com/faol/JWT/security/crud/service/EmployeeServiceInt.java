package com.faol.JWT.security.crud.service;

import com.faol.JWT.security.crud.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceInt {

    Optional<Employee> findEmployeeById(Long employee_id); //optional
    Optional<Employee> findEmployeeByName(String name); //optional
    List<Employee> findAllEmployees();
    Employee saveNewEmployee(Employee employee); // como parametro pasa el employee_id
    Employee deleteEmployeeById(Long employee_id); // void
    void deleteAllEmployees();
    Employee updateEmployee(Long employee_id, Employee employee);

}
