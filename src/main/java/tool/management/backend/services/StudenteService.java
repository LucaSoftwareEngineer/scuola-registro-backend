package tool.management.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tool.management.backend.models.Studente;
import tool.management.backend.repositories.StudenteRepository;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class StudenteService {

    private final StudenteRepository studenteRepository;
    private final String STUDENTE = "STUDENTE";

    public Studente registerStudente(
            String username,
            String password,
            String nome,
			String cognome,
			Date dataNascita,
			String luogoNascita,
			String residenza,
			Date dataImmatricolazione
    ) {
        Studente studente = new Studente();
        studente.setUsername(username);
        studente.setPassword(password);
        studente.setRole(STUDENTE);
        studente.setNome(nome);
        studente.setCognome(cognome);
        studente.setDataNascita(dataNascita);
        studente.setLuogoNascita(luogoNascita);
        studente.setResidenza(residenza);
        studente.setDataImmatricolazione(dataImmatricolazione);
        return studenteRepository.save(studente);
    }

}
