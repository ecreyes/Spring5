package com.ecreyes.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params") //Se crea una ruta de primer nivel.
public class ParamController {

    /**  Esta ruta se encarga de recibir parámetros del tipo GET, es decir recibe un parametro
     *   http://localhost:8080/params/string?texto=holamundo y luego se muestra en la vista.
     *   el value es el nombre que se usará en el get, por defecto es obligatorio el parametro asi que se desactiva,
     *   y en caso de que no se ingrese hay un valor por defecto
     * **/
    @GetMapping("/string")
    public String param(@RequestParam(value = "texto", required = false, defaultValue = "algun valor") String texto,
                        Model model) {
        model.addAttribute("texto", texto);
        return "params/ver";
    }
}
