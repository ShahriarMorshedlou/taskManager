package com.tasker.taskmanager.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Entity
@Table(name = "Tasks")
public class Task {


    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    private LocalDateTime deadline;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;




    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreat() {
        createdAt= LocalDateTime.now() ;
        updatedAt=  LocalDateTime.now() ;
    }

    @PreUpdate
    public void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;



    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }



    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", createdAt=" + createdAt +
                ", updatedAd=" + updatedAt +
                '}';
    }
}
