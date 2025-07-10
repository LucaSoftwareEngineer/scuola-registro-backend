package tool.management.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tool.management.backend.models.Insegnante;

@Repository
public interface InsegnanteRepository extends JpaRepository<Insegnante, Long> {
}
