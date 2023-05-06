package com.scaler.springboot1.task.dtos;

import lombok.Data;

@Data
public class ResponseDTO {
    String name;
    String dueDate;
    Boolean completed;
}
