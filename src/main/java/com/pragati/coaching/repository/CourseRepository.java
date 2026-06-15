package com.pragati.coaching.repository;

import com.pragati.coaching.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByActiveTrue();
}
