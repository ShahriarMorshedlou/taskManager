package com.tasker.taskmanager.controller;


import com.tasker.taskmanager.domain.Task;
import com.tasker.taskmanager.dto.request.CreateTaskRequest;
import com.tasker.taskmanager.dto.request.UpdateTaskRequest;
import com.tasker.taskmanager.dto.response.TaskResponse;
import com.tasker.taskmanager.dto.response.UpdateTaskResponse;
import com.tasker.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public void create(@RequestBody CreateTaskRequest request) {
        taskService.create(request);
    }

    @GetMapping
    public List<TaskResponse> taskList (){
       return taskService.getTasksList();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById (@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public UpdateTaskResponse updateTaskResponse(
            @PathVariable Long id,
            @RequestBody UpdateTaskRequest updateTaskRequest
    ) {
        return taskService.updateTask(id, updateTaskRequest);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        taskService.deleteById(id);
    }


}
