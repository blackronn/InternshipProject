package com.example.InternshipProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
<<<<<<< HEAD
                // 1. CORS yapılandırmasını en üste alalım ve kaynağını belirtelim.
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. CSRF'i devre dışı bırakalım (API'lar için standart).
                .csrf(csrf -> csrf.disable())

                // 3. Tüm HTTP isteklerine, kimlik doğrulaması olmadan izin verelim.
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
=======
                .cors(withDefaults()) // Burası corsConfigurationSource bean'ini kullanır
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Şimdilik tüm isteklere izin veriliyor
>>>>>>> 049e957 (feat: backend project initial push)
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // İzin verilen origin'leri (kaynakları) buraya listeleyin. "*" yerine spesifik olmak daha güvenlidir.
        configuration.setAllowedOrigins(List.of(
                "http://localhost:8085",
                "https://main.d1fdmlydmlt5lg.amplifyapp.com"
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true); // Frontend'in cookie gibi credential'lar göndermesine izin verir

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Tüm yollar için bu konfigürasyonu uygula

        return source;
    }
}