package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Mark;
import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.services.ProfessorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorsController {

    @Autowired
    private ProfessorsService professorsService;

    @RequestMapping("/professor/list")
    public String getList(Model model) {
        model.addAttribute("professorList", professorsService.getProfessors());
        return "professor/list";
    }

    @RequestMapping(value="/professor/add", method = RequestMethod.POST)
    public String setProfessor(@ModelAttribute Professor professor) {
        professorsService.addProfessor(professor);
        return "redirect:/professor/list";
    }

    @RequestMapping(value = "/professor/add")
    public String getProfessor() {
        return "professor/add";
    }

    @RequestMapping("/professor/details/{dni}")
    public String getDetail(Model model, @PathVariable String dni) {
        model.addAttribute("professor", professorsService.getProfessor(dni));
        return "professor/details";
    }

    @RequestMapping(value = "/professor/edit/{dni}")
    public String getEdit(Model model, @PathVariable String dni) {
        model.addAttribute("professor", professorsService.getProfessor(dni));
        return "professor/edit";
    }

    @RequestMapping(value = "/professor/edit/{dni}", method = RequestMethod.POST)
    public String setEdit(@PathVariable String dni, @ModelAttribute Professor professor) {
        professorsService.deleteProfessor(dni);
        professorsService.addProfessor(new Professor(dni, professor.getName(),professor.getSurname(),professor.getCategory()));
        return "redirect:/professor/details/"+dni;
    }

    @RequestMapping(value = "/professor/delete/{dni}")
    public String deleteProfessor(@PathVariable String dni) {
        professorsService.deleteProfessor(dni);
        return "redirect:/professor/list";
    }
}
