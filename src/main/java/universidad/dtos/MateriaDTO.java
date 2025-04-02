package universidad.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MateriaDTO {
    private Long id;
    private String nombreMateria;
    private String codigoUnico;
    private Integer creditos;
}
