package com.ecreyes.springapp.controller;

import com.ecreyes.springapp.model.dao.IClienteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {
    @Autowired
    @Qualifier("ClienteDaoJPA")
    private IClienteDao clienteDao;

    @GetMapping("/clientes")
    public String listar(Model model){
        model.addAttribute("titulo","listar clientes");
        model.addAttribute("clientes",clienteDao.findAll());
        return "cliente/listar";
    }
}
