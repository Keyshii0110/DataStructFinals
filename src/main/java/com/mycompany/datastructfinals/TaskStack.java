package com.mycompany.datastructfinals;

import java.util.Stack;

public class TaskStack {
    Stack<Task> stack = new Stack<>();

    public void push(Task task) {
        stack.push(task);
    }

    public Task pop() {
        if (!stack.isEmpty()) {
            return stack.pop();
        }

        return null;
    }

    public void display() {
        if (stack.isEmpty()) {
            System.out.println("No completed tasks.");
            return;
        }

        for (int i = stack.size() - 1; i >= 0; i--) {
            stack.get(i).display();
        }
    }
}
