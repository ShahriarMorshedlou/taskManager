# Task Manager

A RESTful Task Management application built with Spring Boot.

## Overview

Task Manager is a backend-focused task management application developed with Spring Boot.
It provides a RESTful API for creating, retrieving, updating, and deleting tasks.

## Features

- Create a new task
- Retrieve all tasks
- Retrieve a task by ID
- Update an existing task
- Delete a task
- Search tasks by title
- Filter tasks by priority
- Sort tasks
- Paginate task results
- Validate request data
- Handle application exceptions

- ## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- SpringDoc OpenAPI
- Swagger UI
- HTML5
- CSS3
- Bootstrap
- Vanilla JavaScript

## Architecture

The backend follows a layered architecture with separated responsibilities:

- **Controller:** Handles HTTP requests and responses.
- **DTO:** Defines API request and response models.
- **Domain:** Contains the core business entities and domain concepts.
- **Service:** Contains the application's business logic.
- **Repository:** Handles data access and communication with the database.
- **Exception Handling:** Provides centralized handling of application and validation errors.

## API Documentation

The REST API is documented using SpringDoc OpenAPI and Swagger UI.

Swagger UI:

`http://localhost:8080/swagger-ui/index.html`

OpenAPI specification:

`http://localhost:8080/v3/api-docs`

## Project Structure

```text
taskmanager/
├── backend/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/tasker/taskmanager/
│           │       ├── controller/
│           │       ├── domain/
│           │       ├── dto/
│           │       ├── exceptions/
│           │       ├── repository/
│           │       └── service/
│           └── resources/
│
└── frontend/
    ├── index.html
    ├── create-task.html
    ├── task-detail.html
    └── script.js
```

## Getting Started

### Backend

1. Make sure Java 21 and PostgreSQL are installed.
2. Create the required PostgreSQL database.
3. Configure the database connection in the application configuration.
4. Run the Spring Boot application.

The backend runs on:

`http://localhost:8080`

## Frontend

The project includes a simple frontend built with HTML, CSS, Bootstrap, and Vanilla JavaScript.

The frontend communicates with the Spring Boot backend through REST APIs using the Fetch API.

The frontend is intended primarily as a functional interface for interacting with and testing the backend API.

### Running the Frontend

The frontend consists of static HTML, CSS, and JavaScript files and does not require a build process.

It can be run using the VS Code Live Server extension.

The default frontend address is:

`http://127.0.0.1:5500`

## Development Approach

This project was developed with a primary focus on backend engineering and Spring Boot.

The backend was designed and implemented with a layered architecture, RESTful API principles, DTOs, validation, exception handling, PostgreSQL, and OpenAPI documentation.

The frontend was intentionally kept simple and was developed with AI assistance using HTML, Bootstrap, and Vanilla JavaScript. Its main purpose is to provide a functional interface for consuming and interacting with the backend API.

## Future Improvements

Possible future improvements include:

- Authentication and authorization with Spring Security and JWT
- Automated testing with JUnit and Mockito
- Docker containerization
- Database migration with Flyway or Liquibase
- Caching with Redis
- CI/CD integration
