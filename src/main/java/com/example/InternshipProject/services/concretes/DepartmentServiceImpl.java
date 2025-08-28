package com.example.InternshipProject.services.concretes;
import com.example.InternshipProject.services.dtos.responses.DepartmentDto;
import com.example.InternshipProject.services.abstracts.DepartmentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public List<DepartmentDto> getAllDepartments() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = new ClassPathResource("departments.json").getInputStream();
            return mapper.readValue(is, new TypeReference<List<DepartmentDto>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Departmanlar yüklenemedi!", e);
        }
    }

    @Override
    public List<DepartmentDto> getByUniversity(String university) {
        return getAllDepartments();
    }
}

