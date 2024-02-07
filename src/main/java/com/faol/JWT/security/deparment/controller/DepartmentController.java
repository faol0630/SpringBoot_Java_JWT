package com.faol.JWT.security.deparment.controller;

import com.faol.JWT.security.deparment.dto.DepartmentDTO;
import com.faol.JWT.security.deparment.service.DepartmentServiceInt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentServiceInt service;

    @GetMapping("/all")
    ResponseEntity<?> getAllDepartments() {

        Optional<List<DepartmentDTO>> departmentList = service.getAllDeparments();
        Map<String, Object> response = new LinkedHashMap<>();

        if (departmentList.isPresent()) {
            response.put("message", "Department's list found");
            response.put("departmentList", departmentList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("message", "Department's list empty or not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/id/{department_id}")
    ResponseEntity<?> getDepartmentById(@PathVariable Long department_id) {

        Optional<DepartmentDTO> departmentDTO = service.getDepartmentById(department_id);

        Map<String, Object> response = new LinkedHashMap<>();

        if (departmentDTO.isPresent()) {
            response.put("message", "Department found");
            response.put("departmentDTO", departmentDTO.get());
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            response.put("message", "Department not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/name/{department_name}")
    ResponseEntity<?> getDepartmentByName(@PathVariable String department_name) {

        Optional<DepartmentDTO> departmentDTO = service.getDepartmentByName(department_name);

        Map<String, Object> response = new LinkedHashMap<>();

        if (departmentDTO.isPresent()) {

            response.put("message", "Department found");
            response.put("departmentDTO", departmentDTO.get());
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            response.put("message", "Department not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @PostMapping("/new")
    ResponseEntity<?> newDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {

        Map<String, Object> response = new LinkedHashMap<>();

        Optional<DepartmentDTO> result = Optional.of(departmentDTO);

        if (result.isPresent()) {

            DepartmentDTO departmentDTO1 = service.createDepartment(departmentDTO);
            response.put("message", "Department added successfully");
            response.put("departmentDTO1", departmentDTO1);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }else{
            response.put("message", "error adding department");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @PutMapping("/update/{department_id}")
    ResponseEntity<?> updateDepartment(@PathVariable Long department_id, @RequestBody @Valid DepartmentDTO departmentDTO) {

        Optional<DepartmentDTO> departmentDTO1 = service.getDepartmentById(department_id);

        Map<String, Object> response = new LinkedHashMap<>();

        if (departmentDTO1.isPresent()) {

            DepartmentDTO departmentDTO2 = service.updateDepartment(department_id, departmentDTO).get();
            response.put("message", "Department updated");
            response.put("departmentDTO2", departmentDTO2);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            response.put("message", "Department not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @DeleteMapping("/delete/{department_id}")
    ResponseEntity<?> deleteDepartment(@PathVariable Long department_id) {

        Optional<DepartmentDTO> departmentDTO = service.getDepartmentById(department_id);
        Map<String, Object> response = new HashMap<>();

        if (departmentDTO.isPresent()) {

            response.put("message", "Department with id " + department_id + " deleted");
            service.deleteDepartment(department_id);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            response.put("message", "Department with id " + department_id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @DeleteMapping("/delete_all")
    ResponseEntity<?> deleteAllDepartments() {

        Optional<List<DepartmentDTO>> departmentList = service.getAllDeparments();
        Map<String, Object> response = new HashMap<>();


        if (departmentList.isPresent()) {
            response.put("message", "Department's list deleted");
            service.deleteAllDepartments();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("message", "Department's list empty or not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }


}
