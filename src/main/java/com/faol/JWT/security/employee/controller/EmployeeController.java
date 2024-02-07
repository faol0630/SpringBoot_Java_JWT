package com.faol.JWT.security.employee.controller;

import com.faol.JWT.security.employee.controller.dto.EmployeeDTO;
import com.faol.JWT.security.employee.entity.Employee;
import com.faol.JWT.security.employee.service.EmployeeServiceInt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceInt service;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployees() {

        Optional<List<EmployeeDTO>> employeesList = service.findAllEmployees();
        HashMap<String, Object> response = new LinkedHashMap<>();

        if (employeesList.isPresent()) {

            response.put("message", "List found");
            response.put("list", employeesList);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        } else {
            response.put("message", "error finding list or empty list");
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(response);
        }
    }

    @GetMapping("/get_one/{employee_id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employee_id") Long employee_id) {

        Optional<EmployeeDTO> employeeDTO = service.findEmployeeById(employee_id);

        Map<String, Object> response = new LinkedHashMap<>();

        if (employeeDTO.isPresent()) {

            response.put("message", "employee found");
            response.put("employeeDTO", employeeDTO.get());

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {

            response.put("message", "employee with id " + employee_id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }

    }

    @GetMapping("/get_by_name/{name}")
    public ResponseEntity<?> getEmployeeByName(@PathVariable String name) {

        Optional<EmployeeDTO> employeeDTO = service.findEmployeeByName(name);

        Map<String, Object> response = new LinkedHashMap<>();

        if (employeeDTO.isPresent()) {

            response.put("message", "employee found");
            response.put("employeeDTO", employeeDTO.get());
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {

            response.put("message", "employee not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }

    }

    @PostMapping("/new_employee")
    public ResponseEntity<?> saveNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {

        Map<String, Object> response = new LinkedHashMap<>();

        Optional<EmployeeDTO> result = Optional.of(employeeDTO);

        if (result.isPresent()) {

            EmployeeDTO employeeDTO1 = service.saveNewEmployee(employeeDTO);
            response.put("message", "employee created");
            response.put("employeeDTO1", employeeDTO1);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }else {
            response.put("message", "error saving new employee");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {

        Optional<EmployeeDTO> employeeDTO = service.findEmployeeById(id);
        Map<String, Object> response = new HashMap<>();

        if (employeeDTO.isPresent()) {

            service.deleteEmployeeById(id);
            response.put("message", "employee with id " + id + " deleted");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            response.put("message", "error deleting employee.employee with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteAllEmployees() {

        Optional<List<EmployeeDTO>> employeeList = service.findAllEmployees();
        HashMap<String, Object> response = new HashMap<>();

        if (employeeList.isPresent()) {

            service.deleteAllEmployees();
            response.put("message", "all employees deleted");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            response.put("message", "empty list.nothing to delete");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
    }

    @PutMapping("/update/{employee_id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long employee_id, @RequestBody @Valid EmployeeDTO employeeDTO) {

        Optional<EmployeeDTO> employeeDTO1 = service.findEmployeeById(employee_id);
        Map<String, Object> response = new LinkedHashMap<>();

        if (employeeDTO1.isPresent()) {

            Optional<EmployeeDTO> employeeDTO2 = service.updateEmployee(employee_id, employeeDTO);
            response.put("message", "employee updated");
            response.put("employeeDTO", employeeDTO2.get());
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            response.put("message", "employee not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }

    }

    @GetMapping("/{department_id}/employees")
    public ResponseEntity<?> getEmployeesByDepartmentId(@PathVariable("department_id") Long department_id) {

        List<Employee> employees = service.getEmployeesByDepartmentId(department_id);

        Map<String, Object> response = new LinkedHashMap<>();

        if (!employees.isEmpty()) {

            response.put("message", "employees found");
            response.put("Employee's list", employees);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON) //see the employees list in postman
                    .body(response);
        } else {

            response.put("message", "employees with department id " + department_id + " not found");

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }

}
//example of postman body update employee:

//{
//    "employee_id": 1702,
//    "name": "Samuel",
//    "lastname": "Del Real",
//    "phone_number": "882738129",
//    "email": "samuel_del_real@live.net",
//    "department" : {
//      "department_id": 10,
//      "department_name": "Calidad"
//
//    },
//    "address" : {
//      "address_id": 4,
//      "number": "4",
//      "postcode": "77322",
//      "street": "Vallecas"
//    }
//
//  }
