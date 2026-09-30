package com.mycompany.datastructfinals;

public class TaskLinkedList {

    class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
        }
    }

    Node head;

    public void add(Task task) {
        Node newNode = new Node(task);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    public void display() {
        Node current = head;

        if (current == null) {
            System.out.println("No tasks found.");
            return;
        }

        while (current != null) {
            current.task.display();
            current = current.next;
        }
    }
}
