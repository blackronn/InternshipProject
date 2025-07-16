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
                .csrf(csrf -> csrf.disable()) // Modern API'lerde genellikle devre dışı bırakılır
                .authorizeHttpRequests(auth -> auth
                        // Aşağıdaki satır, /api/ ile başlayan tüm adreslere izinsiz (şifresiz) erişime izin verir.
                        .requestMatchers("/api/**").permitAll()
                        // Diğer tüm istekler kimlik doğrulaması gerektirsin.
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // Tarayıcı tabanlı Basic Auth penceresini etkinleştirir
        return http.build();
    }
}
