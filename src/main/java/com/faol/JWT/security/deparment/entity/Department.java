package com.faol.JWT.security.deparment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "department")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Validated
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long department_id;

    @Column(unique = true)
    @Size(min = 3, max = 15)
    @NotBlank(message = "Department name must not be null or blank")
    private String department_name;

    @Override
    public String toString() {
        return "Department{" +
                "department_id=" + department_id +
                ", department_name='" + department_name + '\'' +
                '}';
    }

}
