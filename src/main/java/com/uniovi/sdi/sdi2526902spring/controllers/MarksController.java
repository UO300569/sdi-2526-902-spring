package com.uniovi.sdi.sdi2526902spring.controllers;

import com.uniovi.sdi.sdi2526902spring.services.MarksService;
import com.uniovi.sdi.sdi2526902spring.entities.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarksController {

    @Autowired //Inyectar el servicio
    private MarksService marksService;

    @RequestMapping("/mark/list")
    public String getList() {
        return marksService.getMarks().toString();
    }

    @PostMapping("/mark/add")
    public String setMark(@ModelAttribute Mark mark) {
        marksService.addMark(mark);
        return "Mark Added";
    }

    @GetMapping(value="/mark/details/{id}")
    public String getDetail(@PathVariable Long id) {
        return marksService.getMark(id).toString();
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        marksService.deleteMark(id);
        return "Mark Deleted";
    }
}
