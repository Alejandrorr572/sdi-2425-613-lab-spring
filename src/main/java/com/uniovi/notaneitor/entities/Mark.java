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

    @ManyToOne
    @JoinColumn(name = "user id")
    private User user;

    public Mark(){}

    public Mark(Long id, String description, double score) {
        this.id = id;
        this.description = description;
        this.score = score;
    }

    public Mark(String description, double score, User user) {
        this.description = description;
        this.score = score;
        this.user = user;
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

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
