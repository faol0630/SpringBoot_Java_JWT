package com.faol.JWT.security.deparment.service;

import com.faol.JWT.security.deparment.dto.DepartmentDTO;


import java.util.List;
import java.util.Optional;

public interface DepartmentServiceInt {

    Optional<List<DepartmentDTO>> getAllDeparments();
    Optional<DepartmentDTO> getDepartmentById(Long department_id);
    Optional<DepartmentDTO> getDepartmentByName(String department_name);
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
    Optional<DepartmentDTO> updateDepartment(Long department_id, DepartmentDTO departmentDTO);
    Optional<DepartmentDTO> deleteDepartment(Long department_id);
    String deleteAllDepartments();




}
