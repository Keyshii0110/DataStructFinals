package com.mycompany.datastructfinals;

import java.util.HashMap;

public class TaskHashTable {

    HashMap<String, Task> table = new HashMap<>();

    public void add(Task task) {
        table.put(task.name, task);
    }

    public Task search(String name) {
        return table.get(name);
    }

    public void display() {
        if (table.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (Task task : table.values()) {
            task.display();
        }
    }
}
