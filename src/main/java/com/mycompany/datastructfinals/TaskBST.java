package com.mycompany.datastructfinals;

public class TaskBST {

    class Node {
        Task task;
        Node left;
        Node right;

        Node(Task task) {
            this.task = task;
        }
    }

    Node root;

    public void insert(Task task) {
        root = insertNode(root, task);
    }

    private Node insertNode(Node node, Task task) {
        if (node == null) {
            return new Node(task);
        }

        if (task.deadline.isBefore(node.task.deadline)) {
            node.left = insertNode(node.left, task);
        } else {
            node.right = insertNode(node.right, task);
        }

        return node;
    }

    public void display() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            node.task.display();
            inorder(node.right);
        }
    }
}
