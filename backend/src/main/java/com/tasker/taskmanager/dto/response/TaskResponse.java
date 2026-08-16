package com.tasker.taskmanager.dto.response;
import com.tasker.taskmanager.domain.Priority;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private Priority priority;

    private LocalDateTime deadline;



}
