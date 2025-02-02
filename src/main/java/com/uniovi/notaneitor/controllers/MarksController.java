package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Mark;
import com.uniovi.notaneitor.services.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MarksController {

    @Autowired
    private MarksService marksService;

    @RequestMapping("/mark/list")
    public String getList(Model model) {
        model.addAttribute("markList", marksService.getMarks());
        return "mark/list";
    }

    @RequestMapping(value="/mark/add", method = RequestMethod.POST)
    public String setMark(@ModelAttribute Mark mark) {
        marksService.addMark(mark);
        return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/add")
    public String getMark() {
        return "mark/add";
    }

//    @RequestMapping("/mark/details")
//    public String getDetail(@RequestParam Long id) {
//        return "Getting Details =>" + id;
//    } // FORMA 1

    @RequestMapping("/mark/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        return "mark/details";
    }  // FORMA 2

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id){
        marksService.deleteMark(id);
        return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        return "mark/edit";
    }

    @RequestMapping(value="/mark/edit/{id}", method=RequestMethod.POST)
    public String setEdit(@ModelAttribute Mark mark, @PathVariable Long id){
        mark.setId(id);
        marksService.addMark(mark);
        return "redirect:/mark/details/"+id;
    }
}

