package com.scaler.springboot1.task;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor

public class Task {
Integer id;
String name;
Date dueDate;
Boolean completed;
}