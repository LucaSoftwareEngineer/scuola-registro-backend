package tool.management.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tool.management.backend.dto.AggiungiMateriaRequest;
import tool.management.backend.dto.EliminaMateriaResponse;
import tool.management.backend.dto.ModificaMateriaRequest;
import tool.management.backend.models.Materia;
import tool.management.backend.services.MateriaService;

@RestController
@RequestMapping("/api/materia")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MateriaController {

    private final MateriaService materiaService;

    @PostMapping("/aggiungi")
    public ResponseEntity<Materia> aggiungiMateria(@RequestBody AggiungiMateriaRequest request) {
        return ResponseEntity.ok(materiaService.aggiungiMateria(request.getNome()));
    }

    @PutMapping("/modifica")
    public ResponseEntity<Materia> modificaMateria(@RequestBody ModificaMateriaRequest request) {
        return ResponseEntity.ok(materiaService.modificaMateria(request.getIdMateria(), request.getNome()));
    }

    @DeleteMapping("/elimina/{idMateria}")
    public ResponseEntity<EliminaMateriaResponse> eliminaMateria(@PathVariable Long idMateria) {
        return ResponseEntity.ok(new EliminaMateriaResponse(materiaService.eliminaMateria(idMateria)));
    }

}
