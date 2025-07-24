// src/main/java/com/example/InternshipProject/controllers/UniversityController.java
package com.example.InternshipProject.controllers;

import com.example.InternshipProject.services.dtos.responses.UniversityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8085")  // Vue port’un
@RequiredArgsConstructor
public class UniversityController {
    private final RestTemplate restTemplate;

    @GetMapping("/universities")
    public List<String> list(@RequestParam(defaultValue = "Turkey") String country) {
        String url = "http://universities.hipolabs.com/search?country="
                + UriUtils.encode(country, StandardCharsets.UTF_8);
        UniversityDto[] arr = restTemplate.getForObject(url, UniversityDto[].class);
        return Arrays.stream(arr)
                .map(UniversityDto::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // --- EKLENEN YENİ ENDPOINT ---
    @GetMapping("/api/universities")
    public List<String> apiList(@RequestParam(defaultValue = "Turkey") String country) {
        // Aynı metodu tekrar kullanıyoruz.
        return list(country);
    }
}
