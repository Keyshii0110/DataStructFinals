package com.mycompany.datastructfinals;

import java.util.PriorityQueue;

public class TaskHeap {

    PriorityQueue<Task> heap = new PriorityQueue<>(
        (a, b) -> Integer.compare(b.priority, a.priority)
    );

    public void add(Task task) {
        heap.add(task);
    }

    public Task remove() {
        return heap.poll();
    }

    public void display() {
        if (heap.isEmpty()) {
            System.out.println("No tasks in priority queue.");
            return;
        }

        for (Task task : heap) {
            task.display();
        }
    }
}
