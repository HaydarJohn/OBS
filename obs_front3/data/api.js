// API functions for sharedData
const api = {
  // Students
  getStudentById: id => sharedData.students.find(s => s.student_id === id),
  getAllStudents: () => sharedData.students,
  addStudent: student => { sharedData.students.push(student); },
  updateStudent: (id, updates) => {
    const s = sharedData.students.find(s => s.student_id === id);
    if (s) Object.assign(s, updates);
  },
  removeStudent: id => { sharedData.students = sharedData.students.filter(s => s.student_id !== id); },
  // Teachers
  getTeacherById: id => sharedData.teachers.find(t => t.teacher_id === id),
  getAllTeachers: () => sharedData.teachers,
  addTeacher: teacher => { sharedData.teachers.push(teacher); },
  updateTeacher: (id, updates) => {
    const t = sharedData.teachers.find(t => t.teacher_id === id);
    if (t) Object.assign(t, updates);
  },
  removeTeacher: id => { sharedData.teachers = sharedData.teachers.filter(t => t.teacher_id !== id); },
  // Admins
  getAdminById: id => sharedData.admins.find(a => a.admin_id === id),
  // Courses
  getCourseById: id => sharedData.courses.find(c => c.course_id === id),
  getAllCourses: () => sharedData.courses,
  addCourse: course => { sharedData.courses.push(course); },
  updateCourse: (id, updates) => {
    const c = sharedData.courses.find(c => c.course_id === id);
    if (c) Object.assign(c, updates);
  },
  removeCourse: id => {
    sharedData.courses = sharedData.courses.filter(c => c.course_id !== id);
    sharedData.enrollments = sharedData.enrollments.filter(e => e.course_id !== id);
    sharedData.grades = sharedData.grades.filter(g => g.course_id !== id);
  },
  // Enrollments
  getEnrollmentsByCourse: course_id => sharedData.enrollments.filter(e => e.course_id === course_id),
  getEnrollmentsByStudent: student_id => sharedData.enrollments.filter(e => e.student_id === student_id),
  enrollStudent: (student_id, course_id, semester_id) => { sharedData.enrollments.push({ student_id, course_id, semester_id }); },
  // Grades
  getGrades: (student_id, course_id) => sharedData.grades.filter(g => g.student_id === student_id && g.course_id === course_id),
  setGrade: (student_id, course_id, category, value) => {
    let g = sharedData.grades.find(g => g.student_id === student_id && g.course_id === course_id && g.category === category);
    if (g) g.value = value;
    else sharedData.grades.push({ student_id, course_id, category, value });
  },
  // Categories
  getCategories: course_id => {
    const c = sharedData.courses.find(c => c.course_id === course_id);
    return c ? c.categories : [];
  },
  addCategory: (course_id, name, weight) => {
    const c = sharedData.courses.find(c => c.course_id === course_id);
    if (c) c.categories.push({ name, weight });
    // Add to all students' grades as 0
    sharedData.enrollments.filter(e => e.course_id === course_id).forEach(e => {
      api.setGrade(e.student_id, course_id, name, 0);
    });
  },
  removeCategory: (course_id, name) => {
    const c = sharedData.courses.find(c => c.course_id === course_id);
    if (c) c.categories = c.categories.filter(cat => cat.name !== name);
    sharedData.grades = sharedData.grades.filter(g => !(g.course_id === course_id && g.category === name));
  }
}; 