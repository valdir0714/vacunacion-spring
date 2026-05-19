package co.sena.cimm.adso.vacunacion.controller;

import co.sena.cimm.adso.vacunacion.model.Usuario;
import co.sena.cimm.adso.vacunacion.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", Usuario.Rol.values());
        return "usuarios/formulario";
    }

    @PostMapping
    public String guardar(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttr) {
        usuarioService.guardar(usuario);
        redirectAttr.addFlashAttribute("mensaje", "Usuario guardado correctamente");
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id));
        model.addAttribute("roles", Usuario.Rol.values());
        return "usuarios/formulario";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Usuario usuario,
                             RedirectAttributes redirectAttr) {
        usuario.setId(id);
        usuarioService.guardar(usuario);
        redirectAttr.addFlashAttribute("mensaje", "Usuario actualizado correctamente");
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttr) {
        usuarioService.eliminar(id);
        redirectAttr.addFlashAttribute("mensaje", "Usuario eliminado correctamente");
        return "redirect:/usuarios";
    }
}
