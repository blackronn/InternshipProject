package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
    List<Evaluation> findByInternId(int internId);

    List<Evaluation> findByMentorId(int mentorId);

    Optional<Evaluation> findByMentor_IdAndIntern_Id(int mentorId, int internId);


}