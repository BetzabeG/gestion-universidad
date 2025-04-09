package universidad.services.impl;
import universidad.dtos.EstudianteDTO;
import universidad.models.Estudiante;
import universidad.repository.EstudianteRepositorys;
import universidad.services.IEstudianteService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteServiceimpl implements IEstudianteService{
    private final EstudianteRepositorys estudianteRepositorys;
    @Autowired
    public EstudianteServiceimpl(EstudianteRepositorys estudianteRepositorys){
        this.estudianteRepositorys = estudianteRepositorys;
    }
    @PostConstruct
    public void init(){
        estudianteRepositorys.init();
    }
    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepositorys.findAll();
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
        Estudiante estudiante = estudianteRepositorys.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        return convertirEstudianteADTO(estudiante);
    }
    /*
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
    }*/
    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        obtenerEstudiantePorId(id);
        Estudiante estudianteActualizado = convertirDTOaEstudiante(estudianteDTO);
        estudianteActualizado.setId(id);
        Estudiante estudianteGuardado = estudianteRepositorys.save(estudianteActualizado);
        return convertirEstudianteADTO(estudianteGuardado);
    }


    // Para crear un nuevo estudiante
    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante nuevoEstudiante = convertirDTOaEstudiante(estudianteDTO);
        Estudiante estudianteGuardado = estudianteRepositorys.save(nuevoEstudiante);
        return convertirEstudianteADTO(estudianteGuardado);
    }
    // para eliminar un estudiante
    @Override
    public void eliminarEstudiante(Long id) {
        estudianteRepositorys.deleteById(id);
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