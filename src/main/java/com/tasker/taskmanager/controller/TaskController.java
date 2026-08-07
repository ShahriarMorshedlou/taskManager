package com.tasker.taskmanager.controller;

import com.tasker.taskmanager.domain.Priority;
import com.tasker.taskmanager.domain.Task;
import com.tasker.taskmanager.dto.request.CreateTaskRequest;
import com.tasker.taskmanager.dto.request.UpdateTaskRequest;
import com.tasker.taskmanager.dto.response.TaskResponse;
import com.tasker.taskmanager.dto.response.UpdateTaskResponse;
import com.tasker.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Validated
@RestController

@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(
            @Valid
            @RequestBody
            CreateTaskRequest request) {

       TaskResponse taskResponse = taskService.create(request);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(taskResponse);

    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> taskList (){
       return ResponseEntity.ok(taskService.getTasksList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById (
            @Positive
            @PathVariable Long id){

        TaskResponse taskResponse = taskService.getTaskById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity <UpdateTaskResponse> updateTaskResponse(
            @Positive
            @PathVariable Long id,
            @Valid
            @RequestBody UpdateTaskRequest updateTaskRequest
    ) {

        UpdateTaskResponse updateTaskResponse = taskService.updateTask(id, updateTaskRequest);
       return ResponseEntity.ok(updateTaskResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Positive
            @PathVariable Long id){
       taskService.deleteById(id);
       return ResponseEntity
               .status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping (params = "priority")
    public ResponseEntity <List<TaskResponse>> getTasks(@RequestParam Priority priority){
         return ResponseEntity.ok(taskService.getTasks(priority));
    }


    @GetMapping (params = "title")
    public ResponseEntity <List<TaskResponse>> getTasks (
            @NotBlank
            @RequestParam String title){
        return ResponseEntity.ok(taskService.searchTasks(title));
    }

    @GetMapping (params="sort")
    public ResponseEntity<List<TaskResponse>> getTask(
            @NotBlank
            @RequestParam String sort){
        return ResponseEntity
                .ok(taskService.sortTask(sort));
    }


}
