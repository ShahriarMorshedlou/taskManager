package com.tasker.taskmanager.service;

import com.tasker.taskmanager.domain.Priority;
import com.tasker.taskmanager.domain.Status;
import com.tasker.taskmanager.domain.Task;
import com.tasker.taskmanager.dto.request.CreateTaskRequest;
import com.tasker.taskmanager.dto.request.UpdateTaskRequest;
import com.tasker.taskmanager.dto.response.TaskResponse;
import com.tasker.taskmanager.dto.response.UpdateTaskResponse;
import com.tasker.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getPriority(),
                    task.getDeadline()
            );

            response.add(taskResponse);
        }

        return response;
    }


    public TaskResponse getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        TaskResponse response = new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.getDeadline()
        );

        return response;
    }


    public UpdateTaskResponse updateTask(Long id,UpdateTaskRequest updateTaskRequest){

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(updateTaskRequest.getTitle());
        task.setDescription(updateTaskRequest.getDescription());
        task.setPriority(updateTaskRequest.getPriority());
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(task);


        UpdateTaskResponse updateTaskResponse = new UpdateTaskResponse(

                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getUpdatedAt()

        );

        return updateTaskResponse;

    }

}




