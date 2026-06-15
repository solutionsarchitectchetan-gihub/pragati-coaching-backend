package com.pragati.coaching.repository;

import com.pragati.coaching.model.Student;
import com.pragati.coaching.model.Student.StudentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRollNumber(String rollNumber);
    boolean existsByEmail(String email);
    boolean existsByRollNumber(String rollNumber);

    long countByStatus(StudentStatus status);

    @Query("SELECT s FROM Student s WHERE " +
           "LOWER(s.fullName) LIKE LOWER(CONCAT('%',:q,'%')) OR " +
           "LOWER(s.email)    LIKE LOWER(CONCAT('%',:q,'%')) OR " +
           "LOWER(s.rollNumber) LIKE LOWER(CONCAT('%',:q,'%')) OR " +
           "LOWER(s.course)   LIKE LOWER(CONCAT('%',:q,'%'))")
    Page<Student> search(@Param("q") String query, Pageable pageable);
}
