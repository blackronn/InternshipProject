package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.entities.concretes.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MentorRepository extends JpaRepository<Mentor,Integer> {
    boolean existsByEmail(String email);
    Optional<Mentor> findMentorByEmailIgnoreCase(String email);
}
