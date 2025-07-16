package tool.management.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tool.management.backend.models.Materia;
import tool.management.backend.models.Studente;
import tool.management.backend.models.Voto;
import tool.management.backend.repositories.MateriaRepository;
import tool.management.backend.repositories.StudenteRepository;
import tool.management.backend.repositories.VotoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VotoService {

    private final VotoRepository votoRepository;
    private final StudenteRepository studenteRepository;
    private final MateriaRepository materiaRepository;

    public Boolean aggiungiVoto(
            Long idStudente,
            Long idMateria,
            float valutazione
    ) {
        try {
            Studente studente = studenteRepository.findById(idStudente).get();
            Materia materia = materiaRepository.findById(idMateria).get();
            Voto voto = new Voto();
            voto.setValutazione(valutazione);
            voto.setMateria(materia);
            voto.setStudente(studente);
            votoRepository.save(voto);
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    public Boolean modificaVoto(
            Long idStudente,
            Long idMateria,
            float valutazione,
            Long idVoto
    ) {
        try {
            Studente studente = studenteRepository.findById(idStudente).get();
            Materia materia = materiaRepository.findById(idMateria).get();
            Voto voto = new Voto();
            voto.setValutazione(valutazione);
            voto.setMateria(materia);
            voto.setStudente(studente);
            voto.setIdVoto(idVoto);
            votoRepository.save(voto);
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    public Boolean eliminaVoto(Long idVoto) {
        try {
            votoRepository.deleteById(idVoto);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Voto> elencoVotiStudentePerMateria(Long idStudente, Long idMateria) {
        Studente studente = studenteRepository.findById(idStudente).get();
        Materia materia = materiaRepository.findById(idMateria).get();
        List<Voto> voti = votoRepository.findByStudenteAndMateria(studente, materia);
        voti.forEach(voto -> voto.getStudente().setPassword(null));
        return voti;
    }

    public List<Voto> elencoVoti(Long idStudente) {
        Studente studente = studenteRepository.findById(idStudente).get();
        List<Voto> voti = votoRepository.findByStudente(studente);
        voti.forEach(voto -> voto.getStudente().setPassword(null));
        return voti;
    }

    public float mediaComplessivaStudente(Long idStudente) {
        float sommaVoti = 0;
        float numeroVoti = 0;
        float mediaVoti = 0;

        List<Voto> voti = this.elencoVoti(idStudente);

        for (Voto voto : voti) {
            sommaVoti += voto.getValutazione();
            numeroVoti++;
        }

        mediaVoti = sommaVoti / numeroVoti;

        return mediaVoti;
    }

    public float mediaMateriaStudente(Long idStudente, Long idMateria) {
        float sommaVoti = 0;
        float numeroVoti = 0;
        float mediaVoti = 0;

        List<Voto> voti = this.elencoVotiStudentePerMateria(idStudente, idMateria);

        for (Voto voto : voti) {
            sommaVoti += voto.getValutazione();
            numeroVoti++;
        }

        mediaVoti = sommaVoti / numeroVoti;

        return mediaVoti;
    }

    public List<Studente> studentiConMediaComplessivaInsufficiente() {
        List<Studente> studenti = studenteRepository.findAll();
        List<Studente> studentiMediaComplessivaInsufficiente = new ArrayList<>();
        for (Studente studente : studenti) {
            if (this.mediaComplessivaStudente(studente.getIdUser()) < 6.0) {
                studentiMediaComplessivaInsufficiente.add(studente);
            }
        }
        return studentiMediaComplessivaInsufficiente;
    }

    public List<Studente> studentiConMediaMateriaInsufficiente(Long idMateria) {
        List<Studente> studenti = studenteRepository.findAll();
        List<Studente> studentiMediaComplessivaInsufficiente = new ArrayList<>();
        for (Studente studente : studenti) {
            if (this.mediaMateriaStudente(studente.getIdUser(), idMateria) < 6.0) {
                studentiMediaComplessivaInsufficiente.add(studente);
            }
        }
        return studentiMediaComplessivaInsufficiente;
    }

    public List<Studente> studentiSenzaVotiSufficienti() {
        List<Studente> studenti = studenteRepository.findAll();
        List<Studente> studentiSenzaSufficienze = new ArrayList<>();

        for (Studente studente : studenti) {
            if (this.elencoVoti(studente.getIdUser()).size() > 0) {
                for (Voto voto : this.elencoVoti(studente.getIdUser())) {
                    if (voto.getValutazione() < 6.0) {
                        if (!studentiSenzaSufficienze.contains(studente)) {
                            studentiSenzaSufficienze.add(studente);
                        }
                    }
                }
            } else {
                studentiSenzaSufficienze.add(studente);
            }
        }

        for (Studente studente : studentiSenzaSufficienze) {
            studente.setPassword(null);
        }

        return studentiSenzaSufficienze;
    }

}
