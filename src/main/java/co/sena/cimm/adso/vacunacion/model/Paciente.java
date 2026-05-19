package co.sena.cimm.adso.vacunacion.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Entity -> esta clase mapea a la tabla 'pacientes' en MySQL
 * @Table -> nombre de la tabla (opcional si el nombre de la clase coincide)
 */
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 20)
    private String documento;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Paciente() {}

    public Paciente(String nombres, String apellidos,
                    String documento, LocalDate fechaNacimiento) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.fechaNacimiento = fechaNacimiento;
    }

    /** Metodo de negocio */
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
