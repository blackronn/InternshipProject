package com.example.InternshipProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults()) // CORS ayarımız kalıyor, bu doğru.
                .csrf(csrf -> csrf.disable()) // CSRF koruması kapalı kalıyor.

                // 👇 YETKİLENDİRME KISMINI GEÇİCİ OLARAK DEĞİŞTİRİYORUZ
                .authorizeHttpRequests(auth -> auth
                        // Bu satır, gelen isteğin adresi ne olursa olsun,
                        // hepsine şimdilik sorgusuz sualsiz izin ver demek.
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}