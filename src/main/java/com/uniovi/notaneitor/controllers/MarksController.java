package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Mark;
import com.uniovi.notaneitor.entities.User;
import com.uniovi.notaneitor.services.MarksService;
import com.uniovi.notaneitor.services.UsersService;
import com.uniovi.notaneitor.validators.MarkValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MarksController {

    private final MarksService marksService;
    private final UsersService usersService;
    private final MarkValidator addMarkValidator;
    private final HttpSession httpSession;

    public MarksController(MarksService marksService, UsersService usersService, MarkValidator addMarkValidator, HttpSession httpSession) {
        this.marksService = marksService;
        this.usersService = usersService;
        this.addMarkValidator = addMarkValidator;
        this.httpSession = httpSession;
    }


    @RequestMapping("/mark/list")
    public String getList(Model model, Principal principal){
        String dni = principal.getName(); // DNI es el name de la autenticación
        User user = usersService.getUserByDni(dni);
        model.addAttribute("marksList", marksService.getMarksForUser(user) );
        return "mark/list";
    }


    @RequestMapping(value="/mark/add", method = RequestMethod.POST)
    public String setMark(@Validated Mark mark, BindingResult result, Model model) {
        addMarkValidator.validate(mark, result);
        if (result.hasErrors()) {
            model.addAttribute("usersList",  usersService.getUsers());
            return "mark/add";
        }
        marksService.addMark(mark);
        return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/add")
    public String getMark(Model model){
        model.addAttribute("mark", new Mark());
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/add";
    }

//    @RequestMapping("/mark/details")
//    public String getDetail(@RequestParam Long id) {
//        return "Getting Details =>" + id;
//    } // FORMA 1

    @RequestMapping("/mark/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/details";
    }  // FORMA 2

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id){
        marksService.deleteMark(id);
        return "redirect:/mark/list";
    }

    @RequestMapping("/mark/list/update")
    public String updateList(Model model, Principal principal) {
        String dni = principal.getName(); // DNI es el name de la autenticación
        User user = usersService.getUserByDni(dni);
        model.addAttribute("marksList", marksService.getMarksForUser(user));
        return "mark/list :: tableMarks";
    }

    @RequestMapping(value = "/mark/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        return "mark/edit";
    }

    @RequestMapping(value="/mark/edit/{id}", method=RequestMethod.POST)
    public String setEdit(@Validated Mark mark, BindingResult result, @PathVariable Long id, Model model){
        addMarkValidator.validate(mark, result);
        if (result.hasErrors()) {
            model.addAttribute("mark", marksService.getMark(id));
            return "mark/edit";
        }

        Mark originalMark = marksService.getMark(id);
        // modificar solo score y description
        originalMark.setScore(mark.getScore());
        originalMark.setDescription(mark.getDescription());
        marksService.addMark(originalMark);
        return "redirect:/mark/details/"+id;
    }

    @RequestMapping(value = "/mark/{id}/resend", method = RequestMethod.GET)
    public String setResendTrue(@PathVariable Long id) {
        marksService.setMarkResend(true, id);
        return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/{id}/noresend", method = RequestMethod.GET)
    public String setResendFalse(@PathVariable Long id) {
        marksService.setMarkResend(false, id);
        return "redirect:/mark/list";
    }
}

