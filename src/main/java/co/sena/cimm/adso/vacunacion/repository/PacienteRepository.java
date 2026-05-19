package co.sena.cimm.adso.vacunacion.repository;

import co.sena.cimm.adso.vacunacion.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Solo la interfaz - Spring genera la implementacion completa automaticamente.
 * JpaRepository<Paciente, Long> ya incluye sin escribir codigo:
 * save(p) -> INSERT o UPDATE segun si el id es null o no
 * findById(id) -> SELECT * FROM pacientes WHERE id = ?
 * findAll() -> SELECT * FROM pacientes
 * deleteById(id) -> DELETE FROM pacientes WHERE id = ?
 * count() -> SELECT COUNT(*) FROM pacientes
 * existsById(id) -> SELECT EXISTS(SELECT 1 FROM pacientes WHERE id = ?)
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Spring genera el SQL a partir del nombre del metodo:
    // findByDocumento -> SELECT * FROM pacientes WHERE documento = ?
    Optional<Paciente> findByDocumento(String documento);

    // findByNombresContainingIgnoreCase ->
    // SELECT * FROM pacientes WHERE UPPER(nombres) LIKE UPPER('%texto%')
    List<Paciente> findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(
            String nombres, String apellidos
    );

    // Metodo con @Query para SQL personalizado
    @Query("SELECT p FROM Paciente p WHERE p.documento = :doc")
    Optional<Paciente> buscarPorDocumento(@Param("doc") String doc);
}
