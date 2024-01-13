package com.faol.JWT.security.deparment.controller;

import com.faol.JWT.security.deparment.dto.DepartmentDTO;
import com.faol.JWT.security.deparment.service.DepartmentServiceInt;
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
        Map<String, Object> response = new HashMap<>();
        response.put("departmentList", departmentList);

        if (departmentList.isPresent()) {
            response.put("message", "Department's list found");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("message", "Department's list empty or not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/id/{department_id}")
    ResponseEntity<?> getDepartmentById(@PathVariable Long department_id) {

        Optional<DepartmentDTO> departmentDTO = service.getDepartmentById(department_id);

        Map<String, Object> response = new HashMap<>();
        response.put("departmentDTO" , departmentDTO);

        if (departmentDTO.isEmpty()) {
            response.put("message", "Department not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            response.put("message", "Department found");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @GetMapping("/name/{department_name}")
    ResponseEntity<?> getDepartmentByName(@PathVariable String department_name) {

        Optional<DepartmentDTO> departmentDTO = service.getDepartmentByName(department_name);

        Map<String, Object> response = new HashMap<>();
        response.put("departmentDTO" , departmentDTO);

        if (departmentDTO.isEmpty()) {
            response.put("message", "Department not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            response.put("message", "Department found");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }


    @PostMapping("/new")
    ResponseEntity<?> newDepartment(@RequestBody DepartmentDTO departmentDTO) {

        Map<String, Object> response = new HashMap<>();

        if (departmentDTO.getDepartment_name() != null) {

            DepartmentDTO departmentDTO1 = service.createDepartment(departmentDTO);
            response.put("departmentDTO1", departmentDTO1);
            response.put("message", "Department added successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }else{

            response.put("message", "Error.Department no added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    @PutMapping("/update/{department_id}")
    ResponseEntity<?> updateDepartment(@PathVariable Long department_id, @RequestBody DepartmentDTO departmentDTO){

        Optional<DepartmentDTO> departmentDTO1 =  service.getDepartmentById(department_id);

        Map<String, Object> response = new HashMap<>();
        response.put("departmentDTO1", departmentDTO1);

        if (departmentDTO1.isEmpty()){
            response.put("message", "Department not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            DepartmentDTO departmentDTO2 = service.updateDepartment(department_id, departmentDTO).get();
            response.put("message", "Department updated");
            return ResponseEntity.status(HttpStatus.OK).body(departmentDTO2);
        }

    }

    @DeleteMapping("/delete/{department_id}")
    ResponseEntity<?> deleteDepartment(@PathVariable Long department_id){

        Optional<DepartmentDTO> departmentDTO =  service.getDepartmentById(department_id);
        Map<String, Object> response = new HashMap<>();

        if (departmentDTO.isEmpty()){
            response.put("message", "Department with id " + department_id  + " not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            response.put("message", "Department with id " + department_id  + " deleted");
            service.deleteDepartment(department_id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @DeleteMapping("/delete_all")
    ResponseEntity<?> deleteAllDepartments(){

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
