package com.scaler.springboot1.task;

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

    public Task createTask(Task task) {
        task.setId(nextTaskId++);
        taskList.add(task);
        return task;
    }


    public Task updateTask(Integer id, Date dueDate, Boolean completed) {
        Task task = getTaskById(id);
        if (dueDate != null) {
            task.setDueDate(dueDate);
        }
        if (completed != null) {
            task.setCompleted(completed);
        }

        return task;
    }

    public Task patchTask(Integer id,String name, Date dueDate, Boolean completed) {
        Task task = getTaskById(id);

            for (char c : name.toCharArray()){
                if(! (Character.isAlphabetic(c) || Character.isSpaceChar(c) ) )
                    throw new TaskNameIllegalException(name);
            }

        if (dueDate != null) {
            task.setDueDate(dueDate);
        }else
            throw new DueDateInValidException(dueDate);
        if (completed != null) {
            task.setCompleted(completed);
        }

        return task;
    }


    public void deleteTask(Integer id) {
        Task task = getTaskById(id);
        taskList.remove(task);
    }

    /*
Create a new class for Exception handling that extemdomg Runtime/IllegalStateException

*/
    public static class TaskNotFoundException extends IllegalStateException {
        public TaskNotFoundException(Integer id) {
            super("Task with id" + id + "not found");
        }
    }

    public static class DueDateInValidException extends RuntimeException {
        public DueDateInValidException(Date duedate) {
            super("Provide due Date " + duedate + " is NOT VALID");
        }
    }

    public static class TaskNameIllegalException extends RuntimeException {
        public TaskNameIllegalException(String name) {
            super("Provide Task Name " + name + " is NOT VALID");
        }
    }

}