package co.sena.cimm.adso.vacunacion.service;

import co.sena.cimm.adso.vacunacion.model.Vacuna;
import co.sena.cimm.adso.vacunacion.repository.VacunaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VacunaService {

    private final VacunaRepository vacunaRepository;

    public VacunaService(VacunaRepository vacunaRepository) {
        this.vacunaRepository = vacunaRepository;
    }

    /** Lista todas las vacunas */
    @Transactional(readOnly = true)
    public List<Vacuna> listarTodas() {
        return vacunaRepository.findAll();
    }

    /** Lista solo las vacunas disponibles (no vencidas) */
    @Transactional(readOnly = true)
    public List<Vacuna> listarDisponibles() {
        return vacunaRepository.findByFechaVencimientoAfter(LocalDate.now());
    }

    /** Busca una vacuna por ID */
    @Transactional(readOnly = true)
    public Vacuna buscarPorId(Long id) {
        return vacunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Vacuna con ID " + id + " no encontrada"));
    }

    /** Guarda o actualiza una vacuna */
    public Vacuna guardar(Vacuna vacuna) {
        return vacunaRepository.save(vacuna);
    }

    /** Elimina una vacuna por ID */
    public void eliminar(Long id) {
        if (!vacunaRepository.existsById(id))
            throw new RuntimeException("Vacuna no encontrada");
        vacunaRepository.deleteById(id);
    }
}
