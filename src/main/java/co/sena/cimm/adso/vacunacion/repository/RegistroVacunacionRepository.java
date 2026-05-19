package co.sena.cimm.adso.vacunacion.repository;

import co.sena.cimm.adso.vacunacion.model.Paciente;
import co.sena.cimm.adso.vacunacion.model.RegistroVacunacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroVacunacionRepository extends JpaRepository<RegistroVacunacion, Long> {

    List<RegistroVacunacion> findByPacienteIdOrderByFechaVacunacionDesc(Long pacienteId);

    List<RegistroVacunacion> findAllByOrderByFechaVacunacionDesc();
}
