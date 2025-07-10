package tool.management.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tool.management.backend.models.Insegnante;
import tool.management.backend.repositories.InsegnanteRepository;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class InsegnanteService {

    private final InsegnanteRepository insegnanteRepository;
    private final String INSEGNANTE = "INSEGNANTE";

    public Insegnante registerInsegnante(
            String username,
            String password,
            String nome,
            String cognome,
            Date dataNascita,
            String luogoNascita,
            String residenza,
            Date dataAssunzione
    ) {
        Insegnante insegnante = new Insegnante();
        insegnante.setUsername(username);
        insegnante.setPassword(password);
        insegnante.setRole(INSEGNANTE);
        insegnante.setNome(nome);
        insegnante.setCognome(cognome);
        insegnante.setDataNascita(dataNascita);
        insegnante.setLuogoNascita(luogoNascita);
        insegnante.setResidenza(residenza);
        insegnante.setDataAssunzione(dataAssunzione);
        return insegnanteRepository.save(insegnante);
    }

}
