package com.example.InternshipProject.repositories;
import com.example.InternshipProject.entities.concretes.Assignment;
import com.example.InternshipProject.entities.concretes.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer>{
    long countByStatusIgnoreCase(String status);
    long countByStatusNotIgnoreCase(String status);

    @Query("SELECT ta FROM Assignment ta WHERE ta.intern.id= :internId ORDER BY ta.createdAt DESC")
    List<Assignment> findAssignmentsByInternId(@Param("internId") Integer internId);

    @Query("SELECT a FROM Assignment a WHERE a.intern.id IN " +
            "(SELECT imr.intern.id FROM InternMentorRelation imr WHERE imr.mentor.id = :mentorId)"+"ORDER BY a.createdAt DESC")
    List<Assignment> findAssignmentsByMentorId(@Param("mentorId") Integer mentorId);

    List<Assignment> findByIntern(Intern intern);


}
