package com.scaler.springboot1.task.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TaskResponseDTO {
    String name;
    Date dueDate;
    Boolean completed;
}
