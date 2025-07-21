package com.example.InternshipProject.repositories;
import com.example.InternshipProject.entities.concretes.Assignment;
import com.example.InternshipProject.entities.concretes.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
    List<Todo> findByInternEmail(String internEmail);
}
