document.addEventListener('DOMContentLoaded', function() {
    const showFormBtn = document.getElementById('showFormBtn');

    if (showFormBtn) {
        showFormBtn.addEventListener('click', function() {
            window.location.href = 'create-task.html';
        });
    }
});