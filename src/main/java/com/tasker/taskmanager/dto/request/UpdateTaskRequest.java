package com.tasker.taskmanager.dto.request;

import com.tasker.taskmanager.domain.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest {
        private String title;
        private String description;
        private Priority priority;



}
