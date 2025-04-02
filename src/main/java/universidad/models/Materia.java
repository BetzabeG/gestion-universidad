package universidad.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Materia {
    private Long id;
    private String nombreMateria;
    private String codigoUnico;
    private String creditos;
}