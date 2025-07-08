package com.example.InternshipProject.repositories;
import com.example.InternshipProject.entities.concretes.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer>{

}
