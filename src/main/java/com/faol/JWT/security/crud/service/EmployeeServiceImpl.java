package com.faol.JWT.security.crud.service;

import com.faol.JWT.security.crud.entity.Employee;
import com.faol.JWT.security.crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeServiceInt {

    @Autowired
    EmployeeRepository repo;

    @Override
    public Optional<Employee> findEmployeeById(Long employee_id) {

        return repo.findById(employee_id);
    }

    @Override
    public Optional<Employee> findEmployeeByName(String name) {

        return repo.findEmployeeByName(name);

    }

    @Override
    public List<Employee> findAllEmployees() {

        List<Employee> employees = repo.findAll();
        return employees;

    }

    @Override
    public Employee saveNewEmployee(Employee employee) {

        Employee employee1 = repo.save(employee);
        return employee1;
    }

    @Override
    public Employee deleteEmployeeById(Long employee_id) {

        Optional<Employee> employee = repo.findById(employee_id);
        repo.deleteById(employee_id);
        return employee.get();

    }

    @Override
    public void deleteAllEmployees() {

        repo.deleteAll();

    }

    @Override
    public Employee updateEmployee(Long employee_id, Employee employee) {

        Employee newEmployee = repo.findById(employee_id).get();

        newEmployee.setEmail(employee.getEmail());
        newEmployee.setLastname(employee.getLastname());
        newEmployee.setName(employee.getName());
        newEmployee.setPhone_number(employee.getPhone_number());

        repo.save(newEmployee);
        return newEmployee;

    }
}
