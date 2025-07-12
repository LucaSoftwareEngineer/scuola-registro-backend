package tool.management.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tool.management.backend.dto.AssociaMateriaInsegnanteRequest;
import tool.management.backend.dto.AssociaMateriaInsegnanteResponse;
import tool.management.backend.dto.DisassociaMateriaInsegnanteRequest;
import tool.management.backend.dto.DisassociaMateriaInsegnanteResponse;
import tool.management.backend.services.InsegnanteService;

@RestController
@RequestMapping("/api/insegnante")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InsegnanteController {

    private final InsegnanteService insegnanteService;

    @PutMapping("/associa/materia")
    public ResponseEntity<AssociaMateriaInsegnanteResponse> associaMateriaInsegnante(@RequestBody AssociaMateriaInsegnanteRequest request) {
        return ResponseEntity.ok(new AssociaMateriaInsegnanteResponse(
                insegnanteService.associaMateriaInsegnante(request.getIdUser(), request.getIdMateria())
            )
        );
    }

    @PutMapping("/disassocia/materia")
    public ResponseEntity<DisassociaMateriaInsegnanteResponse> associaMateriaInsegnante(@RequestBody DisassociaMateriaInsegnanteRequest request) {
        return ResponseEntity.ok(new DisassociaMateriaInsegnanteResponse(
                        insegnanteService.disassociaMateriaInsegnante(request.getIdUser(), request.getIdMateria())
                )
        );
    }

}
