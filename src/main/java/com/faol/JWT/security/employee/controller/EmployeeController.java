package com.faol.JWT.security.employee.controller;

import com.faol.JWT.security.employee.controller.dto.EmployeeDTO;
import com.faol.JWT.security.employee.controller.dto.EmployeeDTOToEmployee;
import com.faol.JWT.security.employee.controller.dto.EmployeeToEmployeeDTO;
import com.faol.JWT.security.employee.entity.Employee;
import com.faol.JWT.security.employee.service.EmployeeServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeDTOToEmployee employeeDTOToEmployee ;

    private final EmployeeToEmployeeDTO employeeToEmployeeDTO ;

    public EmployeeController(){
        employeeDTOToEmployee = new EmployeeDTOToEmployee();
        employeeToEmployeeDTO = new EmployeeToEmployeeDTO();
    }

    @Autowired
    EmployeeServiceInt service;

    @GetMapping("/all")
    public ResponseEntity<List<?>> getAllEmployees() {

        List<Employee> employeesList = service.findAllEmployees();

        if (!employeesList.isEmpty()) {

            List<EmployeeDTO> employeeDTOList = employeesList.stream().map(employee -> {

                return employeeToEmployeeDTO.employeeToEmployeeDTO(employee);

            }).collect(Collectors.toList());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(employeeDTOList);

        } else {

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }
    }

    @GetMapping("/get_one/{employee_id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employee_id") Long employee_id) {

        Optional<Employee> optionalEmployee = service.findEmployeeById(employee_id);

        if (optionalEmployee.isPresent()) {

            Employee employee1 = optionalEmployee.get();
            EmployeeDTO employeeDTO = employeeToEmployeeDTO.employeeToEmployeeDTO(employee1);

            return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);

        } else {

            EmployeeDTO employeeDTO = new EmployeeDTO();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employeeDTO);

        }

    }

    @GetMapping("/get_by_name/{name}")
    public ResponseEntity<?> getEmployeeByName(@PathVariable("name") String name) {

        List<Employee> employeesList = service.findAllEmployees();
        Employee employee = service.findEmployeeByName(name).get();

        if (employeesList.contains(employee)) {

            EmployeeDTO employeeDTO = employeeToEmployeeDTO.employeeToEmployeeDTO(employee);

            return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);

        } else {

            EmployeeDTO employeeDTO = new EmployeeDTO();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employeeDTO);

        }

    }

    @PostMapping("/new_employee")
    public ResponseEntity<?> saveNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        if (!employeeDTO.getName().isEmpty()) {

            Employee employee = employeeDTOToEmployee.employeeDTOToEmployee(employeeDTO);

            service.saveNewEmployee(employee);

            return ResponseEntity.status(HttpStatus.OK).body(employee);
        }else{
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employeeDTO);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        Optional<Employee> optionalEmployee = service.findEmployeeById(id);

        if (optionalEmployee.isPresent()) {

            service.deleteEmployeeById(id);

            //to not directly return the Entity:
            Employee employee = optionalEmployee.get();
            EmployeeDTO employeeDTO = employeeToEmployeeDTO.employeeToEmployeeDTO(employee);

            return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<List<Employee>> deleteAllEmployees() {

        List<Employee> employeeList = service.findAllEmployees();

        if (!employeeList.isEmpty()) {

            service.deleteAllEmployees();
            return ResponseEntity.status(HttpStatus.OK).build();

        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping("/update/{employee_id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("employee_id") Long employee_id, @RequestBody EmployeeDTO employeeDTO) {

        Optional<Employee> optionalEmployee = service.findEmployeeById(employee_id);

        if (optionalEmployee.isPresent()) {

            Employee employee = employeeDTOToEmployee.employeeDTOToEmployee(employeeDTO);

            service.updateEmployee(employee_id, employee);

            return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

    }

    @GetMapping("/{department_id}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable("department_id") Long department_id) {

        List<Employee> employees = service.getEmployeesByDepartmentId(department_id);

        if (!employees.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON) //para ver la lista employees en postman
                    .body(employees);
        } else {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Collections.emptyList());
        }
    }

}
