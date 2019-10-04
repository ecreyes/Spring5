package com.ecreyes.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** Creación de la clase controller para index, cada método de esta clase
 *  se encarga de procesar una petición distinta **/
@Controller
public class IndexController {

    /** - El GetMapping es la ruta que va a procesar el método del tipo GET.
     * - Como este método retorna index debe retornar una plantilla
     *   que se llame index, ubicada en resources/templates.
     * - El Model sirve para enviar parámetros la vista. **/
    @GetMapping(value="/index")
    public String index(Model model){
        model.addAttribute("titulo","Inicio");
        model.addAttribute("welcome","Bienvenido al sitio");
        return "index";
    }
}
