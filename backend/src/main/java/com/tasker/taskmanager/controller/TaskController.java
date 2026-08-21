package com.tasker.taskmanager.controller;

import com.tasker.taskmanager.domain.Priority;
import com.tasker.taskmanager.domain.Task;
import com.tasker.taskmanager.dto.request.CreateTaskRequest;
import com.tasker.taskmanager.dto.request.UpdateTaskRequest;
import com.tasker.taskmanager.dto.response.TaskResponse;
import com.tasker.taskmanager.dto.response.UpdateTaskResponse;
import com.tasker.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Native;
import java.util.List;


@Validated
@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }




    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Task created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid task data"
            )
    })
    @Operation(summary = "Create a new task")
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


    @Operation(summary = "Get All Tasks")
    @ApiResponse(
            responseCode = "200",
            description = "Tasks retrieved successfully"
    )
    @GetMapping
    public ResponseEntity<List<TaskResponse>> taskList (){
       return ResponseEntity.ok(taskService.getTasksList());
    }



    @Operation(summary = "Get task by ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Task retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID must be positive"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Task not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById (
            @Positive
            @PathVariable Long id){

        TaskResponse taskResponse = taskService.getTaskById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskResponse);
    }



    @Operation(summary = "Update a task")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Task updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid task data or ID"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Task not found"
            )
    })
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



    @Operation(summary = "Delete a task")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Task deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID must be positive"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Task not found"
            )
    })
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

    @GetMapping (params = {"page", "size"})
    public ResponseEntity<List<TaskResponse>> pagination(

            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sort
    ) {
        return ResponseEntity
                .ok(taskService.pagination(page,size,sort));
    }


}
