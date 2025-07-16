package tool.management.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AggiungiVotoRequest {

    private Long idStudente;
    private Long idMateria;
    private float valutazione;

}
