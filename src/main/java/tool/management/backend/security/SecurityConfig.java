package tool.management.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/api/auth/login").permitAll()
                                .requestMatchers("/api/auth/register/insegnante").permitAll()
                                .requestMatchers("/api/auth/register/studente").permitAll()
                                .requestMatchers("/api/voto/aggiungi").permitAll()
                                .requestMatchers("/api/voto/modifica").permitAll()
                                .requestMatchers("/api/voto/elimina").permitAll()
                                .requestMatchers("/api/voto/elimina/**").permitAll()
                                .requestMatchers("/api/voto/studente").permitAll()
                                .requestMatchers("/api/voto/studente/media/complessiva/").permitAll()
                                .requestMatchers("/api/voto/studente/media/complessiva/**").permitAll()
                                .requestMatchers("/api/voto/studente/media/materia/").permitAll()
                                .requestMatchers("/api/voto/studente/media/materia/**").permitAll()
                                .requestMatchers("/api/voto/studenti/media/complessiva/insufficiente").permitAll()
                                .requestMatchers("/api/voto/studenti/media/materia/insufficiente").permitAll()
                                .requestMatchers("/api/voto/studenti/media/materia/insufficiente/**").permitAll()
                                .requestMatchers("/api/voto/studenti/senza/voti/sufficienti").permitAll()
                                .requestMatchers("/api/insegnante/associa/materia").authenticated()
                                .requestMatchers("/api/insegnante/disassocia/materia").authenticated()
                                .requestMatchers("/api/materia/aggiungi").authenticated()
                                .requestMatchers("/api/materia/modifica").authenticated()
                                .requestMatchers("/api/materia/elimina").authenticated()
                                .requestMatchers("/api/materia/elimina/**").authenticated()
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtUtil, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}