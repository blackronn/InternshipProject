package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InternRepository extends JpaRepository<Intern,Integer> {

    Optional<Intern> findByEmailIgnoreCase(String email);

    boolean existsByEmail(String email);

    @Query("SELECT imr.intern FROM InternMentorRelation imr WHERE imr.mentor.id = :mentorId")
    List<Intern> findInternsByMentorId(@Param("mentorId") Integer mentorId);
    List<Intern> findByIsActiveAndEndDateBefore(int activeStatus, LocalDate date);
}
