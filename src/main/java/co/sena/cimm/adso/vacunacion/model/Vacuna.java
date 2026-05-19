package co.sena.cimm.adso.vacunacion.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vacunas",
        uniqueConstraints = @UniqueConstraint(columnNames = {"lote", "nombre"}))
public class Vacuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String lote;

    @Column(nullable = false, length = 100)
    private String laboratorio;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Vacuna() {}

    public Vacuna(String nombre, String lote,
                  String laboratorio, LocalDate fechaVencimiento) {
        this.nombre = nombre;
        this.lote = lote;
        this.laboratorio = laboratorio;
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean estaVencida() {
        return LocalDate.now().isAfter(fechaVencimiento);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }

    public String getLaboratorio() { return laboratorio; }
    public void setLaboratorio(String laboratorio) { this.laboratorio = laboratorio; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
