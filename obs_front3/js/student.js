// Use sharedData and api.js for all data operations
// Assume data/api.js and data/sharedData.js are loaded before this file

let studentId = 1; // Simulate logged-in student (John Doe)

function getStudentInfo() {
    return api.getStudentById(studentId);
}

function getAdvisorName(studentInfo) {
    const advisor = api.getTeacherById(studentInfo.advisor_id);
    return advisor ? advisor.first_name + ' ' + advisor.last_name : '';
}

function getStudentCourses() {
    const enrollments = api.getEnrollmentsByStudent(studentId);
    return enrollments.map(enrollment => {
        const course = api.getCourseById(enrollment.course_id);
        const teacher = api.getTeacherById(course.teacher_id);
        return {
            ...course,
            teacher: teacher ? teacher.first_name + ' ' + teacher.last_name : '',
            grades: getCourseGrades(studentId, course.course_id),
            credits: course.credits || 0
        };
    });
}

function getCourseGrades(studentId, courseId) {
    const gradesArr = api.getGrades(studentId, courseId);
    const grades = {};
    gradesArr.forEach(g => {
        grades[g.category.toLowerCase()] = g.value;
    });
    return grades;
}

function fillStudentInfo() {
    const studentInfo = getStudentInfo();
    document.getElementById('studentFirstName').value = studentInfo.first_name;
    document.getElementById('studentLastName').value = studentInfo.last_name;
    document.getElementById('studentEmail').value = studentInfo.email;
    document.getElementById('studentPhone').value = studentInfo.phone_num;
    document.getElementById('studentAddress').value = studentInfo.address;
    document.getElementById('studentDOB').value = studentInfo.date_of_birth;
    document.getElementById('studentGender').value = studentInfo.gender;
    document.getElementById('studentTCKN').value = studentInfo.tckn;
    document.getElementById('studentMajor').value = studentInfo.major;
    document.getElementById('studentEnrollmentDate').value = studentInfo.enrollment_date;
    document.getElementById('studentGraduationDate').value = studentInfo.graduation_date;
    document.getElementById('studentStatus').value = studentInfo.status;
    document.getElementById('studentAdvisor').value = getAdvisorName(studentInfo);
}

function saveStudentInfo(event) {
    event.preventDefault();
    const updates = {
        email: document.getElementById('studentEmail').value.trim(),
        phone_num: document.getElementById('studentPhone').value.trim(),
        address: document.getElementById('studentAddress').value.trim()
    };
    api.updateStudent(studentId, updates);
    alert('Your information has been saved!');
    return false;
}

document.addEventListener('DOMContentLoaded', () => {
    loadEnrolledCoursesTable();
    loadGradesTable();
    updateAcademicSummary();
    // Show summary section by default
    showSection('summary');
    // Fill advisor info
    document.getElementById('advisorDisplay').textContent = getAdvisorName(getStudentInfo());
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
});

function loadEnrolledCoursesTable() {
    const tbody = document.getElementById('enrolledCoursesTableBody');
    tbody.innerHTML = '';
    const studentCourses = getStudentCourses();
    studentCourses.forEach(course => {
        const prereqs = (course.prerequisites && course.prerequisites.length > 0) ? course.prerequisites.join(', ') : '-';
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${course.code}</td>
            <td>${course.title}</td>
            <td>${course.credits}</td>
            <td>${course.teacher}</td>
            <td>${prereqs}</td>
        `;
        tbody.appendChild(row);
    });
}

function loadGradesTable() {
    const tbody = document.getElementById('gradesTableBody');
    tbody.innerHTML = '';
    const studentCourses = getStudentCourses();
    studentCourses.forEach(course => {
        const categories = api.getCategories(course.course_id);
        let gradesCells = '';
        let sum = 0, totalWeight = 0;
        categories.forEach(cat => {
            const val = course.grades[cat.name.toLowerCase()] ?? 0;
            gradesCells += `<td>${val}</td>`;
            sum += (val * cat.weight);
            totalWeight += cat.weight;
        });
        const avg = totalWeight > 0 ? (sum / totalWeight).toFixed(2) : '0.00';
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${course.code}</td>
            <td>${course.title}</td>
            <td>${course.credits}</td>
            ${gradesCells}
            <td>${avg}</td>
        `;
        tbody.appendChild(row);
    });
}

function calculateCourseAverage(grades, categories) {
    let sum = 0, totalWeight = 0;
    categories.forEach(cat => {
        const val = grades[cat.name.toLowerCase()] ?? 0;
        sum += (val * cat.weight);
        totalWeight += cat.weight;
    });
    return totalWeight > 0 ? (sum / totalWeight).toFixed(2) : '0.00';
}

function calculateGPA() {
    let totalPoints = 0;
    let totalCredits = 0;
    const studentCourses = getStudentCourses();
    studentCourses.forEach(course => {
        const categories = api.getCategories(course.course_id);
        const average = parseFloat(calculateCourseAverage(course.grades, categories));
        let gradePoints;
        if (average >= 90) gradePoints = 4.0;
        else if (average >= 85) gradePoints = 3.7;
        else if (average >= 80) gradePoints = 3.3;
        else if (average >= 75) gradePoints = 3.0;
        else if (average >= 70) gradePoints = 2.7;
        else if (average >= 65) gradePoints = 2.3;
        else if (average >= 60) gradePoints = 2.0;
        else gradePoints = 0.0;
        totalPoints += gradePoints * course.credits;
        totalCredits += course.credits;
    });
    return totalCredits > 0 ? (totalPoints / totalCredits).toFixed(2) : '0.00';
}

function updateAcademicSummary() {
    document.getElementById('gpaDisplay').textContent = calculateGPA();
    const studentCourses = getStudentCourses();
    const totalCredits = studentCourses.reduce((sum, course) => sum + course.credits, 0);
    document.getElementById('totalCreditsDisplay').textContent = totalCredits;
    document.getElementById('completedCoursesDisplay').textContent = studentCourses.length;
}

// Sidebar navigation logic
function showSection(sectionId) {
    const sections = ['profile', 'courses', 'grades', 'summary'];
    sections.forEach(id => {
        const el = document.getElementById(id);
        if (el) el.style.display = (id === sectionId) ? 'block' : 'none';
    });
    // Update sidebar active class
    document.querySelectorAll('.sidebar-menu li').forEach(li => li.classList.remove('active'));
    const activeLi = document.querySelector(`.sidebar-menu a[href="#${sectionId}"]`);
    if (activeLi && activeLi.parentElement) activeLi.parentElement.classList.add('active');
    // Load tables if needed
    if (sectionId === 'grades') loadGradesTable();
    if (sectionId === 'courses') loadEnrolledCoursesTable();
    if (sectionId === 'profile') fillStudentInfo();
} 