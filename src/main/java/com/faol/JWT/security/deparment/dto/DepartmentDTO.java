package com.faol.JWT.security.deparment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDTO implements Comparable<DepartmentDTO>{

    @NotNull(message = "Department id must be not null")
    private Long department_id;

    @NotBlank(message = "Department name must be not null")
    @Size(min = 3, max = 15)
    private String department_name;

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "department_id=" + department_id +
                ", department_name='" + department_name + '\'' +
                '}';
    }

    //to sort using department_id
    @Override
    public int compareTo(DepartmentDTO departmentDTO) {
        return department_id.compareTo(departmentDTO.getDepartment_id());
    }
}
