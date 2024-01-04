package com.faol.JWT.security.employee.repository;

import com.faol.JWT.security.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(
            value = "SELECT * FROM employee WHERE name= :name",
            nativeQuery = true
    )
    Optional<Employee> findEmployeeByName(@Param("name") String name);

    //Spring Boot query method:
    Optional<Employee> findByName(String name);

    @Query(
            value = "select * from employee where department_id = :department_id",
            nativeQuery = true
    )
    Optional<List<Employee>> findByDepartmentId(@Param("department_id") Long department_id);



}
