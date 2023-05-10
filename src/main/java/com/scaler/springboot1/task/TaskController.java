package com.scaler.springboot1.task;


import com.scaler.springboot1.task.dtos.CreateTaskDTO;
import com.scaler.springboot1.task.dtos.TaskDTO;
import com.scaler.springboot1.task.dtos.UpdateTaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  private final TasksService tasksService;
    public TaskController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

  @GetMapping("")
    ResponseEntity<List<TaskDTO>> getAllTasks() {
        var tasks = tasksService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
   @PostMapping("")
    ResponseEntity<CreateTaskDTO> createTask(@RequestBody Task task) {
        var createdTask = tasksService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }
   @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable("id") Integer id) {
        var task = tasksService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    //Todo 1: implement Update Task - PATCh
    @PatchMapping("/update/{id}")
    ResponseEntity<UpdateTaskDTO> updateById(@PathVariable("id") Integer id, @RequestBody Task task){
        UpdateTaskDTO currentTask = tasksService.updateTask(id, task);
        return ResponseEntity.ok(currentTask);
    }

    // Todo 2: implement Delete Task - DELETE
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        String stringResp = tasksService.deleteTask(id);

        return ResponseEntity.ok(stringResp);
    }

    // Todo5: create a ResponseBodyDTO - only return name, dueDate, completed
    // Done

    // Todo3 - handle expection for IllegalArgumentException ( due date, name)
    // Todo4 - in error responses, also send the error message in the response body
    @ExceptionHandler(TasksService.TaskNotFoundException.class)
    ResponseEntity<String> handleTaskNotFoundException(TasksService.TaskNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
