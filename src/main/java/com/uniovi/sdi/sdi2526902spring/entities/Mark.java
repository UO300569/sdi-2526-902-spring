package com.uniovi.sdi.sdi2526902spring.entities;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

@Entity
public class Mark {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private double score;

    public Mark(Long id, String description, double score) {
        this.id = id;
        this.description = description;
        this.score = score;
    }

    public Mark() {

    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", score=" + score +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
