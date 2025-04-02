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

@Service
public class EstudianteServiceimpl implements IEstudianteService{
    private final EstudianteRepository estudianteRepository;
    @Autowired
    public EstudianteServiceimpl(EstudianteRepository estudianteRepository){
        this.estudianteRepository = estudianteRepository;
    }
    @PostConstruct
    public void init(){
        estudianteRepository.init();
    }
    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>();
        for(Estudiante estudiante: estudiantes){
            estudiantesDTO.add(EstudianteDTO.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .apellido(estudiante.getApellido())
                .email(estudiante.getEmail())
                .fechaNacimiento(estudiante.getFechaNacimiento())
                .numeroInscripcion(estudiante.getNumeroInscripcion())
                .build());
        }
        return estudiantesDTO;
    }

    @Override
    public EstudianteDTO obtenerEstudiantePorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        return convertirEstudianteADTO(estudiante);
    }

    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudianteExistente = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        estudianteExistente.setNombre(estudianteDTO.getNombre());
        estudianteExistente.setApellido(estudianteDTO.getApellido());
        estudianteExistente.setEmail(estudianteDTO.getEmail());
        estudianteExistente.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudianteExistente.setNumeroInscripcion(estudianteDTO.getNumeroInscripcion());

        Estudiante estudianteActualizado = estudianteRepository.save(estudianteExistente);
        return convertirEstudianteADTO(estudianteActualizado);
    }
    // Para crear un nuevo estudiante
    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante nuevoEstudiante = convertirDTOaEstudiante(estudianteDTO);
        Estudiante estudianteGuardado = estudianteRepository.save(nuevoEstudiante);
        return convertirEstudianteADTO(estudianteGuardado);
    }
    // para eliminar un estudiante
    @Override
    public void eliminarEstudiante(Long id) {
        estudianteRepository.deleteById(id);
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