package com.example.InternshipProject.controllers;

import com.example.InternshipProject.services.dtos.responses.UniversityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8085")  // Vue port’un
@RequiredArgsConstructor
public class UniversityController {
    private final RestTemplate restTemplate;

    @GetMapping("/universities")
    public List<String> list() {
        String url = "https://api.kadircolak.com/Universite/JSON/API/AllUniversity";
        UniversityDto[] arr = restTemplate.getForObject(url, UniversityDto[].class);
        return Arrays.stream(arr)
                .map(UniversityDto::getUniversityName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}

