// Use sharedData and api.js for all data operations
// Assume data/api.js and data/sharedData.js are loaded before this file

let selectedCourseId = null;
let pendingGrades = {}; // { studentId: { category: value, ... }, ... }

function getTeacherCourses(teacherId) {
    return api.getAllCourses().filter(c => c.teacher_id === teacherId);
}

function loadTeacherCoursesTable() {
    const teacherId = 101; // mock login
    const courses = getTeacherCourses(teacherId);
    const tbody = document.getElementById('teacherCoursesTableBody');
    tbody.innerHTML = '';
    courses.forEach(course => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${course.code}</td>
            <td>${course.title}</td>
            <td>${course.department}</td>
        `;
        tbody.appendChild(row);
    });
}

function populateCourseDropdown() {
    const teacherId = 101; // mock login
    const courses = getTeacherCourses(teacherId);
    const select = document.getElementById('courseSelect');
    select.innerHTML = '';
    // Add default option
    const defaultOption = document.createElement('option');
    defaultOption.value = '';
    defaultOption.textContent = 'Select a course...';
    defaultOption.disabled = true;
    defaultOption.selected = true;
    select.appendChild(defaultOption);
    courses.forEach(course => {
        const option = document.createElement('option');
        option.value = course.course_id;
        option.textContent = `${course.code} - ${course.title}`;
        select.appendChild(option);
    });
    selectedCourseId = null;
    renderCategoryManager();
    loadGradesTable();
    select.onchange = function() {
        selectedCourseId = parseInt(this.value);
        renderCategoryManager();
        loadGradesTable();
    };
}

function renderCategoryManager() {
    const listDiv = document.getElementById('categoryList');
    if (!selectedCourseId) { listDiv.innerHTML = ''; return; }
    const categories = api.getCategories(selectedCourseId);
    listDiv.innerHTML = categories.map((cat, i) =>
        `<span style="margin-right:8px;">
            ${cat.name} (${cat.weight})
            <button type="button" onclick="removeCategory(${i})" style="color:red; margin-left:2px;">x</button>
        </span>`
    ).join('');
}

function addCategory() {
    if (!selectedCourseId) return;
    const name = document.getElementById('newCategoryName').value.trim();
    const weight = parseFloat(document.getElementById('newCategoryWeight').value);
    if (!name || isNaN(weight) || weight <= 0 || weight > 1) {
        alert('Please enter a valid name and weight (0 < weight ≤ 1)');
        return;
    }
    const existingCategories = api.getCategories(selectedCourseId);
    if (existingCategories.some(cat => cat.name === name)) {
        alert('This category already exists!');
        return;
    }
    api.addCategory(selectedCourseId, name, weight);
    document.getElementById('newCategoryName').value = '';
    document.getElementById('newCategoryWeight').value = '';
    renderCategoryManager();
    loadGradesTable();
}

function removeCategory(idx) {
    if (!selectedCourseId) return;
    const categories = api.getCategories(selectedCourseId);
    const catName = categories[idx].name;
    api.removeCategory(selectedCourseId, catName);
    renderCategoryManager();
    loadGradesTable();
}

function loadGradesTable() {
    const tbody = document.getElementById('gradesTableBody');
    tbody.innerHTML = '';
    if (!selectedCourseId) return;
    const categories = api.getCategories(selectedCourseId);
    // Table header
    const thead = document.querySelector('#gradesTable thead tr');
    thead.innerHTML = `<th>Student ID</th><th>Student Name</th>` +
        categories.map(cat => `<th>${cat.name}</th>`).join('') +
        `<th>Average</th>`;
    // Get students enrolled in this course
    const enrollments = api.getEnrollmentsByCourse(selectedCourseId);
    enrollments.forEach(enr => {
        const student = api.getStudentById(enr.student_id);
        let row = `<td>${student.student_id}</td><td>${student.first_name} ${student.last_name}</td>`;
        let sum = 0, totalWeight = 0;
        categories.forEach(cat => {
            const gradeObj = api.getGrades(student.student_id, selectedCourseId).find(g => g.category === cat.name);
            const val = gradeObj ? gradeObj.value : 0;
            row += `<td><input type="number" value="${val}" min="0" max="100"
                oninput="updateGrade(${student.student_id}, '${cat.name}', this.value)"></td>`;
            sum += (val * cat.weight);
            totalWeight += cat.weight;
        });
        const avg = totalWeight > 0 ? (sum / totalWeight).toFixed(2) : '0.00';
        row += `<td>${avg}</td>`;
        const tr = document.createElement('tr');
        tr.innerHTML = row;
        tbody.appendChild(tr);
    });
}

function updateGrade(studentId, category, value) {
    let num = parseInt(value) || 0;
    if (num < 0) num = 0;
    if (num > 100) num = 100;
    if (!pendingGrades[studentId]) pendingGrades[studentId] = {};
    pendingGrades[studentId][category] = num;
    document.getElementById('saveGradesBtn').disabled = false;
}

function saveAllGrades() {
    Object.entries(pendingGrades).forEach(([studentId, gradesObj]) => {
        Object.entries(gradesObj).forEach(([category, value]) => {
            api.setGrade(Number(studentId), selectedCourseId, category, value);
        });
    });
    pendingGrades = {};
    document.getElementById('saveGradesBtn').disabled = true;
    loadGradesTable();
    alert('Grades saved!');
}

document.addEventListener('DOMContentLoaded', () => {
    loadTeacherCoursesTable();
});

// Sidebar navigation logic
function showSection(sectionId) {
    const sections = ['courses', 'gradeSection', 'teacherInfo'];
    sections.forEach(id => {
        const el = document.getElementById(id);
        if (el) el.style.display = (id === sectionId) ? 'block' : 'none';
    });
    // Update sidebar active class
    document.querySelectorAll('.sidebar-menu li').forEach(li => li.classList.remove('active'));
    const activeLi = document.querySelector(`.sidebar-menu a[href="#${sectionId}"]`);
    if (activeLi && activeLi.parentElement) activeLi.parentElement.classList.add('active');
    if (sectionId === 'gradeSection') populateCourseDropdown();
    if (sectionId === 'teacherInfo') fillTeacherInfo();
}

document.addEventListener('DOMContentLoaded', () => {
    loadTeacherCoursesTable();
    // Show only courses section by default
    showSection('courses');
    // Sidebar click events
    document.querySelectorAll('.sidebar-menu a').forEach(link => {
        link.addEventListener('click', function(e) {
            const href = this.getAttribute('href');
            if (href && href.startsWith('#')) {
                e.preventDefault();
                showSection(href.substring(1));
                if (href === '#gradeSection') {
                    populateCourseDropdown();
                }
            }
        });
    });
});

function fillTeacherInfo() {
    // Simulate logged-in teacher (id: 101)
    const teacher = api.getTeacherById(101);
    if (!teacher) return;
    document.getElementById('teacherFirstName').value = teacher.first_name;
    document.getElementById('teacherLastName').value = teacher.last_name;
    document.getElementById('teacherEmail').value = teacher.email;
    document.getElementById('teacherPhone').value = teacher.phone_num || '';
    document.getElementById('teacherAddress').value = teacher.address || '';
    document.getElementById('teacherOffice').value = teacher.office_location || '';
    document.getElementById('teacherDepartment').value = teacher.department || '';
    document.getElementById('teacherHireDate').value = teacher.hire_date || '';
} 

function saveTeacherInfo(event) {
    event.preventDefault(); // Formun kendi kendine sayfa yenilemesini engeller

    const email = document.getElementById('teacherEmail').value.trim();
    const phone = document.getElementById('teacherPhone').value.trim();
    const address = document.getElementById('teacherAddress').value.trim();

    // Basit bir doğrulama
    if (!email || !phone || !address) {
        alert('Please fill in all editable fields.');
        return false;
    }

    // Örneğin burada bir API'ye gönderiyor olabilirsin:
    const teacherData = {
        email: email,
        phone: phone,
        address: address
    };

    // Örnek: API'ye kaydetme (senin projenin api objesi varsa)
    api.updateTeacherInfo(teacherData);

    alert('Teacher info saved successfully!');
    return false; // Formun submit olmasını yine de engelliyoruz
}
