package universidad.controllers;

import universidad.dtos.EstudianteDTO;
import universidad.models.Estudiante;
import universidad.models.Materia;
import universidad.services.IEstudianteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final IEstudianteService estudianteService;

    @Autowired
    public EstudianteController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes() {
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/inscripcion/{numeroInscripcion}")
    public ResponseEntity<EstudianteDTO> obtenerEstudiantePorNumeroInscripcion(@PathVariable String numeroInscripcion) {
        EstudianteDTO estudiante = estudianteService.obtenerEstudiantePorNumeroInscripcion(numeroInscripcion);
        return ResponseEntity.ok(estudiante);
    }
/*
    @GetMapping("/{id}/materias")
    public ResponseEntity<List<Materia>> obtenerMateriasDeEstudiante(@PathVariable("id") Long estudianteId) {
        List<Materia> materias = estudianteService.obtenerMateriasDeEstudiante(estudianteId);
        return ResponseEntity.ok(materias);
    }*/

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EstudianteDTO> crearEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO nuevoEstudiante = estudianteService.crearEstudiante(estudianteDTO);
        return ResponseEntity.status(201).body(nuevoEstudiante);
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO estudianteActualizado = estudianteService.actualizarEstudiante(id, estudianteDTO);
        return ResponseEntity.ok(estudianteActualizado);
    }

    @PutMapping("/baja/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EstudianteDTO> actualizarBajaEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO estudianteActualizado = estudianteService.actualizarEstudiante(id, estudianteDTO);
        return ResponseEntity.ok(estudianteActualizado);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<EstudianteDTO>> obtenerEstudianteActivo() {
        List<EstudianteDTO> estudiantesActivos = estudianteService.obtenerEstudianteActivo();
        return ResponseEntity.ok(estudiantesActivos);
    }

    // ejercicios
    //nuevo
    @GetMapping("/nombre")
    public ResponseEntity<List<EstudianteDTO>> obtenerEstudiantesPorNombre(@RequestParam
                                                                           String nombre) {
        List<EstudianteDTO> estudiantes =
                estudianteService.obtenerEstudiantesPorNombre(nombre);
        return ResponseEntity.ok(estudiantes);
    }
    @GetMapping("/ordenados/apellido")
    public ResponseEntity<List<EstudianteDTO>>
    obtenerTodosLosEstudiantesOrdenadosPorApellido() {
        List<EstudianteDTO> estudiantesOrdenados =
                estudianteService.obtenerTodosLosEstudiantesOrdenadosPorApellido();
        return ResponseEntity.ok(estudiantesOrdenados);}
    //nuevo

    //obtenere estudiantes por cursos
    // Archivo: src/main/java/universidad/controllers/EstudianteController.java
    /*@GetMapping("/{id}/materias")
    public ResponseEntity<List<Materia>> obtenerMateriasDeEstudiante(@PathVariable Long id) {
        List<Materia> materias = estudianteService.obtenerMateriasDeEstudiante(id);
        return ResponseEntity.ok(materias);
    }*/
/*
    @GetMapping("/{id}/materias")
    public ResponseEntity<?> obtenerMateriasDeEstudiante(@PathVariable Long id) {
        List<EstudianteDTO> estudiantes = estudianteService.obtenerEstudiantePorId(id);

        if (!estudiantes.isEmpty()) {
            return ResponseEntity.ok(estudiantes.get(0).getMaterias());
        } else {
            return ResponseEntity.ok("Estudiante no encontrado");
        }
    }*/

    //EstudianteController.java:
    @GetMapping("/{id}/materias")
    public ResponseEntity<?> obtenerMateriasDeEstudiante(@PathVariable("id") Long estudianteId) {
        Optional<List<Materia>> materiasOptional = estudianteService.obtenerMateriasDeEstudiante(estudianteId);
        return materiasOptional.map(materias -> {
                    if (materias.isEmpty()) {
                        return ResponseEntity.ok("El estudiante no tiene materias asignadas.");
                    } else {
                        return ResponseEntity.ok().body(materias);
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}





