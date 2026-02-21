package com.uniovi.sdi.sdi2526902spring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private String name;
    private String faculty;
    private String telephone;
    private int professors;

    public Department(Long id, String code, String name, String faculty, String telephone, int professors) {
        this.id = id;
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
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                ", telephone='" + telephone + '\'' +
                ", professors=" + professors +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
