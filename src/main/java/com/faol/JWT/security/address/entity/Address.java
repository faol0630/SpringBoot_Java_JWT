package com.faol.JWT.security.address.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "address")
//@Validated //because Address is a nested object in EmployeeDTO
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;

    @NotBlank(message = "Street must not be blank or null")
    @Size(min = 3, max = 15)
    private String street;

    @NotBlank(message = "Number must not be null or blank")
    @Size(min = 1, max = 3)
    private String number;

    @NotBlank(message = "Postcode must not be null or blank")
    @Size(max = 5)
    private String postcode;

    @Override
    public String toString() {
        return "Address{" +
                "address_id=" + address_id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}
