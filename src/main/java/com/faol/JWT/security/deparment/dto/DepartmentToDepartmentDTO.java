package com.faol.JWT.security.deparment.dto;

import com.faol.JWT.security.deparment.entity.Department;

public class DepartmentToDepartmentDTO {

    public static DepartmentDTO departmentToDepartmentDTO(Department department){

        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setDepartment_id(department.getDepartment_id());
        departmentDTO.setDepartment_name(department.getDepartment_name());

        return departmentDTO;

    }
}
