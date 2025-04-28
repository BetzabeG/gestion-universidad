package universidad.services;

import universidad.dtos.EstudianteDTO;
import universidad.models.Materia;

import java.util.List;
import java.util.Optional;

public interface IEstudianteService {
    List<EstudianteDTO> obtenerTodosLosEstudiantes();
    List<EstudianteDTO> obtenerEstudianteActivo();
    EstudianteDTO obtenerEstudiantePorNumeroInscripcion(String numeroInscripcion);
    //List<Materia> obtenerMateriasDeEstudiante(Long estudianteId);
    EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO);
    EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO);

    //Nuevo 1604
    List<EstudianteDTO> obtenerEstudiantesPorNombre(String nombre);
    List<EstudianteDTO> obtenerTodosLosEstudiantesOrdenadosPorApellido();

    //obtener por cursos
    //List<Materia> obtenerMateriasDeEstudiante(Long estudianteId);

    //List<EstudianteDTO> obtenerEstudiantePorId(Long id);

    //Modificacion 2104
    public Optional<List<Materia>> obtenerMateriasDeEstudiante(Long estudianteId); // Método para obtener las materias de un estudiante por su ID
}






