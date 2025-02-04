package com.uniovi.notaneitor.services;

import com.uniovi.notaneitor.entities.Professor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfessorsService {

    private final List<Professor> professorsList = new LinkedList<>();

    @PostConstruct
    public void init() {
        professorsList.add(new Professor("12345678A","Alejandro","Rivada","Catedrático"));
        professorsList.add(new Professor("12345678B","Pablo","Perez","Titular"));
    }

    public List<Professor> getProfessors() {
        return professorsList;
    }

    public Professor getProfessor(String dni) {
        return professorsList.stream().filter(mark -> mark.getDni().equals(dni)).findFirst().get();
    }

    public void addProfessor(Professor professor) {
        professorsList.add(professor);
    }

    public void deleteProfessor(String dni) {
        professorsList.removeIf(professor -> professor.getDni().equals(dni));
    }
    
}
