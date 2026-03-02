package com.uniovi.sdi.sdi2526902spring.controllers;

import com.uniovi.sdi.sdi2526902spring.entities.Department;
import com.uniovi.sdi.sdi2526902spring.entities.Mark;
import com.uniovi.sdi.sdi2526902spring.services.DepartmentService;
import com.uniovi.sdi.sdi2526902spring.services.MarksService;
import com.uniovi.sdi.sdi2526902spring.services.UsersService;
import com.uniovi.sdi.sdi2526902spring.validators.DepartmentValidator;
import com.uniovi.sdi.sdi2526902spring.validators.MarkValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private final DepartmentValidator departmentValidator;

    public DepartmentController(DepartmentValidator departmentValidator) {
        this.departmentValidator = departmentValidator;
    }

    @GetMapping(value = "/list")
    public String getList(Model model, Pageable pageable) {
        Page<Department> departments = departmentService.getDepartments(pageable);
        model.addAttribute("departmentList", departments.getContent());
        model.addAttribute("page", departments);

        return "department/list";
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getDepartment(id));
        return "department/details";
    }

    @PostMapping("/add")
    public String addDepartment(@ModelAttribute Department department, BindingResult result, Model model) {
        departmentValidator.validate(department, result);

        if (result.hasErrors()) {
            return "department/add";
        }

        departmentService.addDepartment(department);
        return "redirect:/department/list";
    }

    @GetMapping(value = "/add")
    public String getDepartment(Model model) {
        model.addAttribute("department", new Department());
        return "/department/add";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("department", departmentService.getDepartment(id));
        return "department/edit";
    }

    @PostMapping("/edit")
    public String setEdit(@ModelAttribute Department dept) {
        departmentService.addDepartment(dept);
        return "redirect:/department/details/" + dept.getId();
    }

    @GetMapping("/remove/{id}")
    public String removeDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/department/list";
    }


}
