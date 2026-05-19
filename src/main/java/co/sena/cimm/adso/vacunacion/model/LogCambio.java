package co.sena.cimm.adso.vacunacion.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_cambios")
public class LogCambio {

    public enum Accion {
        INSERT, UPDATE, DELETE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('INSERT','UPDATE','DELETE')")
    private Accion accion;

    @Column(name = "id_registro", nullable = false)
    private Integer idRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime fecha;

    public LogCambio() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Accion getAccion() { return accion; }
    public void setAccion(Accion accion) { this.accion = accion; }
    public Integer getIdRegistro() { return idRegistro; }
    public void setIdRegistro(Integer idRegistro) { this.idRegistro = idRegistro; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
