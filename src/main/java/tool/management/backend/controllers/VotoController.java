package tool.management.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tool.management.backend.dto.*;
import tool.management.backend.models.Studente;
import tool.management.backend.models.Voto;
import tool.management.backend.services.VotoService;

import java.util.List;

@RestController
@RequestMapping("/api/voto")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class VotoController {

    private final VotoService votoService;

    @PostMapping("/aggiungi")
    public ResponseEntity<AggiungiVotoResponse> aggiungiVoto(@RequestBody AggiungiVotoRequest request) {
        return ResponseEntity.ok(new AggiungiVotoResponse(
                votoService.aggiungiVoto(
                        request.getIdStudente(),
                        request.getIdMateria(),
                        request.getValutazione()
                )
            )
        );
    }

    @PutMapping("/modifica")
    public ResponseEntity<ModificaVotoResponse> modificaVoto(@RequestBody ModificaVotoRequest request) {
        return ResponseEntity.ok(new ModificaVotoResponse(
                        votoService.modificaVoto(
                                request.getIdStudente(),
                                request.getIdMateria(),
                                request.getValutazione(),
                                request.getIdVoto()
                        )
                )
        );
    }

    @DeleteMapping("/elimina/{idVoto}")
    public ResponseEntity<EliminaVotoResponse> eliminaVoto(@PathVariable Long idVoto) {
        return ResponseEntity.ok(new EliminaVotoResponse(
                votoService.eliminaVoto(idVoto)
        ));
    }

    @GetMapping("/studente")
    public ResponseEntity<List<Voto>> elencoVotiStudentePerMateria(
            @RequestParam Long idStudente,
            @RequestParam Long idMateria
    ) {
        return ResponseEntity.ok(votoService.elencoVotiStudentePerMateria(idStudente, idMateria));
    }

    @GetMapping("/studente/media/complessiva/{idStudente}")
    public ResponseEntity<MediaComplessivaResponse> mediaComplessivaStudente(
            @PathVariable Long idStudente
    ) {
        return ResponseEntity.ok(new MediaComplessivaResponse(votoService.mediaComplessivaStudente(idStudente)));
    }

    @GetMapping("/studente/media/materia/{idStudente}/{idMateria}")
    public ResponseEntity<MediaMateriaResponse> mediaComplessivaStudente(
            @PathVariable Long idStudente,
            @PathVariable Long idMateria
    ) {
        return ResponseEntity.ok(new MediaMateriaResponse(
                votoService.mediaMateriaStudente(idStudente, idMateria)
            )
        );
    }

    @GetMapping("/studenti/media/complessiva/insufficiente")
    public ResponseEntity<List<Studente>> studentiConMediaComplessivaInsufficiente() {
        return ResponseEntity.ok(votoService.studentiConMediaComplessivaInsufficiente());
    }

    @GetMapping("/studenti/media/materia/insufficiente/{idMateria}")
    public ResponseEntity<List<Studente>> studentiConMediaMateriaInsufficiente(
            @PathVariable Long idMateria
    ) {
        return ResponseEntity.ok(votoService.studentiConMediaMateriaInsufficiente(idMateria));
    }

    @GetMapping("/studenti/senza/voti/sufficienti")
    public ResponseEntity<List<Studente>> studentiSenzaVotiSufficienti() {
        return ResponseEntity.ok(votoService.studentiSenzaVotiSufficienti());
    }

}
