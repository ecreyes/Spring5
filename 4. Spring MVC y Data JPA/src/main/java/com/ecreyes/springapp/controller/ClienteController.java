package com.ecreyes.springapp.controller;

import com.ecreyes.springapp.model.dao.IClienteDao;
import com.ecreyes.springapp.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ClienteController {
    @Autowired
    @Qualifier("ClienteDaoJPA")
    private IClienteDao clienteDao;

    // Muestra un listado de clientes
    @GetMapping("/clientes")
    public String index(Model model){
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
        model.addAttribute("btnForm","Añadir cliente");
        return "cliente/crear";
    }

    // método para almacenar el cliente creado en el form en la bd
    @PostMapping("clientes")
    public String store(@Valid Cliente cliente, BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("titulo","Formulario crear cliente");
            model.addAttribute("cliente",cliente);
            return "cliente/crear";
        }
        clienteDao.save(cliente);
        return "redirect:clientes"; //redirige a la url /clientes
    }

    // Muestra un formulario para actualizar un cliente
    @GetMapping("clientes/editar/{id}")
    public String edit(@PathVariable Long id, Model model){
        Cliente cliente;
        if(id>0){
            cliente = clienteDao.findOne(id);
            if(cliente==null){
                return "redirect:/clientes";
            }
        }else{
            return "redirect:/clientes";
        }
        model.addAttribute("titulo","Formulario actualizar cliente");
        model.addAttribute("cliente",cliente);
        model.addAttribute("btnForm","Actualizar cliente");
        return "cliente/crear";
    }


}
