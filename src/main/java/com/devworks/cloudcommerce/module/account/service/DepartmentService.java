package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.module.account.dto.DepartmentDTO;
import com.devworks.cloudcommerce.module.account.mapper.DepartmentMapper;
import com.devworks.cloudcommerce.module.account.model.Department;
import com.devworks.cloudcommerce.module.account.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Set<Department> getValidDepartments(Set<Department> departments) {
        var validActions = new HashSet<Department>();
        for (Department department : departments) {
            var existsAction = departmentRepository.findById(department.getId())
                .orElseThrow(() -> new NotFoundException("Department not found " + department.getId()));
            validActions.add(existsAction);
        }
        return validActions;
    }

    public DepartmentDTO create(DepartmentDTO input) {
        var optDepartment = departmentRepository.findById(input.getId());

        if(optDepartment.isPresent())
            throw new BadRequestException("Department already exists");

        var department = departmentRepository.save(DepartmentMapper.toEntity(input));

        return DepartmentMapper.toDto(department);
    }
}

