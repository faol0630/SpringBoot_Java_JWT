package com.faol.JWT.security;

import com.faol.JWT.security.deparment.entity.Department;
import com.faol.JWT.security.deparment.repository.DepartmentRepository;
import com.faol.JWT.security.employee.entity.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JwtSecurityApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JwtSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Successful start ");

	}

}
