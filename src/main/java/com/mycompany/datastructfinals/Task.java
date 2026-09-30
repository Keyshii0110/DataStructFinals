package com.mycompany.datastructfinals;

import java.time.LocalDate;

public class Task {
    String name;
    String type;
    LocalDate deadline;
    int priority;
    boolean completed;

    public Task(String name, String type, LocalDate deadline, int priority) {
        this.name = name;
        this.type = type;
        this.deadline = deadline;
        this.priority = priority;
        this.completed = false;
    }

    public void display() {
        System.out.println(
            name + " | " + type +
            " | Deadline: " + deadline +
            " | Priority: " + priority +
            " | " + (completed ? "Completed" : "Pending")
        );
    }
}
