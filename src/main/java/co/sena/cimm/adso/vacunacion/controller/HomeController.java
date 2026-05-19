package co.sena.cimm.adso.vacunacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador de inicio - redirige la raiz a la lista de pacientes
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio() {
        return "redirect:/pacientes";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
