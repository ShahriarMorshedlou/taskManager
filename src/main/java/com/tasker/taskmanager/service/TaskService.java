package com.tasker.taskmanager.service;

import com.tasker.taskmanager.domain.Priority;
import com.tasker.taskmanager.domain.Status;
import com.tasker.taskmanager.domain.Task;
import com.tasker.taskmanager.dto.request.CreateTaskRequest;
import com.tasker.taskmanager.dto.response.TaskResponse;
import com.tasker.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public void create(CreateTaskRequest createTaskRequest){
        Task task = new Task();

        task.setDescription(createTaskRequest.getDescription());
        task.setTitle(createTaskRequest.getTitle());
        task.setStatus(Status.PENDING);
        task.setPriority(Priority.LOW);

        taskRepository.save(task);
    }


    public List<TaskResponse> getTasksList() {

        List<Task> tasks = taskRepository.findAll();

        List<TaskResponse> response = new ArrayList<>();

        for (Task task : tasks) {

            TaskResponse taskResponse = new TaskResponse(
                    task.getTitle(),
                    task.getDescription(),
                    task.getPriority(),
                    task.getDeadline()
            );

            response.add(taskResponse);
        }

        return response;
    }
}
