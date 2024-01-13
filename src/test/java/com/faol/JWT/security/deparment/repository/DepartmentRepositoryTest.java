package com.faol.JWT.security.deparment.repository;

import com.faol.JWT.security.deparment.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository repo;

    @Test
    public void findAllDepartments() {
        List<Department> departmentList = repo.findAll();
        System.out.println("Departement's list" + departmentList);
    }

    @Test
    public void SaveDepartment() {

        Department department = Department.builder()
                .department_id(34L)
                .department_name("Calidad")
                .build();
        if (department.getDepartment_name() != "Calidad") {
            repo.save(department);

        }

    }

    @Test
    public void FindDepartmentById() {

        Long department_id = 11L;
        Department department = repo.findById(department_id).get();

        System.out.println("department.getDepartment_id()" + department.getDepartment_id());
        System.out.println("department.getDepartment_name()" + department.getDepartment_name());

    }

}