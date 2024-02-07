package com.faol.JWT.security.employee.service;


import com.faol.JWT.security.employee.controller.dto.EmployeeDTO;
import com.faol.JWT.security.employee.controller.dto.EmployeeDTOToEmployee;
import com.faol.JWT.security.employee.controller.dto.EmployeeToEmployeeDTO;
import com.faol.JWT.security.employee.entity.Employee;
import com.faol.JWT.security.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeServiceInt {

    @Autowired
    EmployeeRepository repo;

    private final EmployeeToEmployeeDTO employeeToEmployeeDTO;

    public EmployeeServiceImpl(){
        employeeToEmployeeDTO = new EmployeeToEmployeeDTO();
    }

    @Override
    public Optional<EmployeeDTO> findEmployeeById(Long employee_id) {

        Optional<Employee> employee = repo.findById(employee_id);

        return employee.map(EmployeeToEmployeeDTO::employeeToEmployeeDTO);


    }

    @Override
    public Optional<EmployeeDTO> findEmployeeByName(String name) {

        Optional<Employee> employee = repo.findEmployeeByName(name);

        return employee.map(EmployeeToEmployeeDTO::employeeToEmployeeDTO);

    }

    @Override
    public Optional<List<EmployeeDTO>> findAllEmployees() {

        List<Employee> employees = repo.findAll();

        if (!employees.isEmpty()) {

            List<EmployeeDTO> employeeDTOList = employees.stream().map(employee -> {

                return employeeToEmployeeDTO.employeeToEmployeeDTO(employee);

            }).collect(Collectors.toList());

            Collections.sort(employeeDTOList); //sorted by id

            return Optional.of(employeeDTOList);
        }else{

            return Optional.empty();
        }


    }

    @Override
    public EmployeeDTO saveNewEmployee(EmployeeDTO employeeDTO) {

        Employee newEmployee = EmployeeDTOToEmployee.employeeDTOToEmployee(employeeDTO);

        repo.save(newEmployee);
        return employeeDTO;

    }

    @Override
    public Optional<EmployeeDTO> deleteEmployeeById(Long employee_id) {

        Optional<Employee> employee = repo.findById(employee_id);
        Optional<EmployeeDTO> employeeDTOOptional = employee.map(EmployeeToEmployeeDTO::employeeToEmployeeDTO);
        repo.deleteById(employee_id);
        return employeeDTOOptional;

    }

    @Override
    public void deleteAllEmployees() {

        repo.deleteAll();

    }

    @Override
    public Optional<EmployeeDTO> updateEmployee(Long employee_id, EmployeeDTO employeeDTO) {

        Optional<Employee> newEmployee = repo.findById(employee_id);

        if (newEmployee.isPresent()){

            //repo.save(newEmployee.get());
            saveNewEmployee(employeeDTO);
            return Optional.of(employeeDTO);

        }else{
            return Optional.empty();
        }

    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(Long department_id) {
        Optional<List<Employee>> employees = repo.findByDepartmentId(department_id);

        List<Employee> employeeList = employees.get();
        return employeeList;

    }


}
