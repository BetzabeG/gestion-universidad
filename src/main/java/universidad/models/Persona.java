package universidad.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity // es una clase JPA
@Table (name="persona")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Persona {
    // atributos de a clase persona

    @Id //anotacion que indica que este campo es una llave primaria
    @Column (name="id_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    @Column (nullable = false, length = 50)

    private String nombre;
    @Column (nullable = false, length = 50)
    private String apellido;
    @Column (nullable = false, unique = true)
    private String email;
    @Column (name="fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    @Basic(optional=false)
    private LocalDate fechaNacimiento;
}
