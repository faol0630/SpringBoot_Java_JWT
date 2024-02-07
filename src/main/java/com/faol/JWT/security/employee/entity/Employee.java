package com.faol.JWT.security.employee.entity;

import com.faol.JWT.security.address.entity.Address;
import com.faol.JWT.security.deparment.entity.Department;
import jakarta.persistence.*;
import lombok.Builder;


@Entity
@Table(name = "employee")
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long employee_id;

    @Column(name = "name", nullable = false, unique = true, length = 40)
    private String name;

    @Column(name = "lastname", nullable = false, length = 40)
    private String lastname;

    @Column(name = "phone_number", nullable = false,  length = 10)
    private String phone_number;

    @Column(name = "email", nullable = false,  length = 40)
    private String email;

    //unidirectional
    @ManyToOne
    @JoinColumn(
            name = "department_id",
            referencedColumnName = "department_id"
    )
    private Department department;

    //unidirectional
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id")
    private Address address;



    public Employee() {
    }

    public Employee(Long employee_id, String name, String lastname, String phone_number, String email, Department department, Address address) {
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
        return "Employee{" +
                "employee_id=" + employee_id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", address_id='" + address.getAddress_id() + '\'' +
                ", department_id='" + department.getDepartment_id() + '\'' +
                '}';
    }
}

//postman, @Post  body example:

/*{
        "name": "Lorena",
        "lastname": "Palo",
        "phone_number": "111222333",
        "email": "lorenapa@net.live",
        "department" : {
            "department_id": 10
        },
        "address" : {
            "address_id": 1
        }

}*/

