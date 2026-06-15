-- ============================================================
-- V5 : Seed Students — 20 realistic sample records  (Prod — MySQL)
-- ============================================================

INSERT IGNORE INTO students (full_name, email, phone, roll_number, course, batch, standard,
                              address, status, join_date, fees_paid, total_fees,
                              parent_name, parent_phone, created_at, updated_at)
VALUES
-- ── IIT-JEE Foundation ────────────────────────────────────────────────────────
('Arjun Mehta',      'arjun.mehta@student.com',    '9876543201', 'PCC2024001',
 'IIT-JEE Foundation', 'Batch-A Morning', 'Class 12',
 '12 MG Road, Jaipur, Rajasthan 302001', 'ACTIVE', '2024-01-15', 45000, 45000,
 'Suresh Mehta', '9876543001', NOW(), NOW()),

('Ananya Patel',     'ananya.patel@student.com',   '9876543202', 'PCC2024002',
 'IIT-JEE Foundation', 'Batch-A Morning', 'Dropper',
 '45 Vaishali Nagar, Jaipur, Rajasthan 302021', 'ACTIVE', '2024-01-15', 45000, 45000,
 'Ramesh Patel', '9876543002', NOW(), NOW()),

('Rohit Sharma',     'rohit.sharma@student.com',   '9876543203', 'PCC2024003',
 'IIT-JEE Foundation', 'Batch-B Evening', 'Class 11',
 '7 Tonk Road, Jaipur, Rajasthan 302015', 'ACTIVE', '2024-02-01', 25000, 45000,
 'Vinod Sharma', '9876543003', NOW(), NOW()),

('Kavya Nair',       'kavya.nair@student.com',     '9876543204', 'PCC2024004',
 'IIT-JEE Foundation', 'Batch-A Morning', 'Class 12',
 '89 C-Scheme, Jaipur, Rajasthan 302001', 'ACTIVE', '2024-01-20', 30000, 45000,
 'Krishnan Nair', '9876543004', NOW(), NOW()),

-- ── NEET Medical ──────────────────────────────────────────────────────────────
('Priya Singh',      'priya.singh@student.com',    '9876543205', 'PCC2024005',
 'NEET Medical', 'Batch-C Afternoon', 'Class 12',
 '23 Bani Park, Jaipur, Rajasthan 302016', 'ACTIVE', '2024-01-15', 42000, 42000,
 'Rajendra Singh', '9876543005', NOW(), NOW()),

('Aditya Kumar',     'aditya.kumar@student.com',   '9876543206', 'PCC2024006',
 'NEET Medical', 'Batch-C Afternoon', 'Dropper',
 '56 Mansarovar, Jaipur, Rajasthan 302020', 'ACTIVE', '2024-01-18', 21000, 42000,
 'Rakesh Kumar', '9876543006', NOW(), NOW()),

('Sneha Joshi',      'sneha.joshi@student.com',    '9876543207', 'PCC2024007',
 'NEET Medical', 'Batch-C Afternoon', 'Class 11',
 '34 Pratap Nagar, Jaipur, Rajasthan 302033', 'INACTIVE', '2024-02-10', 0, 42000,
 'Mohan Joshi', '9876543007', NOW(), NOW()),

('Riya Verma',       'riya.verma@student.com',     '9876543208', 'PCC2024008',
 'NEET Medical', 'Batch-D Weekend', 'Class 12',
 '11 Jawahar Nagar, Jaipur, Rajasthan 302004', 'ACTIVE', '2024-03-01', 42000, 42000,
 'Sunil Verma', '9876543008', NOW(), NOW()),

-- ── Class 10 Board Excellence ─────────────────────────────────────────────────
('Rahul Singh',      'rahul.singh@student.com',    '9876543209', 'PCC2024009',
 'Class 10 Board Excellence', 'Board-A', 'Class 10',
 '78 Shyam Nagar, Jaipur, Rajasthan 302019', 'ACTIVE', '2024-04-01', 18000, 18000,
 'Bharat Singh', '9876543009', NOW(), NOW()),

('Meera Gupta',      'meera.gupta@student.com',    '9876543210', 'PCC2024010',
 'Class 10 Board Excellence', 'Board-A', 'Class 10',
 '5 Raja Park, Jaipur, Rajasthan 302004', 'ACTIVE', '2024-04-01', 9000, 18000,
 'Deepak Gupta', '9876543010', NOW(), NOW()),

('Yash Trivedi',     'yash.trivedi@student.com',   '9876543211', 'PCC2024011',
 'Class 10 Board Excellence', 'Board-B', 'Class 10',
 '90 Malviya Nagar, Jaipur, Rajasthan 302017', 'ACTIVE', '2024-04-05', 18000, 18000,
 'Ashok Trivedi', '9876543011', NOW(), NOW()),

-- ── Class 12 PCM ──────────────────────────────────────────────────────────────
('Vikram Yadav',     'vikram.yadav@student.com',   '9876543212', 'PCC2024012',
 'Class 12 PCM', 'PCM-Evening', 'Class 12',
 '22 Nirman Nagar, Jaipur, Rajasthan 302019', 'ACTIVE', '2024-03-15', 10000, 22000,
 'Hemant Yadav', '9876543012', NOW(), NOW()),

('Pooja Agarwal',    'pooja.agarwal@student.com',  '9876543213', 'PCC2024013',
 'Class 12 PCM', 'PCM-Evening', 'Class 12',
 '67 Sodala, Jaipur, Rajasthan 302019', 'ACTIVE', '2024-03-15', 22000, 22000,
 'Pankaj Agarwal', '9876543013', NOW(), NOW()),

('Nikhil Bansal',    'nikhil.bansal@student.com',  '9876543214', 'PCC2024014',
 'Class 12 PCM', 'PCM-Morning', 'Class 11',
 '14 Gopalpura, Jaipur, Rajasthan 302018', 'DROPPED', '2024-02-01', 5000, 22000,
 'Manoj Bansal', '9876543014', NOW(), NOW()),

-- ── Foundation Class 8-10 ─────────────────────────────────────────────────────
('Karan Gupta',      'karan.gupta@student.com',    '9876543215', 'PCC2024015',
 'Foundation Class 8-10', 'Foundation-Weekend', 'Class 9',
 '33 Durgapura, Jaipur, Rajasthan 302018', 'ACTIVE', '2024-06-01', 14000, 14000,
 'Sanjay Gupta', '9876543015', NOW(), NOW()),

('Tanya Saxena',     'tanya.saxena@student.com',   '9876543216', 'PCC2024016',
 'Foundation Class 8-10', 'Foundation-Weekend', 'Class 8',
 '45 Sanganer, Jaipur, Rajasthan 303902', 'ACTIVE', '2024-06-01', 7000, 14000,
 'Amit Saxena', '9876543016', NOW(), NOW()),

('Dev Chauhan',      'dev.chauhan@student.com',    '9876543217', 'PCC2024017',
 'Foundation Class 8-10', 'Foundation-Weekend', 'Class 10',
 '8 Sitapura, Jaipur, Rajasthan 302022', 'ACTIVE', '2024-06-05', 14000, 14000,
 'Rajesh Chauhan', '9876543017', NOW(), NOW()),

-- ── Crash Course ──────────────────────────────────────────────────────────────
('Siddharth Jain',   'siddharth.jain@student.com', '9876543218', 'PCC2024018',
 'Crash Course JEE Mains', 'Crash-Jan', 'Dropper',
 '19 Muhana, Jaipur, Rajasthan 302029', 'ACTIVE', '2024-01-05', 12000, 12000,
 'Vinay Jain', '9876543018', NOW(), NOW()),

('Muskan Sharma',    'muskan.sharma@student.com',  '9876543219', 'PCC2024019',
 'Crash Course JEE Mains', 'Crash-Nov', 'Class 12',
 '52 Vidhyadhar Nagar, Jaipur, Rajasthan 302039', 'PASSED_OUT', '2023-11-01', 12000, 12000,
 'Govind Sharma', '9876543019', NOW(), NOW()),

('Harsh Mishra',     'harsh.mishra@student.com',   '9876543220', 'PCC2024020',
 'NEET Medical', 'Batch-D Weekend', 'Class 12',
 '3 Adarsh Nagar, Jaipur, Rajasthan 302004', 'ACTIVE', '2024-05-10', 42000, 42000,
 'Kamlesh Mishra', '9876543020', NOW(), NOW());
