// src/main/java/com/example/InternshipProject/services/abstracts/DepartmentService.java
package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.services.dtos.responses.DepartmentDto;
import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> getByUniversity(String university);
    List<DepartmentDto> getAllDepartments();
}