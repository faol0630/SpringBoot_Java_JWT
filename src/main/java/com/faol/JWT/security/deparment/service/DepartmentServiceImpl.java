package com.faol.JWT.security.deparment.service;

import com.faol.JWT.security.deparment.dto.DepartmentDTO;
import com.faol.JWT.security.deparment.dto.DepartmentToDepartmentDTO;
import com.faol.JWT.security.deparment.entity.Department;
import com.faol.JWT.security.deparment.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentServiceInt{

    @Autowired
    private DepartmentRepository repo;

    @Override
    public Optional<List<DepartmentDTO>> getAllDeparments() {

        List<Department> departmentList = repo.findAll();

        if (departmentList.isEmpty()){

            return Optional.empty();

        }else{

            List<DepartmentDTO> departmentDTOList = departmentList.stream()
                    .map(DepartmentToDepartmentDTO::departmentToDepartmentDTO)
                    .collect(Collectors.toList());

            return Optional.of(departmentDTOList);
        }

    }

    @Override
    public Optional<DepartmentDTO> getDepartmentById(Long department_id) {

        Optional<Department> department = repo.findById(department_id);
        if (department.isEmpty()){
            return Optional.empty();
        }else{
            DepartmentDTO departmentDTO = DepartmentToDepartmentDTO.departmentToDepartmentDTO(department.get());
            return Optional.of(departmentDTO);
        }


    }

    @Override
    public Optional<DepartmentDTO> getDepartmentByName(String department_name) {

        Optional<Department> department = repo.findDepartmentByName(department_name);
        if (department.isEmpty()){
            return Optional.empty();
        }else{
            DepartmentDTO departmentDTO = DepartmentToDepartmentDTO.departmentToDepartmentDTO(department.get());
            return Optional.of(departmentDTO);
        }

    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {

        Department department = new Department();

        if (departmentDTO.getDepartment_name() != null){
            department.setDepartment_name(departmentDTO.getDepartment_name());
        }

        repo.save(department);
        return departmentDTO;
    }

    @Override
    public Optional<DepartmentDTO> updateDepartment(Long department_id, DepartmentDTO departmentDTO) {

        Department department = repo.findById(department_id).get();

        if (departmentDTO.getDepartment_name() != null){
            department.setDepartment_name(departmentDTO.getDepartment_name());
        }

        repo.save(department);
        return Optional.of(departmentDTO);
    }

    @Override
    public Optional<DepartmentDTO> deleteDepartment(Long department_id) {

        Optional<Department> department = repo.findById(department_id);
        if (department.isEmpty()){
            return Optional.empty();
        }else{

            DepartmentDTO departmentDTO = DepartmentToDepartmentDTO.departmentToDepartmentDTO(department.get());
            repo.deleteById(department_id);
            return Optional.of(departmentDTO);

        }

    }

    @Override
    public String deleteAllDepartments() {

        List<Department> departmentList = repo.findAll();
        if (departmentList.isEmpty()){
            return "Empty list.Nothing to delete";
        }
        repo.deleteAll();
        return "list successfully deleted";
    }




}
