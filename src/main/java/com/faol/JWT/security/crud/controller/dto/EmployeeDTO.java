package com.faol.JWT.security.crud.controller.dto;

import lombok.Builder;

@Builder
public class EmployeeDTO {

    private Long employee_id;
    private String name;
    private String lastname;
    private String phone_number;
    private String email;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long employee_id, String name, String lastname, String phone_number, String email) {
        this.employee_id = employee_id;
        this.name = name;
        this.lastname = lastname;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employee_id=" + employee_id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
