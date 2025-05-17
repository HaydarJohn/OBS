// Use sharedData and api.js for all data operations
// Assume data/api.js and data/sharedData.js are loaded before this file

// Mock data for testing
let students = [
    { id: 1, name: 'John Doe', email: 'john@example.com', department: 'Computer Science' },
    { id: 2, name: 'Jane Smith', email: 'jane@example.com', department: 'Mathematics' }
];

let teachers = [
    { id: 1, name: 'Dr. Brown', email: 'brown@example.com', department: 'Computer Science' },
    { id: 2, name: 'Prof. Wilson', email: 'wilson@example.com', department: 'Mathematics' }
];

let courses = [
    { code: 'CS101', title: 'Introduction to Programming', department: 'Computer Science', teacher: 'Dr. Brown' },
    { code: 'MATH201', title: 'Calculus I', department: 'Mathematics', teacher: 'Prof. Wilson' }
];

let adminInfo = {
    first_name: "Alice",
    last_name: "Admin",
    email: "alice.admin@example.com",
    role: "Super Admin",
    permissions: "All"
};

// Sidebar navigation logic
function showSection(sectionId) {
    const sections = ['adminInfo', 'students', 'teachers', 'courses', 'enrollments'];
    // Hide dashboard by default
    const dashboard = document.getElementById('adminDashboard');
    if (dashboard) dashboard.style.display = 'none';
    sections.forEach(id => {
        const el = document.getElementById(id);
        if (el) el.style.display = 'none';
    });
    // Show the selected section or dashboard
    if (sectionId === 'dashboard') {
        if (dashboard) dashboard.style.display = 'flex';
    } else {
        const el = document.getElementById(sectionId);
        if (el) el.style.display = 'block';
    }
    // Update sidebar active class
    document.querySelectorAll('.sidebar-menu li').forEach(li => li.classList.remove('active'));
    const activeLi = document.querySelector(`.sidebar-menu a[href="#${sectionId}"]`);
    if (activeLi && activeLi.parentElement) activeLi.parentElement.classList.add('active');
    if (sectionId === 'adminInfo') fillAdminInfo();
    if (sectionId === 'students') loadStudentsTable();
    if (sectionId === 'teachers') loadTeachersTable();
    if (sectionId === 'courses') loadCoursesTable();
    if (sectionId === 'enrollments') loadEnrollmentsTable();
}

document.addEventListener('DOMContentLoaded', () => {
    // Hide all sections except dashboard by default
    showSection('dashboard');
    // Sidebar click events
    document.querySelectorAll('.sidebar-menu a').forEach(link => {
        link.addEventListener('click', function(e) {
            const href = this.getAttribute('href');
            if (href && href.startsWith('#')) {
                e.preventDefault();
                showSection(href.substring(1));
            }
        });
    });
    updateDashboardCounts();
});

// Student Management
function loadStudentsTable() {
    const tbody = document.getElementById('studentsTableBody');
    tbody.innerHTML = '';
    api.getAllStudents().forEach(student => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${student.student_id}</td>
            <td>${student.first_name} ${student.last_name}</td>
            <td>${student.email}</td>
            <td>${student.major}</td>
            <td>
                <button onclick="editStudent(${student.student_id})" class="btn btn-primary">Edit</button>
                <button onclick="deleteStudent(${student.student_id})" class="btn btn-danger">Delete</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

// Modal helpers
function openModal(html) {
    document.getElementById('modalContent').innerHTML = html;
    document.getElementById('modalOverlay').style.display = 'flex';
}
function closeModal() {
    document.getElementById('modalOverlay').style.display = 'none';
}

function showAddStudentForm() {
    openModal(`
        <h2>Add New Student</h2>
        <div class="form-group">
            <label>First Name</label>
            <input type="text" id="newStudentFirstName" required>
        </div>
        <div class="form-group">
            <label>Last Name</label>
            <input type="text" id="newStudentLastName" required>
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" id="newStudentEmail" required>
        </div>
        <div class="form-group">
            <label>Major</label>
            <input type="text" id="newStudentMajor" required>
        </div>
        <button class="btn btn-primary" onclick="addStudentFromModal()">Add Student</button>
    `);
}
function addStudentFromModal() {
    const first_name = document.getElementById('newStudentFirstName').value.trim();
    const last_name = document.getElementById('newStudentLastName').value.trim();
    const email = document.getElementById('newStudentEmail').value.trim();
    const major = document.getElementById('newStudentMajor').value.trim();
    if (!first_name || !last_name || !email || !major) return alert('All fields are required!');
    const newId = Math.max(0, ...api.getAllStudents().map(s => s.student_id)) + 1;
    api.addStudent({ student_id: newId, first_name, last_name, email, major });
    closeModal();
    loadStudentsTable();
}

function editStudent(id) {
    const student = api.getStudentById(id);
    if (!student) return;
    openModal(`
        <h2>Edit Student</h2>
        <div class="form-group">
            <label>First Name</label>
            <input type="text" id="editStudentFirstName" value="${student.first_name}" required>
        </div>
        <div class="form-group">
            <label>Last Name</label>
            <input type="text" id="editStudentLastName" value="${student.last_name}" required>
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" id="editStudentEmail" value="${student.email}" required>
        </div>
        <div class="form-group">
            <label>Major</label>
            <input type="text" id="editStudentMajor" value="${student.major}" required>
        </div>
        <button class="btn btn-primary" onclick="saveStudentEdit(${id})">Save</button>
    `);
}
function saveStudentEdit(id) {
    const updates = {
        first_name: document.getElementById('editStudentFirstName').value.trim(),
        last_name: document.getElementById('editStudentLastName').value.trim(),
        email: document.getElementById('editStudentEmail').value.trim(),
        major: document.getElementById('editStudentMajor').value.trim()
    };
    api.updateStudent(id, updates);
    closeModal();
    loadStudentsTable();
}
function deleteStudent(id) {
    openModal(`
        <h2>Delete Student</h2>
        <p>Are you sure you want to delete this student?</p>
        <button class="btn btn-danger" onclick="confirmDeleteStudent(${id})">Delete</button>
        <button class="btn btn-primary" onclick="closeModal()">Cancel</button>
    `);
}
function confirmDeleteStudent(id) {
    api.removeStudent(id);
    closeModal();
    loadStudentsTable();
}

// Teacher Management
function loadTeachersTable() {
    const tbody = document.getElementById('teachersTableBody');
    tbody.innerHTML = '';
    api.getAllTeachers().forEach(teacher => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${teacher.teacher_id}</td>
            <td>${teacher.first_name} ${teacher.last_name}</td>
            <td>${teacher.email}</td>
            <td>${teacher.department}</td>
            <td>
                <button onclick="editTeacher(${teacher.teacher_id})" class="btn btn-primary">Edit</button>
                <button onclick="deleteTeacher(${teacher.teacher_id})" class="btn btn-danger">Delete</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

function showAddTeacherForm() {
    openModal(`
        <h2>Add New Teacher</h2>
        <div class="form-group">
            <label>First Name</label>
            <input type="text" id="newTeacherFirstName" required>
        </div>
        <div class="form-group">
            <label>Last Name</label>
            <input type="text" id="newTeacherLastName" required>
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" id="newTeacherEmail" required>
        </div>
        <div class="form-group">
            <label>Department</label>
            <input type="text" id="newTeacherDepartment" required>
        </div>
        <button class="btn btn-primary" onclick="addTeacherFromModal()">Add Teacher</button>
    `);
}
function addTeacherFromModal() {
    const first_name = document.getElementById('newTeacherFirstName').value.trim();
    const last_name = document.getElementById('newTeacherLastName').value.trim();
    const email = document.getElementById('newTeacherEmail').value.trim();
    const department = document.getElementById('newTeacherDepartment').value.trim();
    if (!first_name || !last_name || !email || !department) return alert('All fields are required!');
    const newId = Math.max(0, ...api.getAllTeachers().map(t => t.teacher_id)) + 1;
    api.addTeacher({ teacher_id: newId, first_name, last_name, email, department });
    closeModal();
    loadTeachersTable();
}

function editTeacher(id) {
    const teacher = api.getTeacherById(id);
    if (!teacher) return;
    openModal(`
        <h2>Edit Teacher</h2>
        <div class="form-group">
            <label>First Name</label>
            <input type="text" id="editTeacherFirstName" value="${teacher.first_name}" required>
        </div>
        <div class="form-group">
            <label>Last Name</label>
            <input type="text" id="editTeacherLastName" value="${teacher.last_name}" required>
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" id="editTeacherEmail" value="${teacher.email}" required>
        </div>
        <div class="form-group">
            <label>Department</label>
            <input type="text" id="editTeacherDepartment" value="${teacher.department}" required>
        </div>
        <button class="btn btn-primary" onclick="saveTeacherEdit(${id})">Save</button>
    `);
}
function saveTeacherEdit(id) {
    const updates = {
        first_name: document.getElementById('editTeacherFirstName').value.trim(),
        last_name: document.getElementById('editTeacherLastName').value.trim(),
        email: document.getElementById('editTeacherEmail').value.trim(),
        department: document.getElementById('editTeacherDepartment').value.trim()
    };
    api.updateTeacher(id, updates);
    closeModal();
    loadTeachersTable();
}
function deleteTeacher(id) {
    openModal(`
        <h2>Delete Teacher</h2>
        <p>Are you sure you want to delete this teacher?</p>
        <button class="btn btn-danger" onclick="confirmDeleteTeacher(${id})">Delete</button>
        <button class="btn btn-primary" onclick="closeModal()">Cancel</button>
    `);
}
function confirmDeleteTeacher(id) {
    api.removeTeacher(id);
    closeModal();
    loadTeachersTable();
}

// Course Management
function loadCoursesTable() {
    const tbody = document.getElementById('coursesTableBody');
    tbody.innerHTML = '';
    api.getAllCourses().forEach(course => {
        const teacher = api.getTeacherById(course.teacher_id);
        const teacherName = teacher ? teacher.first_name + ' ' + teacher.last_name : '';
        const prereqs = (course.prerequisites && course.prerequisites.length > 0) ? course.prerequisites.join(', ') : '-';
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${course.code}</td>
            <td>${course.title}</td>
            <td>${course.department}</td>
            <td>${teacherName}</td>
            <td>${prereqs}</td>
            <td>
                <button onclick="editCourse(${course.course_id})" class="btn btn-primary">Edit</button>
                <button onclick="deleteCourse(${course.course_id})" class="btn btn-danger">Delete</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

function showAddCourseForm() {
    const allCourses = api.getAllCourses();
    openModal(`
        <h2>Add New Course</h2>
        <div class="form-group">
            <label>Code</label>
            <input type="text" id="newCourseCode" required>
        </div>
        <div class="form-group">
            <label>Title</label>
            <input type="text" id="newCourseTitle" required>
        </div>
        <div class="form-group">
            <label>Department</label>
            <input type="text" id="newCourseDepartment" required>
        </div>
        <div class="form-group">
            <label>Teacher ID</label>
            <input type="number" id="newCourseTeacherId" required>
        </div>
        <div class="form-group">
            <label>Prerequisites</label>
            <div id="prereqTags"></div>
            <input type="text" id="prereqSearch" placeholder="Type to search..." style="width:100%; margin-bottom:5px;">
            <div id="prereqList" style="max-height:100px; overflow-y:auto; border:1px solid #ccc;"></div>
        </div>
        <button class="btn btn-primary" onclick="addCourseFromModal()">Add Course</button>
    `);
    setupPrereqSelector(allCourses, []);
}

function editCourse(id) {
    const course = api.getCourseById(id);
    if (!course) return;
    const allCourses = api.getAllCourses().filter(c => c.course_id !== id);
    openModal(`
        <h2>Edit Course</h2>
        <div class="form-group">
            <label>Code</label>
            <input type="text" id="editCourseCode" value="${course.code}" required disabled>
        </div>
        <div class="form-group">
            <label>Title</label>
            <input type="text" id="editCourseTitle" value="${course.title}" required>
        </div>
        <div class="form-group">
            <label>Department</label>
            <input type="text" id="editCourseDepartment" value="${course.department}" required>
        </div>
        <div class="form-group">
            <label>Teacher ID</label>
            <input type="number" id="editCourseTeacherId" value="${course.teacher_id}" required>
        </div>
        <div class="form-group">
            <label>Prerequisites</label>
            <div id="prereqTags"></div>
            <input type="text" id="prereqSearch" placeholder="Type to search..." style="width:100%; margin-bottom:5px;">
            <div id="prereqList" style="max-height:100px; overflow-y:auto; border:1px solid #ccc;"></div>
        </div>
        <button class="btn btn-primary" onclick="saveCourseEdit(${id})">Save</button>
    `);
    setupPrereqSelector(allCourses, course.prerequisites);
}

// Helper for both add/edit
function setupPrereqSelector(allCourses, initialSelected) {
    let selected = [...initialSelected];
    const tagsDiv = document.getElementById('prereqTags');
    const searchInput = document.getElementById('prereqSearch');
    const listDiv = document.getElementById('prereqList');
    function renderTags() {
        tagsDiv.innerHTML = selected.map(code => {
            const c = allCourses.find(x => x.code === code);
            return `<span style='display:inline-block; background:#e0e0e0; border-radius:12px; padding:2px 8px; margin:2px;'>${code}${c ? ' - ' + c.title : ''} <b style='cursor:pointer;color:red;' onclick='this.parentElement.remove();removePrereqTag("${code}")'>&times;</b></span>`;
        }).join('');
    }
    function renderList() {
        const q = searchInput.value.toLowerCase();
        listDiv.innerHTML = allCourses.filter(c => !selected.includes(c.code) && (c.code.toLowerCase().includes(q) || c.title.toLowerCase().includes(q))).map(c =>
            `<div style='padding:2px 6px; cursor:pointer;' onclick='addPrereqTag("${c.code}")'>${c.code} - ${c.title}</div>`
        ).join('');
    }
    window.addPrereqTag = code => { selected.push(code); renderTags(); renderList(); };
    window.removePrereqTag = code => { selected = selected.filter(x => x !== code); renderTags(); renderList(); };
    searchInput.oninput = renderList;
    renderTags();
    renderList();
    // Save selected to a hidden input for modal save
    window.getSelectedPrereqs = () => selected;
}

function addCourseFromModal() {
    const code = document.getElementById('newCourseCode').value.trim();
    const title = document.getElementById('newCourseTitle').value.trim();
    const department = document.getElementById('newCourseDepartment').value.trim();
    const teacher_id = parseInt(document.getElementById('newCourseTeacherId').value);
    const prerequisites = window.getSelectedPrereqs ? window.getSelectedPrereqs() : [];
    if (!code || !title || !department || !teacher_id) return alert('All fields are required!');
    const newId = Math.max(0, ...api.getAllCourses().map(c => c.course_id)) + 1;
    api.addCourse({ course_id: newId, code, title, department, teacher_id, categories: [], prerequisites });
    closeModal();
    loadCoursesTable();
}
function saveCourseEdit(id) {
    const updates = {
        title: document.getElementById('editCourseTitle').value.trim(),
        department: document.getElementById('editCourseDepartment').value.trim(),
        teacher_id: parseInt(document.getElementById('editCourseTeacherId').value),
        prerequisites: window.getSelectedPrereqs ? window.getSelectedPrereqs() : []
    };
    api.updateCourse(id, updates);
    closeModal();
    loadCoursesTable();
}
function deleteCourse(id) {
    openModal(`
        <h2>Delete Course</h2>
        <p>Are you sure you want to delete this course?</p>
        <button class="btn btn-danger" onclick="confirmDeleteCourse(${id})">Delete</button>
        <button class="btn btn-primary" onclick="closeModal()">Cancel</button>
    `);
}
function confirmDeleteCourse(id) {
    api.removeCourse(id);
    closeModal();
    loadCoursesTable();
}

function fillAdminInfo() {
    const admin = api.getAdminById(1);
    document.getElementById('adminFirstName').value = admin.first_name;
    document.getElementById('adminLastName').value = admin.last_name;
    document.getElementById('adminEmail').value = admin.email;
    document.getElementById('adminRole').value = admin.role;
    document.getElementById('adminPermissions').value = admin.permissions;
}

function saveAdminInfo(event) {
    event.preventDefault();
    const admin = api.getAdminById(1);
    admin.email = document.getElementById('adminEmail').value.trim();
    alert('Your information has been saved!');
    return false;
}

function showEnrollStudentForm() {
    const courses = api.getAllCourses();
    openModal(`
        <h2>Enroll Student in Course</h2>
        <div class="form-group">
            <label>Student ID</label>
            <input type="text" id="enrollStudentIdInput" placeholder="Enter student ID" style="width:100%" required inputmode="numeric" pattern="[0-9]*">
        </div>
        <div class="form-group">
            <label>Course</label>
            <select id="enrollCourseSelect" style="width:100%">${courses.map(c => `<option value='${c.course_id}'>${c.code} - ${c.title}</option>`).join('')}</select>
        </div>
        <button class="btn btn-primary" onclick="enrollStudentInCourse()">Enroll</button>
    `);
}

function enrollStudentInCourse() {
    const student_id = parseInt(document.getElementById('enrollStudentIdInput').value);
    const course_id = parseInt(document.getElementById('enrollCourseSelect').value);
    const semester_id = 1;
    const course = api.getCourseById(course_id);
    // Student ID validation
    const student = api.getStudentById(student_id);
    if (!student) {
        alert('Student ID not found!');
        return;
    }
    // Prerequisite check
    if (course.prerequisites && course.prerequisites.length > 0) {
        // Check if student has completed all prerequisites
        const completedCourses = api.getEnrollmentsByStudent(student_id)
            .map(e => api.getCourseById(e.course_id)?.code)
            .filter(Boolean);
        const missing = course.prerequisites.filter(prereqCode => !completedCourses.includes(prereqCode));
        if (missing.length > 0) {
            alert('Cannot enroll: Student is missing prerequisites: ' + missing.join(', '));
            return;
        }
    }
    api.enrollStudent(student_id, course_id, semester_id);
    closeModal();
    loadStudentsTable();
    loadCoursesTable();
    alert('Student enrolled in course!');
}

function loadEnrollmentsTable() {
    const tbody = document.getElementById('enrollmentsTableBody');
    tbody.innerHTML = '';
    api.getAllCourses(); // ensure courses loaded
    const enrollments = sharedData.enrollments;
    enrollments.forEach(enr => {
        const student = api.getStudentById(enr.student_id);
        const course = api.getCourseById(enr.course_id);
        const semester = sharedData.semesters.find(s => s.semester_id === enr.semester_id);
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${enr.student_id}</td>
            <td>${student ? student.first_name + ' ' + student.last_name : ''}</td>
            <td>${course ? course.code + ' - ' + course.title : ''}</td>
            <td>${semester ? semester.name : enr.semester_id}</td>
            <td><button class="btn btn-danger" onclick="deleteEnrollment(${enr.student_id}, ${enr.course_id}, ${enr.semester_id})">Delete</button></td>
        `;
        tbody.appendChild(row);
    });
}

function deleteEnrollment(student_id, course_id, semester_id) {
    sharedData.enrollments = sharedData.enrollments.filter(e => !(e.student_id === student_id && e.course_id === course_id && e.semester_id === semester_id));
    loadEnrollmentsTable();
}

// Dashboard count updater
function updateDashboardCounts() {
    const studentCount = api.getAllStudents().length;
    const teacherCount = api.getAllTeachers().length;
    const courseCount = api.getAllCourses().length;
    const studentCountEl = document.getElementById('studentCount');
    const teacherCountEl = document.getElementById('teacherCount');
    const courseCountEl = document.getElementById('courseCount');
    if (studentCountEl) studentCountEl.textContent = studentCount;
    if (teacherCountEl) teacherCountEl.textContent = teacherCount;
    if (courseCountEl) courseCountEl.textContent = courseCount;
}