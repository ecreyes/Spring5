package com.ecreyes.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/variable")
public class VariableController {

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable int id, Model model){
        model.addAttribute("id",id);
        return "variable/ver";
    }
}
