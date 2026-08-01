package com.tasker.taskmanager.repository;

import com.tasker.taskmanager.domain.Priority;
import com.tasker.taskmanager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByPriority(Priority priority);
}
