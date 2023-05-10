package com.scaler.springboot1.task;

import com.scaler.springboot1.task.dtos.TaskResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TasksService {
    List<Task> taskList = new ArrayList<>();
    private int nextTaskId = 0;

    public List<Task> getAllTasks() {
        return taskList;
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

    public TaskResponseDTO createTask(Task task) {
        task.setId(nextTaskId++);
        taskList.add(task);
        return new TaskResponseDTO(task.getName(), task.getDueDate(), task.getCompleted());
    }


    public TaskResponseDTO updateTask(Integer id, Date dueDate, Boolean completed) {
        Task task = getTaskById(id);
        if (dueDate != null) {
            task.setDueDate(dueDate);
        }

        if (completed != null) {
            task.setCompleted(completed);
        }

        return new TaskResponseDTO(task.getName(), dueDate, completed);
    }

    public Task deleteTask(Integer id) {
        Task task = getTaskById(id);
        taskList.remove(task);
        return task;
    }

    /*
Create a new class for Exception handling that extemdomg Runtime/IllegalStateException

*/
    public static class TaskNotFoundException extends IllegalStateException {
        public TaskNotFoundException(Integer id) {
            super("Task with id" + id + "not found");
        }
    }

}