package com.uniovi.sdi.sdi2526902spring.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Department {
    private String code;
    private String name;
    private String faculty;
    private String telephone;
    private int professors;

    public Department(String code, String name, String faculty, String telephone, int professors) {
        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.telephone = telephone;
        this.professors = professors;
    }

    public Department() {

    }

    @Override
    public String toString() {
        return "Department{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                ", telephone=" + telephone +
                ", professors=" + professors +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getProfessors() {
        return professors;
    }

    public void setProfessors(int professors) {
        this.professors = professors;
    }
}
