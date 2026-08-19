document.addEventListener('DOMContentLoaded', function() {
    const showFormBtn = document.getElementById('showFormBtn');
    if (showFormBtn) {
        showFormBtn.addEventListener('click', function() {
            window.location.href = 'create-task.html';
        });
    }

    let currentPage = 0;
    const pageSize = 5;
    let currentSort = 'title';

    fetchTasks(currentPage, pageSize, currentSort);

    document.getElementById('searchBtn').addEventListener('click', function() {
        const title = document.getElementById('searchInput').value.trim();
        if (title) {
            searchTasks(title);
        } else {
            fetchTasks(0, pageSize, document.getElementById('sortSelect').value);
        }
    });

    document.getElementById('resetBtn').addEventListener('click', function() {
        document.getElementById('searchInput').value = '';
        currentPage = 0;
        currentSort = document.getElementById('sortSelect').value;
        fetchTasks(currentPage, pageSize, currentSort);
    });

    document.getElementById('sortSelect').addEventListener('change', function() {
        currentPage = 0;
        currentSort = this.value;
        fetchTasks(currentPage, pageSize, currentSort);
    });

    function fetchTasks(page, size, sort) {
        const container = document.getElementById('taskListContainer');
        container.innerHTML = '<div class="alert alert-info">Loading tasks...</div>';

        const url = `http://localhost:8080/tasks?page=${page}&size=${size}&sort=${sort}`;

        fetch(url)
            .then(response => {
                if (!response.ok) throw new Error('Failed to fetch tasks');
                return response.json();
            })
            .then(tasks => {
                if (tasks.length === 0) {
                    container.innerHTML = '<div class="alert alert-warning">No tasks found.</div>';
                    document.getElementById('paginationControls').innerHTML = '';
                    return;
                }
                displayTasks(tasks);
                renderPagination(page, tasks.length);
            })
            .catch(error => {
                container.innerHTML = `<div class="alert alert-danger">Error loading tasks: ${error.message}</div>`;
                console.error('Error:', error);
            });
    }

    function displayTasks(tasks) {
        const container = document.getElementById('taskListContainer');

        let html = `<table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Priority</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>`;

        tasks.forEach(task => {
            html += `<tr>
                <td>${task.id}</td>
                <td><a href="task-detail.html?id=${task.id}">${task.title}</a></td>
                <td><span class="badge bg-secondary">${task.priority || 'N/A'}</span></td>
                <td><span class="badge bg-info">${task.status || 'TODO'}</span></td>
                <td>
                    <button class="btn btn-sm btn-danger delete-btn" data-id="${task.id}">Delete</button>
                </td>
            </tr>`;
        });

        html += `</tbody></table>`;
        container.innerHTML = html;

        document.querySelectorAll('.delete-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                const id = this.getAttribute('data-id');
                deleteTask(id);
            });
        });
    }

    function renderPagination(currentPage, totalItems) {
        const paginationContainer = document.getElementById('paginationControls');
        const totalPages = Math.ceil(totalItems / pageSize);
        let html = '';

        html += `<li class="page-item ${currentPage === 0 ? 'disabled' : ''}">
            <a class="page-link" href="#" data-page="${currentPage - 1}">Previous</a>
        </li>`;

        for (let i = 0; i < totalPages; i++) {
            html += `<li class="page-item ${i === currentPage ? 'active' : ''}">
                <a class="page-link" href="#" data-page="${i}">${i + 1}</a>
            </li>`;
        }

        html += `<li class="page-item ${currentPage >= totalPages - 1 ? 'disabled' : ''}">
            <a class="page-link" href="#" data-page="${currentPage + 1}">Next</a>
        </li>`;

        paginationContainer.innerHTML = html;

        document.querySelectorAll('#paginationControls .page-link').forEach(link => {
            link.addEventListener('click', function(e) {
                e.preventDefault();
                const page = parseInt(this.getAttribute('data-page'));
                if (!isNaN(page) && page >= 0) {
                    currentPage = page;
                    fetchTasks(currentPage, pageSize, currentSort);
                }
            });
        });
    }

    function searchTasks(title) {
        const container = document.getElementById('taskListContainer');
        container.innerHTML = '<div class="alert alert-info">Searching...</div>';

        fetch(`http://localhost:8080/tasks?title=${encodeURIComponent(title)}`)
            .then(response => {
                if (!response.ok) throw new Error('Search failed');
                return response.json();
            })
            .then(tasks => {
                if (tasks.length === 0) {
                    container.innerHTML = '<div class="alert alert-warning">No tasks found for this title.</div>';
                    document.getElementById('paginationControls').innerHTML = '';
                    return;
                }
                displayTasks(tasks);
                document.getElementById('paginationControls').innerHTML = '';
            })
            .catch(error => {
                container.innerHTML = `<div class="alert alert-danger">Error searching: ${error.message}</div>`;
                console.error('Error:', error);
            });
    }

    function deleteTask(id) {
        if (!confirm('Are you sure you want to delete this task?')) return;

        fetch(`http://localhost:8080/tasks/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.status === 204) {
                alert('Task deleted successfully!');
                fetchTasks(currentPage, pageSize, currentSort);
            } else {
                throw new Error('Failed to delete task');
            }
        })
        .catch(error => {
            alert('Error deleting task: ' + error.message);
            console.error('Error:', error);
        });
    }
});