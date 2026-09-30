package com.mycompany.datastructfinals;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {
    Queue<Task> queue = new LinkedList<>();

    public void add(Task task) {
        queue.add(task);
    }

    public Task remove() {
        if (!queue.isEmpty()) {
            return queue.remove();
        }

        return null;
    }

    public void display() {
        if (queue.isEmpty()) {
            System.out.println("No pending reminders.");
            return;
        }

        for (Task task : queue) {
            task.display();
        }
    }
}
