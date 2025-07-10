package tool.management.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="srb_insegnanti")
@PrimaryKeyJoinColumn(name = "idUser")
public class Insegnante extends User {

    private String nome;
    private String cognome;
    private Date dataNascita;
    private String luogoNascita;
    private String residenza;
    private Date dataAssunzione;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Materia> materie;

    public void associaMateria(Materia materia) {
        Boolean giaEsistente = false;
        for (Materia attuale:materie) {
            if (attuale == materia) {
                giaEsistente = true;
            }
        }
        if (giaEsistente == false) {
            materie.add(materia);
        }
    }

}
