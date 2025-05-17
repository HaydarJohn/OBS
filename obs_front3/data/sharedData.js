// Simulated backend data for all panels
window.sharedData = {
  students: [
    { student_id: 1, first_name: 'John', last_name: 'Doe', email: 'john@example.com', phone_num: '555-123-4567', address: '123 Main St', date_of_birth: '2000-01-01', gender: 'Male', tckn: '12345678901', major: 'Computer Science', enrollment_date: '2022-08-22', graduation_date: '2026-06-15', status: 'Active', advisor_id: 101 }
  ],
  teachers: [
    { teacher_id: 101, first_name: 'Dr. Brown', last_name: 'Smith', email: 'brown.smith@example.com', phone_num: '555-987-6543', address: '456 Faculty Ave', office_location: 'B-201', department: 'Computer Science', hire_date: '2015-09-01', salary: '12000' }
  ],
  admins: [
    { admin_id: 1, first_name: 'Alice', last_name: 'Admin', email: 'alice.admin@example.com', role: 'Super Admin', permissions: 'All' }
  ],
  courses: [
    { course_id: 201, code: 'CS101', title: 'Introduction to Programming', department: 'Computer Science', teacher_id: 101, prerequisites: [], categories: [ { name: 'Midterm', weight: 0.3 }, { name: 'Final', weight: 0.5 }, { name: 'Assignment', weight: 0.2 } ] },
    { course_id: 202, code: 'CS202', title: 'Data Structures', department: 'Computer Science', teacher_id: 101, prerequisites: ['CS101'], categories: [ { name: 'Midterm', weight: 0.3 }, { name: 'Final', weight: 0.5 }, { name: 'Assignment', weight: 0.2 } ] }
  ],
  enrollments: [
    { student_id: 1, course_id: 201, semester_id: 1 },
    { student_id: 1, course_id: 202, semester_id: 1 }
  ],
  grades: [
    { student_id: 1, course_id: 201, category: 'Midterm', value: 85 },
    { student_id: 1, course_id: 201, category: 'Final', value: 90 },
    { student_id: 1, course_id: 201, category: 'Assignment', value: 88 },
    { student_id: 1, course_id: 202, category: 'Midterm', value: 78 },
    { student_id: 1, course_id: 202, category: 'Final', value: 85 },
    { student_id: 1, course_id: 202, category: 'Assignment', value: 80 }
  ],
  semesters: [
    { semester_id: 1, name: '2024-2025 Spring' }
  ]
}; 