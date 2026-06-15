package com.pragati.coaching.seed;

import com.pragati.coaching.model.*;
import com.pragati.coaching.model.Student.StudentStatus;
import com.pragati.coaching.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Profile("dev")           // ← runs ONLY in dev (H2 in-memory)
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository    roleRepo;
    private final UserRepository    userRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository  courseRepo;
    private final PasswordEncoder   passwordEncoder;

    @Override
    public void run(String... args) {
        seedRoles();
        seedUsers();
        seedCourses();
        seedStudents();
        log.info("✅ Seed data loaded for Pragati Coaching Center");
    }

    private void seedRoles() {
        if (roleRepo.count() > 0) return;
        roleRepo.saveAll(List.of(
            Role.builder().name("ADMIN")  .description("Full access — manage everything").build(),
            Role.builder().name("TEACHER").description("Manage courses and view students").build(),
            Role.builder().name("STUDENT").description("View own profile and courses").build(),
            Role.builder().name("MANAGER").description("Manage students, fees, admissions").build()
        ));
        log.info("Roles seeded.");
    }

    private void seedUsers() {
        if (userRepo.count() > 0) return;
        var adminRole   = roleRepo.findByName("ADMIN").orElseThrow();
        var teacherRole = roleRepo.findByName("TEACHER").orElseThrow();

        userRepo.saveAll(List.of(
            User.builder()
                .username("admin")
                .email("admin@pragaticoaching.com")
                .password(passwordEncoder.encode("admin123"))
                .fullName("Admin User")
                .role(adminRole)
                .active(true).build(),
            User.builder()
                .username("teacher1")
                .email("teacher@pragaticoaching.com")
                .password(passwordEncoder.encode("teacher123"))
                .fullName("Rajesh Kumar")
                .role(teacherRole)
                .active(true).build()
        ));
        log.info("Users seeded. Admin: admin/admin123");
    }

    private void seedCourses() {
        if (courseRepo.count() > 0) return;
        courseRepo.saveAll(List.of(
            Course.builder()
                .name("IIT-JEE Foundation")
                .description("Comprehensive preparation for IIT JEE Mains & Advanced with expert faculty")
                .duration("2 Years")
                .subjects("Physics, Chemistry, Mathematics")
                .fees(45000.0)
                .eligibility("Class 11 / 12 / Dropper")
                .schedule("Mon–Sat | 7:00 AM – 10:00 AM")
                .instructor("Dr. Amit Sharma")
                .active(true).build(),
            Course.builder()
                .name("NEET Medical")
                .description("Result-oriented NEET preparation with biology, chemistry and physics")
                .duration("2 Years")
                .subjects("Biology, Physics, Chemistry")
                .fees(42000.0)
                .eligibility("Class 11 / 12 / Dropper")
                .schedule("Mon–Sat | 11:00 AM – 2:00 PM")
                .instructor("Dr. Priya Singh")
                .active(true).build(),
            Course.builder()
                .name("Class 10 Board Excellence")
                .description("Board exam mastery with all core subjects — score 95+")
                .duration("1 Year")
                .subjects("Maths, Science, English, Social Studies, Hindi")
                .fees(18000.0)
                .eligibility("Class 9 Pass")
                .schedule("Mon–Sat | 4:00 PM – 7:00 PM")
                .instructor("Mrs. Kavita Verma")
                .active(true).build(),
            Course.builder()
                .name("Class 12 PCM")
                .description("Focused coaching for Class 12 Physics, Chemistry and Mathematics")
                .duration("1 Year")
                .subjects("Physics, Chemistry, Mathematics")
                .fees(22000.0)
                .eligibility("Class 11 Pass")
                .schedule("Mon–Fri | 6:00 PM – 9:00 PM")
                .instructor("Mr. Sunil Gupta")
                .active(true).build(),
            Course.builder()
                .name("Foundation (Class 8–10)")
                .description("Build strong basics in Maths and Science for competitive exams")
                .duration("1 Year")
                .subjects("Maths, Science")
                .fees(14000.0)
                .eligibility("Class 8–10")
                .schedule("Sat–Sun | 9:00 AM – 1:00 PM")
                .instructor("Ms. Neha Joshi")
                .active(true).build()
        ));
        log.info("Courses seeded.");
    }

    private void seedStudents() {
        if (studentRepo.count() > 0) return;
        studentRepo.saveAll(List.of(
            makeStudent("Arjun Mehta",    "arjun@email.com",  "9876543210", "PCC2024001", "IIT-JEE Foundation", "2024-A", "Class 12", StudentStatus.ACTIVE,   45000.0, 45000.0),
            makeStudent("Priya Sharma",   "priya@email.com",  "9876543211", "PCC2024002", "NEET Medical",       "2024-B", "Class 12", StudentStatus.ACTIVE,   20000.0, 42000.0),
            makeStudent("Rahul Singh",    "rahul@email.com",  "9876543212", "PCC2024003", "Class 10 Board",     "2024-A", "Class 10", StudentStatus.ACTIVE,   18000.0, 18000.0),
            makeStudent("Ananya Patel",   "ananya@email.com", "9876543213", "PCC2024004", "IIT-JEE Foundation", "2024-A", "Dropper",  StudentStatus.ACTIVE,   45000.0, 45000.0),
            makeStudent("Vikram Yadav",   "vikram@email.com", "9876543214", "PCC2024005", "Class 12 PCM",       "2024-C", "Class 12", StudentStatus.ACTIVE,   10000.0, 22000.0),
            makeStudent("Sneha Joshi",    "sneha@email.com",  "9876543215", "PCC2024006", "NEET Medical",       "2024-B", "Class 11", StudentStatus.INACTIVE,  0.0,    42000.0),
            makeStudent("Karan Gupta",    "karan@email.com",  "9876543216", "PCC2024007", "Foundation",         "2024-D", "Class 9",  StudentStatus.ACTIVE,   14000.0, 14000.0)
        ));
        log.info("Students seeded.");
    }

    private Student makeStudent(String name, String email, String phone, String roll,
                                String course, String batch, String std, StudentStatus status,
                                double paid, double total) {
        return Student.builder()
                .fullName(name).email(email).phone(phone).rollNumber(roll)
                .course(course).batch(batch).standard(std).status(status)
                .joinDate(LocalDate.of(2024, 1, 15))
                .feesPaid(paid).totalFees(total)
                .parentName("Parent of " + name.split(" ")[0])
                .parentPhone("98765" + (int)(Math.random()*99999))
                .build();
    }
}
