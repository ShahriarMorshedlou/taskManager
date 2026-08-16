package com.tasker.taskmanager.dto.request;

import com.tasker.taskmanager.domain.Priority;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

        @NotBlank
        private String title;
        @Size(max = 500)
        private String description;
        @NotNull
        private Priority priority;

}
