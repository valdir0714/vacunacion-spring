package co.sena.cimm.adso.vacunacion.controller;

import co.sena.cimm.adso.vacunacion.model.Paciente;
import co.sena.cimm.adso.vacunacion.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Controller - equivale a @WebServlet del proyecto de Servlets
 * @RequestMapping("/pacientes") - prefijo base de todas las rutas
 */
@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // GET /pacientes -> listar todos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "pacientes/lista";
    }

    // GET /pacientes/nuevo -> mostrar formulario vacio
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "pacientes/formulario";
    }

    // POST /pacientes -> guardar nuevo paciente
    @PostMapping
    public String guardar(@ModelAttribute Paciente paciente,
                          RedirectAttributes redirectAttr) {
        pacienteService.guardar(paciente);
        redirectAttr.addFlashAttribute("mensaje", "Paciente guardado correctamente");
        return "redirect:/pacientes";
    }

    // GET /pacientes/{id}/editar -> formulario con datos del paciente
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", pacienteService.buscarPorId(id));
        return "pacientes/formulario";
    }

    // POST /pacientes/{id} -> actualizar paciente
    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @ModelAttribute Paciente paciente,
                             RedirectAttributes redirectAttr) {
        paciente.setId(id);
        pacienteService.guardar(paciente);
        redirectAttr.addFlashAttribute("mensaje", "Paciente actualizado");
        return "redirect:/pacientes";
    }

    // GET /pacientes/{id}/eliminar -> eliminar paciente
    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id,
                           RedirectAttributes redirectAttr) {
        pacienteService.eliminar(id);
        redirectAttr.addFlashAttribute("mensaje", "Paciente eliminado");
        return "redirect:/pacientes";
    }
}
