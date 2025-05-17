// Mock user data for testing
const users = {
    admin: { username: 'admin', password: 'admin123', role: 'admin' },
    teacher: { username: 'teacher', password: 'teacher123', role: 'teacher' },
    student: { username: 'student', password: 'student123', role: 'student' }
};

let selectedRole = null;

// Set appropriate body class based on page
document.addEventListener('DOMContentLoaded', () => {
    const path = window.location.pathname;
    if (path.endsWith('index.html') || path === '/') {
        document.body.classList.add('login-page');
    } else {
        document.body.classList.add('dashboard');
    }
    checkAuth();
});

function selectRole(role) {
    selectedRole = role;
    // Remove active class from all buttons
    document.querySelectorAll('.role-btn').forEach(btn => {
        btn.classList.remove('active');
    });
    // Add active class to selected role button
    document.querySelector(`button[onclick="selectRole('${role}')"]`).classList.add('active');
}

function handleLogin(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Basic validation
    if (!selectedRole) {
        alert('Please select a role first');
        return false;
    }

    if (!username || !password) {
        alert('Please enter both username and password');
        return false;
    }

    // Check credentials (mock authentication)
    const user = users[selectedRole];
    if (user && user.username === username && user.password === password) {
        // Store user info in sessionStorage
        sessionStorage.setItem('currentUser', JSON.stringify({
            username,
            role: selectedRole
        }));
        
        // Redirect based on role
        window.location.href = `pages/${selectedRole}.html`;
    } else {
        alert('Invalid credentials');
    }

    return false;
}

// Check if user is already logged in
function checkAuth() {
    const currentUser = sessionStorage.getItem('currentUser');
    if (currentUser) {
        const { role } = JSON.parse(currentUser);
        // If on login page and already logged in, redirect to appropriate page
        if (window.location.pathname.endsWith('index.html') || window.location.pathname === '/') {
            window.location.href = `pages/${role}.html`;
        }
    } else {
        // If not logged in and not on login page, redirect to login
        if (!window.location.pathname.endsWith('index.html') && window.location.pathname !== '/') {
            window.location.href = '../index.html';
        }
    }
}

// Logout function
function logout() {
    sessionStorage.removeItem('currentUser');
    window.location.href = '../index.html';
}