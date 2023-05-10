package com.scaler.springboot1.task;

import com.scaler.springboot1.task.dtos.CreateTaskDTO;
import com.scaler.springboot1.task.dtos.TaskDTO;
import com.scaler.springboot1.task.dtos.UpdateTaskDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TasksService {
    List<Task> taskList = new ArrayList<>();
    private int nextTaskId = 0;

    public List<TaskDTO> getAllTasks() {
        List<TaskDTO> taskDTOs = this.taskList.stream().map(t->this.taskToDTO(t)).collect(Collectors.toList());
        return taskDTOs;
    }

    public Task getTaskById(Integer id) {
//        Task foundTask = taskList.stream().filter(
//                        task -> task.getId().equals(id)) // for
//                .findFirst().orElse(null);

        for (Task task : taskList) {
            if (task.getId().equals(id)) {
                return task;
            }
        }

        throw new TaskNotFoundException(id);
    }

    public CreateTaskDTO createTask(Task task) {
        task.setId(nextTaskId++);
        taskList.add(task);
        CreateTaskDTO createTaskDTO = this.taskToCreateDTO(task);
        return createTaskDTO;
    }


    public UpdateTaskDTO updateTask(Integer id, Task task) {
        Task currentTask = getTaskById(id);
        if(task.name != null){
            currentTask.name = task.name;
        }
        if(task.dueDate != null){
            currentTask.dueDate = task.dueDate;
        }
        if(task.completed != null){
            currentTask.completed = task.completed;
        }
        UpdateTaskDTO updateTaskDTO = this.taskToUpdateDTO(task);
        return updateTaskDTO;
    }

    public String deleteTask(Integer id) {
        for (Task task : taskList) {
            if (task.getId().equals(id)) {
                Task taskToDelete = getTaskById(id);
                taskList.remove(taskToDelete);
                return "Task is deleted";
            }
        }

        throw new TaskNotFoundException(id);
    }

    /*
Create a new class for Exception handling that extemdomg Runtime/IllegalStateException

*/
    public static class TaskNotFoundException extends IllegalStateException {
        public TaskNotFoundException(Integer id) {
            super("Task with id" + id + "not found");
        }
    }

    public CreateTaskDTO taskToCreateDTO(Task task){
        CreateTaskDTO createTaskDTO = new CreateTaskDTO();
        createTaskDTO.setName(task.getName());
        createTaskDTO.setDueDate(task.getDueDate()) ;
        return createTaskDTO;
    }

    public TaskDTO taskToDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName(task.getName());
        taskDTO.setDueDate(task.getDueDate()) ;
        return taskDTO;
    }

    public UpdateTaskDTO taskToUpdateDTO(Task task){
        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO();
        updateTaskDTO.setDueDate(task.getDueDate());
        updateTaskDTO.setCompleted(task.getCompleted());
        return updateTaskDTO;
    }
}