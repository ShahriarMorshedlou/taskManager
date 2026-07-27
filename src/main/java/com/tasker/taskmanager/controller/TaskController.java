package com.tasker.taskmanager.controller;


import com.tasker.taskmanager.dto.request.CreateTaskRequest;
import com.tasker.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
