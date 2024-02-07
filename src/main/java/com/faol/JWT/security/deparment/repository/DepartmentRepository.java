package com.faol.JWT.security.deparment.repository;

import com.faol.JWT.security.deparment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(
            //do not leave space after : example ": deparment_name", this will give error
            value = "Select * from department where department_name = :department_name",
            nativeQuery = true
    )
    Optional<Department> findDepartmentByName(@Param("department_name") String department_name);




}
