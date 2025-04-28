package universidad.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import universidad.dtos.EstudianteDTO;
import universidad.models.Estudiante;
import universidad.models.Materia;
import universidad.repository.EstudianteRepository;
import universidad.services.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceimpl implements IEstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EstudianteDTO obtenerEstudiantePorNumeroInscripcion(String numeroInscripcion) {
        Estudiante estudiante = estudianteRepository.findByNumeroInscripcion(numeroInscripcion);
        return convertToDTO(estudiante);
    }

    @Override
    public List<EstudianteDTO> obtenerEstudianteActivo() {
        return estudianteRepository.findAll()
                .stream()
                .filter(estudiante -> "activo".equalsIgnoreCase(estudiante.getEstado()))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
/*
    @Override
    public List<Materia> obtenerMateriasDeEstudiante(Long estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        return estudiante.getMaterias();
    }*/

    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = convertToEntity(estudianteDTO);
        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);
        return convertToDTO(estudianteGuardado);
    }

    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudianteExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        estudianteExistente.setNombre(estudianteDTO.getNombre());
        estudianteExistente.setApellido(estudianteDTO.getApellido());
        estudianteExistente.setEmail(estudianteDTO.getEmail());
        estudianteExistente.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudianteExistente.setNumeroInscripcion(estudianteDTO.getNumeroInscripcion());
        estudianteExistente.setEstado(estudianteDTO.getEstado());
        estudianteExistente.setUsuarioAlta(estudianteDTO.getUsuarioAlta());
        estudianteExistente.setFechaAlta(estudianteDTO.getFechaAlta());
        estudianteExistente.setUsuarioModificacion(estudianteDTO.getUsuarioModificacion());
        estudianteExistente.setFechaModificacion(estudianteDTO.getFechaModificacion());
        estudianteExistente.setUsuarioBaja(estudianteDTO.getUsuarioBaja());
        estudianteExistente.setFechaBaja(estudianteDTO.getFechaBaja());
        estudianteExistente.setMotivoBaja(estudianteDTO.getMotivoBaja());

        Estudiante estudianteActualizado = estudianteRepository.save(estudianteExistente);
        return convertToDTO(estudianteActualizado);
    }

    @Override
    public List<EstudianteDTO> obtenerEstudiantesPorNombre(String nombre) {
        return List.of();
    }
/*
    @Override
    public List<EstudianteDTO> obtenerEstudiantePorId(Long id) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id);
        if (estudianteOptional.isPresent()) {
            return List.of(convertToDTO(estudianteOptional.get()));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado");
        }
    }
*/
    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantesOrdenadosPorApellido() {
        return List.of();
    }
    // estudiantes por curso

/*
    @Override
    public List<Materia> obtenerMateriasDeEstudiante(Long estudianteId) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(estudianteId);
        if (estudianteOptional.isPresent()) {
            return estudianteOptional.get().getMaterias();
        } else {
            throw new RuntimeException("Estudiante no encontrado con ID: " + estudianteId);
        }
    }*/
// EstudianteServiceImpl.java:
@Override
public Optional<List<Materia>> obtenerMateriasDeEstudiante(Long estudianteId) {
    Optional<Estudiante> estudianteOptional = estudianteRepository.findById(estudianteId);
    return estudianteOptional.map(Estudiante::getMaterias);
}



    /*
    // nuevo
    //nuevo 1604
    @Override
    public List<EstudianteDTO> obtenerEstudiantesPorNombre(String nombre) {
        return estudianteRepository.findByNombre(nombre).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantesOrdenadosPorApellido() {
        return estudianteRepository.findAllByOrderByApellidoAsc().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());


     */

    private EstudianteDTO convertToDTO(Estudiante estudiante) {
        return EstudianteDTO.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .apellido(estudiante.getApellido())
                .email(estudiante.getEmail())
                .fechaNacimiento(estudiante.getFechaNacimiento())
                .numeroInscripcion(estudiante.getNumeroInscripcion())
                .estado(estudiante.getEstado())
                .usuarioAlta(estudiante.getUsuarioAlta())
                .fechaAlta(estudiante.getFechaAlta())
                .usuarioModificacion(estudiante.getUsuarioModificacion())
                .fechaModificacion(estudiante.getFechaModificacion())
                .usuarioBaja(estudiante.getUsuarioBaja())
                .fechaBaja(estudiante.getFechaBaja())
                .motivoBaja(estudiante.getMotivoBaja())
                .build();
    }

    private Estudiante convertToEntity(EstudianteDTO estudianteDTO) {
        return Estudiante.builder()
                .id(estudianteDTO.getId())
                .nombre(estudianteDTO.getNombre())
                .apellido(estudianteDTO.getApellido())
                .email(estudianteDTO.getEmail())
                .fechaNacimiento(estudianteDTO.getFechaNacimiento())
                .numeroInscripcion(estudianteDTO.getNumeroInscripcion())
                .usuarioAlta(estudianteDTO.getUsuarioAlta())
                .fechaAlta(estudianteDTO.getFechaAlta())
                .usuarioModificacion(estudianteDTO.getUsuarioModificacion())
                .fechaModificacion(estudianteDTO.getFechaModificacion())
                .estado(estudianteDTO.getEstado())
                .usuarioBaja(estudianteDTO.getUsuarioBaja())
                .fechaBaja(estudianteDTO.getFechaBaja())
                .motivoBaja(estudianteDTO.getMotivoBaja())
                .build();
    }
}
