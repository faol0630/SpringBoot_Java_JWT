package com.faol.JWT.security.employee.repository;

import com.faol.JWT.security.deparment.entity.Department;
import com.faol.JWT.security.deparment.repository.DepartmentRepository;
import com.faol.JWT.security.employee.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    Department department;
    Long department_id;
    List<Employee> employeeList;
    String name;

    @BeforeEach
    public void before() {
        department = departmentRepository.findById(8L).get();
        department_id = 8L;
        employeeList = employeeRepository.findAll();
        name = "Pedro";

    }

    @Test
    public void saveEmployee() {

        Employee employee = Employee.builder()
                .employee_id(33L)
                .name("Pedro")
                .lastname("Night")
                .email("pedron2@live.com")
                .department(department)
                .phone_number("546342189")
                .build();

        if (employee.getName() != name) {
            employeeRepository.save(employee);

        }

    }

    @Test
    public void findEmployeesByDepartmentId() {

        employeeRepository.findByDepartmentId(department_id).get();
    }

    @Test
    public void findEmployeeByName(){

        employeeRepository.findByName(name);
    }

}