package com.ecreyes.springapp.controller;

import com.ecreyes.springapp.model.entity.Cliente;
import com.ecreyes.springapp.model.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
    @Autowired
    @Qualifier("ClienteService")
    private IClienteService clienteService;

    // Muestra un listado de clientes
    @GetMapping("/clientes")
    public String index(@RequestParam(name = "page",defaultValue = "0") int page,Model model){
        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("titulo","listar clientes");
        model.addAttribute("clientes",clientes);
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
    public String store(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status,
                        RedirectAttributes flash){
        if(result.hasErrors()){
            if(cliente.getId()==null){
                model.addAttribute("titulo","Formulario crear cliente");
                model.addAttribute("btnForm","Añadir cliente");
            }else{
                model.addAttribute("titulo","Formulario actualizar cliente");
                model.addAttribute("btnForm","Actualizar cliente");
            }
            model.addAttribute("cliente",cliente);
            return "cliente/crear";
        }
        String mensajeFlash = (cliente.getId()==null)?
                "El cliente se ha creado correctamente!": "El cliente se ha actualizado correctamente!";
        clienteService.save(cliente);
        status.setComplete();
        flash.addFlashAttribute("success",mensajeFlash);
        return "redirect:/clientes"; //redirige a la url /clientes
    }

    // Muestra un formulario para actualizar un cliente
    @GetMapping("clientes/{id}/editar")
    public String edit(@PathVariable Long id, Model model){
        Cliente cliente;
        if(id>0){
            cliente = clienteService.findOne(id);
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

    //Eliminar un cliente
    @GetMapping("clientes/{id}")
    public String destroy(@PathVariable Long id,RedirectAttributes flash){
        Cliente cliente;
        if(id>0){
            cliente = clienteService.findOne(id);
            if(cliente==null){
                return "redirect:/clientes";
            }
        }else{
            return "redirect:/clientes";
        }
        clienteService.delete(id);
        flash.addFlashAttribute("success","El cliente se eliminó correctamente");
        return "redirect:/clientes";
    }

    @ModelAttribute("active_cliente")
    public boolean navActive(){
        return true;
    }


}
