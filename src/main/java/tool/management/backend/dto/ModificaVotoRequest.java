package tool.management.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModificaVotoRequest {

    private Long idStudente;
    private Long idMateria;
    private float valutazione;
    private Long idVoto;

}
