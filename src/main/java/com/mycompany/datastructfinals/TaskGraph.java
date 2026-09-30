package com.mycompany.datastructfinals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class TaskGraph {

    HashMap<String, ArrayList<String>> graph = new HashMap<>();

    public void addTask(String task) {
        if (!graph.containsKey(task)) {
            graph.put(task, new ArrayList<>());
        }
    }

    public void addConnection(String task1, String task2) {
        addTask(task1);
        addTask(task2);

        graph.get(task1).add(task2);
        graph.get(task2).add(task1);
    }

    public void display() {
        for (String task : graph.keySet()) {
            System.out.println(task + " -> " + graph.get(task));
        }
    }

    public void bfs(String start) {
        if (!graph.containsKey(start)) {
            System.out.println("Task not found.");
            return;
        }

        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.remove();
            System.out.print(current + " ");

            for (String next : graph.get(current)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        System.out.println();
    }
}
