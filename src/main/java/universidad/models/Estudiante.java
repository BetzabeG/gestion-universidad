package universidad.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import universidad.models.Persona;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "estudiante")
public class Estudiante extends Persona {

    @Column(name = "numero_inscripcion", nullable = false, unique = true)
    private String numeroInscripcion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "usuario_alta")
    private String usuarioAlta;

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    @Basic(optional = true)
    private LocalDate fechaAlta;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    @Basic(optional = true)
    private LocalDate fechaModificacion;

    @Column(name = "usuario_baja")
    private String usuarioBaja;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    @Basic(optional = true)
    private LocalDate fechaBaja;

    @Column(name = "motivo_baja")
    private String motivoBaja;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "estudiante_materia",
            joinColumns = @JoinColumn(name = "id_estudiante"),
            inverseJoinColumns = @JoinColumn(name = "id_materia"))
    private List<Materia> materias;




}
