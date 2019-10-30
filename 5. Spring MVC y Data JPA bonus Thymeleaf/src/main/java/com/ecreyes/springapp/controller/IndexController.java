package com.ecreyes.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IndexController {
    @GetMapping({"/index",""})
    public String index(Model model){
        model.addAttribute("titulo","Inicio");
        return "index";
    }

    @ModelAttribute("active_inicio")
    public boolean navActive(){
        return true;
    }
}
