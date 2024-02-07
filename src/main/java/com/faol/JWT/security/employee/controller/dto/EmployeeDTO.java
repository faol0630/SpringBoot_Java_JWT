package com.faol.JWT.security.employee.controller.dto;

import com.faol.JWT.security.address.entity.Address;
import com.faol.JWT.security.deparment.entity.Department;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public class EmployeeDTO implements Comparable<EmployeeDTO>{

    private Long employee_id;

    @NotNull(message = "Name must not be null")
    @Size(min = 3, max = 15)
    private String name;

    @NotNull(message = "Lastname must not be null")
    @Size(min = 3, max = 15)
    private String lastname;

    @NotNull(message = "Phone number must not be null")
    @Size(max = 10)
    private String phone_number;

    @Email(message = "Email must be in a valid format")
    @NotNull(message = "Email must not be null")
    @Size(max = 30)
    private String email;

    //@Valid
    private Department department;

    //@Valid //to validate nested object
    private Address address;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long employee_id, String name, String lastname, String phone_number, String email, Department department, Address address) {
        this.employee_id = employee_id;
        this.name = name;
        this.lastname = lastname;
        this.phone_number = phone_number;
        this.email = email;
        this.department = department;
        this.address = address;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employee_id=" + employee_id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", department_id=" + department.getDepartment_id() +
                ", address_id=" + address.getAddress_id() +
                '}';
    }

    @Override
    public int compareTo(EmployeeDTO employeeDTO) {
        return employee_id.compareTo(employeeDTO.getEmployee_id());
    }
}
