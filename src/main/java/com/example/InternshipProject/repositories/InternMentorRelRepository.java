package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.entities.concretes.InternMentorRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternMentorRelRepository extends JpaRepository<InternMentorRelation,Integer> {
    Optional<InternMentorRelation> findByIntern(Intern intern);
}
