package com.mycompany.datastructfinals;

// Stores information about a task
public class Task {
    String name;
    String type;
    String dueDate;
    int priority;
    boolean completed;

    // Create a task
    public Task(String name, String type, String dueDate, int priority) {
        this.name = name;
        this.type = type;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;
    }

    // Complete the task
    public void complete() {
        completed = true;
    }

    // Display task information
    public void displayTask() {
        System.out.println("Task: " + name);
        System.out.println("Type: " + type);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Priority: " + priority);
        System.out.println("Status: " + (completed ? "Completed" : "Pending"));
        System.out.println();
    }
}
