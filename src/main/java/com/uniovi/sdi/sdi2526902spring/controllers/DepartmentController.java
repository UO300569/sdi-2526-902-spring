package com.uniovi.sdi.sdi2526902spring.controllers;

import com.uniovi.sdi.sdi2526902spring.entities.Department;
import com.uniovi.sdi.sdi2526902spring.entities.Mark;
import com.uniovi.sdi.sdi2526902spring.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/list")
    public String getList(Model model) {
        model.addAttribute("departmentList", departmentService.getDepartments());
        return "department/list";
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getDepartment(id));
        return "department/details";
    }

    @PostMapping("/add")
    public String addDepartment(@ModelAttribute Department department) {
        departmentService.addDepartment(department);
        return "redirect:/department/list";
    }

    @GetMapping(value = "/add")
    public String getDepartment() {
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
