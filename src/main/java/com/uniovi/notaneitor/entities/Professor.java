package com.uniovi.notaneitor.entities;

public class Professor {
    private String dni;
    private String name;
    private String surname;
    private String category;

    public Professor() {
    }

    public Professor(String dni, String name, String surname, String category) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.category = category;
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

