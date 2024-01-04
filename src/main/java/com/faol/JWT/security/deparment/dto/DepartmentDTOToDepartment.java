package com.faol.JWT.security.deparment.dto;

import com.faol.JWT.security.deparment.entity.Department;

public class DepartmentDTOToDepartment {

    public static Department departmentDTOToDepartment(DepartmentDTO departmentDTO){

        Department department = new Department();

        department.setDepartment_id(departmentDTO.getDepartment_id());
        department.setDepartment_name(departmentDTO.getDepartment_name());

        return department;

    }


}
