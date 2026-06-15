-- ============================================================
-- V4 : Seed Courses  (Prod — MySQL)
-- ============================================================

INSERT IGNORE INTO courses (name, description, duration, subjects, fees, eligibility, schedule, instructor, active, created_at)
VALUES
    ('IIT-JEE Foundation',
     'Comprehensive preparation for IIT JEE Mains and Advanced with expert faculty. Covers complete syllabus with weekly tests and doubt sessions.',
     '2 Years', 'Physics, Chemistry, Mathematics',
     45000.00, 'Class 11 / 12 / Dropper',
     'Mon-Sat | 7:00 AM - 10:00 AM',
     'Dr. Amit Sharma', 1, NOW()),

    ('NEET Medical',
     'Result-oriented NEET preparation with biology, chemistry and physics. Includes lab practicals, mock tests and personal mentoring.',
     '2 Years', 'Biology, Physics, Chemistry',
     42000.00, 'Class 11 / 12 / Dropper',
     'Mon-Sat | 11:00 AM - 2:00 PM',
     'Dr. Priya Singh', 1, NOW()),

    ('Class 10 Board Excellence',
     'Board exam mastery covering all core subjects. Score 95+ with our proven methodology and regular revision tests.',
     '1 Year', 'Maths, Science, English, Social Studies, Hindi',
     18000.00, 'Class 9 Pass',
     'Mon-Sat | 4:00 PM - 7:00 PM',
     'Mrs. Kavita Verma', 1, NOW()),

    ('Class 12 PCM',
     'Focused coaching for Class 12 Physics, Chemistry and Mathematics. Board + engineering entrance preparation together.',
     '1 Year', 'Physics, Chemistry, Mathematics',
     22000.00, 'Class 11 Pass',
     'Mon-Fri | 6:00 PM - 9:00 PM',
     'Mr. Sunil Gupta', 1, NOW()),

    ('Foundation Class 8-10',
     'Build strong conceptual basics in Maths and Science for competitive exams. Early preparation for JEE/NEET.',
     '1 Year', 'Maths, Science',
     14000.00, 'Class 8 / 9 / 10',
     'Sat-Sun | 9:00 AM - 1:00 PM',
     'Ms. Neha Joshi', 1, NOW()),

    ('Class 12 PCB',
     'Complete Biology, Chemistry and Physics coaching for Class 12 boards and NEET foundation.',
     '1 Year', 'Biology, Chemistry, Physics',
     20000.00, 'Class 11 Pass',
     'Mon-Fri | 3:00 PM - 6:00 PM',
     'Dr. Priya Singh', 1, NOW()),

    ('Crash Course JEE Mains',
     'Intensive 3-month crash course covering the most important topics. Ideal for final revision before exam.',
     '3 Months', 'Physics, Chemistry, Mathematics',
     12000.00, 'Class 12 / Dropper',
     'Daily | 8:00 AM - 12:00 PM',
     'Dr. Amit Sharma', 1, NOW());
