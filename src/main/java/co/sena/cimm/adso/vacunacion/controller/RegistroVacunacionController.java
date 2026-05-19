package co.sena.cimm.adso.vacunacion.controller;

import co.sena.cimm.adso.vacunacion.model.RegistroVacunacion;
import co.sena.cimm.adso.vacunacion.service.PacienteService;
import co.sena.cimm.adso.vacunacion.service.RegistroVacunacionService;
import co.sena.cimm.adso.vacunacion.service.UsuarioService;
import co.sena.cimm.adso.vacunacion.service.VacunaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registros")
public class RegistroVacunacionController {

    private final RegistroVacunacionService registroService;
    private final PacienteService pacienteService;
    private final VacunaService vacunaService;
    private final UsuarioService usuarioService;

    public RegistroVacunacionController(RegistroVacunacionService registroService,
                                        PacienteService pacienteService,
                                        VacunaService vacunaService,
                                        UsuarioService usuarioService) {
        this.registroService = registroService;
        this.pacienteService = pacienteService;
        this.vacunaService = vacunaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("registros", registroService.listarTodos());
        return "registros/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("registro", new RegistroVacunacion());
        model.addAttribute("pacientes", pacienteService.listarTodos());
        model.addAttribute("vacunas", vacunaService.listarTodas());
        model.addAttribute("personalMedico", usuarioService.listarTodos());
        return "registros/formulario";
    }

    @PostMapping
    public String guardar(@ModelAttribute RegistroVacunacion registro, RedirectAttributes redirectAttr) {
        registroService.guardar(registro);
        redirectAttr.addFlashAttribute("mensaje", "Registro de vacunaci\u00f3n guardado correctamente");
        return "redirect:/registros";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("registro", registroService.buscarPorId(id));
        model.addAttribute("pacientes", pacienteService.listarTodos());
        model.addAttribute("vacunas", vacunaService.listarTodas());
        model.addAttribute("personalMedico", usuarioService.listarTodos());
        return "registros/formulario";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute RegistroVacunacion registro,
                             RedirectAttributes redirectAttr) {
        registro.setId(id);
        registroService.guardar(registro);
        redirectAttr.addFlashAttribute("mensaje", "Registro actualizado correctamente");
        return "redirect:/registros";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttr) {
        registroService.eliminar(id);
        redirectAttr.addFlashAttribute("mensaje", "Registro eliminado correctamente");
        return "redirect:/registros";
    }
}
