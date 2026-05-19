package co.sena.cimm.adso.vacunacion.repository;

import co.sena.cimm.adso.vacunacion.model.LogCambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogCambioRepository extends JpaRepository<LogCambio, Long> {

    List<LogCambio> findAllByOrderByFechaDesc();
}
