package com.uniovi.notaneitor.entities;

import javax.persistence.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Entity
public class Mark {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private double score;
    private Boolean resend = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
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

    public Boolean getResend() {return resend;}

    public void setResend(Boolean resend) {this.resend = resend;}


    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return Objects.equals(id, mark.id);
    }
}
