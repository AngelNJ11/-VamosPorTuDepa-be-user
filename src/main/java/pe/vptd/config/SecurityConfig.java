package pe.vptd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para pruebas con Postman/Angular
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/usuario/**").permitAll() // Permitir sin autenticación
	                .anyRequest().authenticated()
	            )
	            .httpBasic(Customizer.withDefaults()); // Usa auth básica si lo necesitas

	        return http.build();
	    }
}
