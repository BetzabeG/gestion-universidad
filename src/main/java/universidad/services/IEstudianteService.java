package universidad.services;
import universidad.models.Estudiante;

import java.util.*;

import universidad.dtos.EstudianteDTO;
import java.util.List;

public interface IEstudianteService {
    List<EstudianteDTO> obtenerTodosLosEstudiantes();
    EstudianteDTO obtenerEstudiantePorId(Long id);
    EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO);
    // Para crear un nuevo estudiante
    EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO);
    
    // para eliminar un estudiante
    void eliminarEstudiante(Long id); 
}
