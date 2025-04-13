package universidad.services.impl;
import universidad.dtos.EstudianteDTO;
import universidad.models.Estudiante;
import universidad.repository.EstudianteRepository;
import universidad.services.IEstudianteService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceimpl implements IEstudianteService{
    //private final EstudianteRepository estudianteRepository;
    @Autowired
    public EstudianteRepository estudianteRepository;

    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll().stream()
                .map(this::convertirEstudianteADTO)
                .collect(Collectors.toList());
    }

    @Override
    public EstudianteDTO obtenerEstudiantePorId(Long id) {
        return null;
    }

    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        return null;
    }

    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        return null;
    }

    @Override
    public void eliminarEstudiante(Long id) {

    }


    private EstudianteDTO convertirEstudianteADTO(Estudiante estudiante){
        return EstudianteDTO.builder()
            .id(estudiante.getId())
            .nombre(estudiante.getNombre())
            .apellido(estudiante.getApellido())
            .email(estudiante.getEmail())
            .fechaNacimiento(estudiante.getFechaNacimiento())
            .numeroInscripcion(estudiante.getNumeroInscripcion())
            .build();
    }

    private Estudiante convertirDTOaEstudiante(EstudianteDTO estudianteDTO){
        return Estudiante.builder()
            //.id(estudianteDTO.getId())
            .nombre(estudianteDTO.getNombre())
            .apellido(estudianteDTO.getApellido())
            .email(estudianteDTO.getEmail())
            .fechaNacimiento(estudianteDTO.getFechaNacimiento())
            .numeroInscripcion(estudianteDTO.getNumeroInscripcion())
            .build();
    }
}