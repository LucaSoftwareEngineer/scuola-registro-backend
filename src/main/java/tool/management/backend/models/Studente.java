package tool.management.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="srb_studenti")
@PrimaryKeyJoinColumn(name = "idUser")
public class Studente extends User {

    private String nome;
    private String cognome;
    private Date dataNascita;
    private String luogoNascita;
    private String residenza;
    private Date dataImmatricolazione;

}
