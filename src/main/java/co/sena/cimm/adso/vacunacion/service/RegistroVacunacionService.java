package co.sena.cimm.adso.vacunacion.service;

import co.sena.cimm.adso.vacunacion.model.RegistroVacunacion;
import co.sena.cimm.adso.vacunacion.repository.RegistroVacunacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegistroVacunacionService {

    private final RegistroVacunacionRepository registroVacunacionRepository;

    public RegistroVacunacionService(RegistroVacunacionRepository registroVacunacionRepository) {
        this.registroVacunacionRepository = registroVacunacionRepository;
    }

    @Transactional(readOnly = true)
    public List<RegistroVacunacion> listarTodos() {
        return registroVacunacionRepository.findAllByOrderByFechaVacunacionDesc();
    }

    @Transactional(readOnly = true)
    public RegistroVacunacion buscarPorId(Long id) {
        return registroVacunacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro con ID " + id + " no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<RegistroVacunacion> listarPorPaciente(Long pacienteId) {
        return registroVacunacionRepository.findByPacienteIdOrderByFechaVacunacionDesc(pacienteId);
    }

    public RegistroVacunacion guardar(RegistroVacunacion registro) {
        return registroVacunacionRepository.save(registro);
    }

    public void eliminar(Long id) {
        if (!registroVacunacionRepository.existsById(id)) {
            throw new RuntimeException("Registro no encontrado");
        }
        registroVacunacionRepository.deleteById(id);
    }
}
