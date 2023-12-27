package com.faol.JWT.security.crud.repository;

import com.faol.JWT.security.crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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



}
