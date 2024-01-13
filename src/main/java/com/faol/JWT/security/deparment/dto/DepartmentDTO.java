package com.faol.JWT.security.deparment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDTO {


    private Long department_id;
    private String department_name;

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "department_id=" + department_id +
                ", department_name='" + department_name + '\'' +
                '}';
    }
}
