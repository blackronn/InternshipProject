//package com.example.InternshipProject.configuration; // Kendi paket adınıza göre düzeltin
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class Config implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**") // /api/ altındaki tüm yollara uygula
//                .allowedOrigins("http://localhost:8085")
//                .allowedOrigins("https://main.d1fdmlydmlt5lg.amplifyapp.com")// Sadece bu adresten gelen isteklere izin ver
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // İzin verilen HTTP metotları
//                .allowedHeaders("*") // Gelen tüm header'lara izin ver
//                .allowCredentials(true);
//    }
//}