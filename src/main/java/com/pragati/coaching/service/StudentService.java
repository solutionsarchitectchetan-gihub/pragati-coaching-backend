package com.pragati.coaching.service;

import com.pragati.coaching.dto.StudentDto;
import com.pragati.coaching.exception.ResourceNotFoundException;
import com.pragati.coaching.model.Student;
import com.pragati.coaching.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repo;

    public Page<Student> getAll(int page, int size, String sort, String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        if (search != null && !search.isBlank()) {
            return repo.search(search, pageable);
        }
        return repo.findAll(pageable);
    }

    public Student getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id));
    }

    public Student create(StudentDto dto) {
        if (repo.existsByEmail(dto.email()))
            throw new IllegalArgumentException("Email already registered: " + dto.email());
        Student s = toEntity(dto);
        s.setRollNumber(generateRoll());
        return repo.save(s);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Student update(Long id, StudentDto dto) {
        Student s = getById(id);
        s.setFullName(dto.fullName());
        s.setEmail(dto.email());
        s.setPhone(dto.phone());
        s.setCourse(dto.course());
        s.setBatch(dto.batch());
        s.setStandard(dto.standard());
        s.setAddress(dto.address());
        s.setStatus(dto.status() != null ? dto.status() : s.getStatus());
        s.setFeesPaid(dto.feesPaid());
        s.setTotalFees(dto.totalFees());
        s.setParentName(dto.parentName());
        s.setParentPhone(dto.parentPhone());
        return repo.save(s);
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Student not found: " + id);
        repo.deleteById(id);
    }

    public Map<String, Long> stats() {
        Map<String, Long> map = new HashMap<>();
        map.put("total",      repo.count());
        map.put("active",     repo.countByStatus(Student.StudentStatus.ACTIVE));
        map.put("inactive",   repo.countByStatus(Student.StudentStatus.INACTIVE));
        map.put("passedOut",  repo.countByStatus(Student.StudentStatus.PASSED_OUT));
        return map;
    }

    private Student toEntity(StudentDto dto) {
        return Student.builder()
                .fullName(dto.fullName()).email(dto.email()).phone(dto.phone())
                .course(dto.course()).batch(dto.batch()).standard(dto.standard())
                .address(dto.address()).status(dto.status())
                .joinDate(dto.joinDate()).feesPaid(dto.feesPaid()).totalFees(dto.totalFees())
                .parentName(dto.parentName()).parentPhone(dto.parentPhone())
                .build();
    }

    private String generateRoll() {
        long count = repo.count() + 1;
        return "PCC%d%04d".formatted(java.time.Year.now().getValue(), count);
    }
}
