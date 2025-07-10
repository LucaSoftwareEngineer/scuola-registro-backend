package tool.management.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tool.management.backend.dto.*;
import tool.management.backend.models.Insegnante;
import tool.management.backend.models.Studente;
import tool.management.backend.models.User;
import tool.management.backend.repositories.UserRepository;
import tool.management.backend.security.JwtUtil;
import tool.management.backend.services.InsegnanteService;
import tool.management.backend.services.StudenteService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final StudenteService studenteService;
    private final InsegnanteService insegnanteService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register/studente")
    public ResponseEntity<Studente> registerStudente(@RequestBody RegisterStudenteRequest request) {
        return ResponseEntity.ok(studenteService.registerStudente(
                request.getUsername(),
                passwordEncoder.encode(request.getRawPassword()),
                request.getNome(),
                request.getCognome(),
                request.getDataNascita(),
                request.getLuogoNascita(),
                request.getResidenza(),
                request.getDataImmatricolazione()
        ));
    }

    @PostMapping("/register/insegnante")
    public ResponseEntity<Insegnante> registerInsegnante(@RequestBody RegisterInsegnanteRequest request) {
        return ResponseEntity.ok(insegnanteService.registerInsegnante(
                request.getUsername(),
                passwordEncoder.encode(request.getRawPassword()),
                request.getNome(),
                request.getCognome(),
                request.getDataNascita(),
                request.getLuogoNascita(),
                request.getResidenza(),
                request.getDataAssunzione()
        ));
    }

}
