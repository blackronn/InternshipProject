package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternRepository extends JpaRepository<Intern,Integer> {
    Optional<Intern> findByEmailIgnoreCase(String email);


}
