package com.uniovi.sdi.sdi2526902spring.services;

import com.uniovi.sdi.sdi2526902spring.entities.Department;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    public List<Department> departments = new ArrayList<>();

    @PostConstruct
    public void init() {
        departments.add(new Department(
                "INF-01",
                "Departamento de Informática",
                "EII",
                "985102796",
                120));
        departments.add(new Department(
                "MAT-01",
                "Departamento de Matemáticas",
                "EII",
                "985102797",
                30));
    }


    public List<Department> getDepartments() {
        return departments;
    }

    public Department getDepartment(String code) {
        for (Department dept : departments) {
            if (dept.getCode().equals(code)) {
                return dept;
            }
        }
        return null;
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void editDepartment(Department department) {
        for (int i = 0; i < departments.size(); i++) {
            Department dept = departments.get(i);
            if (dept.getCode().equals(department.getCode())) {
                departments.set(i, department);
                return;
            }
        }
    }

    public void  deleteDepartment(String code) {
        departments.removeIf(dept -> dept.getCode().equals(code));
    }

}
