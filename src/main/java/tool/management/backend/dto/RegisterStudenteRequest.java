package tool.management.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class RegisterStudenteRequest extends RegisterRequest {

    private String nome;
    private String cognome;
    private Date dataNascita;
    private String luogoNascita;
    private String residenza;
    private Date dataImmatricolazione;

}
