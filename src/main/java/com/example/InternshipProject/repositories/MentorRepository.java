package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor,Integer> {
}
