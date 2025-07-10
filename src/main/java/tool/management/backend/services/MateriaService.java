package tool.management.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tool.management.backend.models.Materia;
import tool.management.backend.repositories.MateriaRepository;

@Service
@RequiredArgsConstructor
public class MateriaService {

    private final MateriaRepository materiaRepository;

    public Materia aggiungiMateria(String nome) {
        Materia materia = new Materia(null, nome);
        return materiaRepository.save(materia);
    }

    public Materia modificaMateria(Long idMateria, String nome) {
        Materia materia = new Materia(idMateria, nome);
        return materiaRepository.save(materia);
    }

    public Boolean eliminaMateria(Long idMateria) {
        try {
            materiaRepository.deleteById(idMateria);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
