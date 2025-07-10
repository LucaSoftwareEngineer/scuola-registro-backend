package tool.management.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tool.management.backend.models.Studente;

import java.util.Optional;

@Repository
public interface StudenteRepository extends JpaRepository<Studente, Long> {

}
