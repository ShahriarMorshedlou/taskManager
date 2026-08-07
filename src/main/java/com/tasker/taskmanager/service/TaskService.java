package com.tasker.taskmanager.service;

import com.tasker.taskmanager.domain.Priority;
import com.tasker.taskmanager.domain.Status;
import com.tasker.taskmanager.domain.Task;
import com.tasker.taskmanager.dto.request.CreateTaskRequest;
import com.tasker.taskmanager.dto.request.UpdateTaskRequest;
import com.tasker.taskmanager.dto.response.TaskResponse;
import com.tasker.taskmanager.dto.response.UpdateTaskResponse;
import com.tasker.taskmanager.exceptions.InvalidSortFieldException;
import com.tasker.taskmanager.exceptions.TaskNotFoundException;
import com.tasker.taskmanager.repository.TaskRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public TaskResponse create(CreateTaskRequest createTaskRequest){

        Task task = new Task();

        task.setDescription(createTaskRequest.getDescription());
        task.setTitle(createTaskRequest.getTitle());
        task.setStatus(Status.PENDING);
        task.setPriority(Priority.LOW);

        taskRepository.save(task);

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.getDeadline()
        );
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
                .orElseThrow(() -> new TaskNotFoundException( "Task with id " + id + " not found"));

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
                .orElseThrow(() -> new TaskNotFoundException( "Task with id " + id + " not found"));

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

    public void deleteById(Long id ){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException( "Task with id " + id + " not found"));

        taskRepository.delete(task);
    }

    public List<TaskResponse> getTasks(Priority priority){

        List<Task> tasks = taskRepository.findByPriority(priority);

        List <TaskResponse> taskResponseList = new ArrayList<>();

        for ( Task task : tasks){

            TaskResponse taskResponse = new TaskResponse(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getPriority(),
                    task.getDeadline()

            );
                taskResponseList.add(taskResponse);
        } return taskResponseList;

    }

    public List<TaskResponse> searchTasks (String title){

        List<Task> tasks = taskRepository.findByTitleContaining(title);

        List<TaskResponse> taskResponseList = new ArrayList<>();


        for (Task task: tasks){

            TaskResponse taskResponse = new TaskResponse(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getPriority(),
                    task.getDeadline()
            ); taskResponseList.add(taskResponse);
        } return taskResponseList;
    }


    private static final Set<String> VALID_SORT_FIELDS = Set.of(
            "title",
            "priority",
            "createdAt"
    );

    public List<TaskResponse> sortTask(String sort) {

        if (!VALID_SORT_FIELDS.contains(sort)) {
            throw new InvalidSortFieldException("Invalid sort field: " + sort);
        }

        Sort sortObject = Sort.by(Sort.Direction.ASC, sort);

        List<Task> tasks = taskRepository.findAll(sortObject);

        List<TaskResponse> taskResponseList = new ArrayList<>();

        for (Task task : tasks) {

            TaskResponse taskResponse = new TaskResponse(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getPriority(),
                    task.getDeadline()
            );

            taskResponseList.add(taskResponse);
        }

        return taskResponseList;
    }
}




