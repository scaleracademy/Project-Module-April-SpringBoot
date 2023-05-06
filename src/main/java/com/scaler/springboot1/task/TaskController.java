package com.scaler.springboot1.task;


import com.scaler.springboot1.task.dtos.ResponseDTO;
import com.scaler.springboot1.task.dtos.UpdateTaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping("/all")
    ResponseEntity<List<Task>> createTasks(@RequestBody Task[] tasks) {
        List<Task> createdTaskList = new ArrayList<>();
        for (Task task : tasks) {
            createdTaskList.add(tasksService.createTask(task));
        }

        return ResponseEntity.ok(createdTaskList);
    }

    @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable("id") Integer id) {
        var task = tasksService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    //Todo 1: implement Update Task - PATCh
    @PatchMapping("/{id}")
    ResponseEntity<ResponseDTO> updateTask(@RequestBody Task task, @PathVariable("id") Integer id) {

        var updatedTask = tasksService.patchTask(id, task.getName(), task.getDueDate(), task.completed);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setName(updatedTask.getName());
        responseDTO.setDueDate(updatedTask.getDueDate().toString());
        responseDTO.setCompleted(updatedTask.getCompleted());
        return ResponseEntity.ok(responseDTO);
    }

    // Todo 2: implement Delete Task - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Integer id) {
        var deletedTask = tasksService.getTaskById(id);
        tasksService.deleteTask(id);
        return ResponseEntity.ok(deletedTask);
    }
    // Todo5: create a ResponseBodyDTO - only return name, dueDate, completed

    // Todo3 - handle expection for IllegalArgumentException ( due date, name)
    // Todo4 - in error responses, also send the error message in the response body
/*    @ExceptionHandler(TasksService.TaskNotFoundException.class)
    ResponseEntity<String> handleTaskNotFoundException(TasksService.TaskNotFoundException e) {
        return ResponseEntity.notFound().build();
    }*/
}
