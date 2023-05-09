package com.scaler.springboot1.task;


import com.scaler.springboot1.task.dtos.UpdateTaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  private final TasksService tasksService;
    public TaskController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

  @GetMapping("")
    ResponseEntity<List<Task>> getAllTasks() {
        var tasks = tasksService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
   @PostMapping("")
    ResponseEntity<Task> createTask(@RequestBody Task task) {
        var createdTask = tasksService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }
   @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable("id") Integer id) {
        var task = tasksService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Task> deleteTaskById(@PathVariable("id") Integer id) {
        var task = tasksService.deleteTask(id);
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Task> updateTaskById(@RequestBody UpdateTaskDTO task, @PathVariable("id") Integer id) {
        var updatedTask = tasksService.updateTask(id, task.getDueDate(), task.getCompleted());
        return ResponseEntity.ok(updatedTask);
    }

    //Todo 1: implement Update Task - PATCh
    // Todo 2: implement Delete Task - DELETE
    // Todo5: create a ResponseBodyDTO - only return name, dueDate, completed

    // Todo3 - handle expection for IllegalArgumentException ( due date, name)
    // Todo4 - in error responses, also send the error message in the response body
    @ExceptionHandler(TasksService.TaskNotFoundException.class)
    ResponseEntity<String> handleTaskNotFoundException(TasksService.TaskNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
