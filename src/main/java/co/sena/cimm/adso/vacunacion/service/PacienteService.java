package co.sena.cimm.adso.vacunacion.service;

import co.sena.cimm.adso.vacunacion.model.Paciente;
import co.sena.cimm.adso.vacunacion.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Service indica a Spring que esta clase contiene logica de negocio.
 * Spring la registra como un bean y la inyecta donde se necesite.
 */
@Service
@Transactional
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /** Lista todos los pacientes ordenados por apellido */
    @Transactional(readOnly = true)
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .sorted((a, b) -> a.getApellidos().compareTo(b.getApellidos()))
                .toList();
    }

    /** Busca un paciente por ID. Lanza excepcion si no existe */
    @Transactional(readOnly = true)
    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Paciente con ID " + id + " no encontrado"));
    }

    /** Busca por numero de documento */
    @Transactional(readOnly = true)
    public Optional<Paciente> buscarPorDocumento(String documento) {
        return pacienteRepository.findByDocumento(documento);
    }

    /** Guarda o actualiza un paciente */
    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    /** Elimina un paciente por ID */
    public void eliminar(Long id) {
        if (!pacienteRepository.existsById(id))
            throw new RuntimeException("Paciente no encontrado");
        pacienteRepository.deleteById(id);
    }
}
