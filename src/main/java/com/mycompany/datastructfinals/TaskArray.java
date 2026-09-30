package com.mycompany.datastructfinals;

public class TaskArray {
    Task[] tasks;
    int count;

    public TaskArray(int size) {
        tasks = new Task[size];
        count = 0;
    }

    public void add(Task task) {
        if (count < tasks.length) {
            tasks[count] = task;
            count++;
        } else {
            System.out.println("Task list is full.");
        }
    }

    public void display() {
        if (count == 0) {
            System.out.println("No tasks found.");
            return;
        }

        for (int i = 0; i < count; i++) {
            tasks[i].display();
        }
    }

    public Task get(int index) {
        if (index >= 0 && index < count) {
            return tasks[index];
        }

        return null;
    }

    public int size() {
        return count;
    }
}