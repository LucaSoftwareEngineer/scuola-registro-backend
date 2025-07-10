package tool.management.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tool.management.backend.models.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
}
