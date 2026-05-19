package co.sena.cimm.adso.vacunacion.controller;

import co.sena.cimm.adso.vacunacion.model.Vacuna;
import co.sena.cimm.adso.vacunacion.service.VacunaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vacunas")
public class VacunaController {

    private final VacunaService vacunaService;

    public VacunaController(VacunaService vacunaService) {
        this.vacunaService = vacunaService;
    }

    // GET /vacunas -> listar todas
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("vacunas", vacunaService.listarTodas());
        return "vacunas/lista";
    }

    // GET /vacunas/nueva -> mostrar formulario vacio
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("vacuna", new Vacuna());
        return "vacunas/formulario";
    }

    // POST /vacunas -> guardar nueva vacuna
    @PostMapping
    public String guardar(@ModelAttribute Vacuna vacuna,
                          RedirectAttributes redirectAttr) {
        vacunaService.guardar(vacuna);
        redirectAttr.addFlashAttribute("mensaje", "Vacuna guardada correctamente");
        return "redirect:/vacunas";
    }

    // GET /vacunas/{id}/editar -> formulario con datos de la vacuna
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("vacuna", vacunaService.buscarPorId(id));
        return "vacunas/formulario";
    }

    // POST /vacunas/{id} -> actualizar vacuna
    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @ModelAttribute Vacuna vacuna,
                             RedirectAttributes redirectAttr) {
        vacuna.setId(id);
        vacunaService.guardar(vacuna);
        redirectAttr.addFlashAttribute("mensaje", "Vacuna actualizada");
        return "redirect:/vacunas";
    }

    // GET /vacunas/{id}/eliminar -> eliminar vacuna
    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id,
                           RedirectAttributes redirectAttr) {
        vacunaService.eliminar(id);
        redirectAttr.addFlashAttribute("mensaje", "Vacuna eliminada");
        return "redirect:/vacunas";
    }
}
