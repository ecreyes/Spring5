package com.ecreyes.springapp.controller;

import com.ecreyes.springapp.model.dao.IClienteDao;
import com.ecreyes.springapp.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {
    @Autowired
    @Qualifier("ClienteDaoJPA")
    private IClienteDao clienteDao;

    // Muestra un listado de clientes
    @GetMapping("/clientes")
    public String listar(Model model){
        model.addAttribute("titulo","listar clientes");
        model.addAttribute("clientes",clienteDao.findAll());
        return "cliente/listar";
    }

    // Muestra un formulario para añadir un cliente
    @GetMapping("clientes/create")
    public String create(Model model){
        Cliente cliente = new Cliente();
        model.addAttribute("titulo","Formulario crear cliente");
        model.addAttribute("cliente",cliente);
        return "cliente/crear";
    }

    // método para almacenar el cliente creado en el form en la bd
    @PostMapping("clientes")
    public String store(Cliente cliente){
        clienteDao.save(cliente);
        return "redirect:clientes"; //redirige a la url /clientes
    }
}
