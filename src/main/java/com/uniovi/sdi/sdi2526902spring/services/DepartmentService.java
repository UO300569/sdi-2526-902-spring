package com.uniovi.sdi.sdi2526902spring.services;

import com.uniovi.sdi.sdi2526902spring.entities.Department;
import com.uniovi.sdi.sdi2526902spring.repositories.DepartmentRepository;
import com.uniovi.sdi.sdi2526902spring.repositories.MarksRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAll().forEach(departments::add);
        return departments;
    }

    public Page<Department> getDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void editDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void  deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public Department getDepartmentByCode(String code) { return departmentRepository.findByCode(code); }


}
