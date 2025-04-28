package universidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universidad.models.Estudiante;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Estudiante findByNumeroInscripcion(String numeroInscripcion);
    Estudiante findByEstado(String estado);

    Optional<Estudiante> findById(Long id);
}


