package com.uniovi.sdi.sdi2526902spring.controllers;

import com.uniovi.sdi.sdi2526902spring.entities.Department;
import com.uniovi.sdi.sdi2526902spring.entities.Mark;
import com.uniovi.sdi.sdi2526902spring.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getList() {
        return departmentService.getDepartments();
    }

    @GetMapping("/details")
    public Department getDetails(@RequestParam String code) {
        return departmentService.getDepartment(code);
    }

    @PostMapping
    public String addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        return "Departamento Añadido";
    }

    @PutMapping("/edit")
    public String editDepartment(@RequestBody Department department) {
        departmentService.editDepartment(department);
        return "Departamento actualizado";
    }

    @DeleteMapping("/remove")
    public String removeDepartment(@RequestParam String code) {
        departmentService.deleteDepartment(code);
        return "Departamento eliminado";
    }


}
