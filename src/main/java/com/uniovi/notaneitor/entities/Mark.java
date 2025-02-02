package com.uniovi.notaneitor.entities;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RestController;

@Entity
public class Mark {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private double score;

    public Mark(){}

    public Mark(Long id, String description, double score) {
        this.id = id;
        this.description = description;
        this.score = score;
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

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
