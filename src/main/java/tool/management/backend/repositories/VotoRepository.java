package tool.management.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tool.management.backend.models.Materia;
import tool.management.backend.models.Studente;
import tool.management.backend.models.Voto;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    public List<Voto> findByStudenteAndMateria(Studente studente, Materia materia);
    public List<Voto> findByStudente(Studente studente);
}
