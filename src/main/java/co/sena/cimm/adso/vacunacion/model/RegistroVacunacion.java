package co.sena.cimm.adso.vacunacion.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registros_vacunacion")
public class RegistroVacunacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacuna", nullable = false)
    private Vacuna vacuna;

    @Column(name = "fecha_vacunacion", nullable = false)
    private LocalDateTime fechaVacunacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_personal_medico", nullable = false)
    private Usuario personalMedico;

    @Column(name = "lugar_vacunacion", nullable = false, length = 200)
    private String lugarVacunacion;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public RegistroVacunacion() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Vacuna getVacuna() { return vacuna; }
    public void setVacuna(Vacuna vacuna) { this.vacuna = vacuna; }
    public LocalDateTime getFechaVacunacion() { return fechaVacunacion; }
    public void setFechaVacunacion(LocalDateTime fechaVacunacion) { this.fechaVacunacion = fechaVacunacion; }
    public Usuario getPersonalMedico() { return personalMedico; }
    public void setPersonalMedico(Usuario personalMedico) { this.personalMedico = personalMedico; }
    public String getLugarVacunacion() { return lugarVacunacion; }
    public void setLugarVacunacion(String lugarVacunacion) { this.lugarVacunacion = lugarVacunacion; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
