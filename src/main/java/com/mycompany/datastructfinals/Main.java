package com.mycompany.datastructfinals;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static TaskArray taskArray = new TaskArray(100);
    static TaskLinkedList taskList = new TaskLinkedList();
    static TaskStack completedTasks = new TaskStack();
    static TaskQueue reminderQueue = new TaskQueue();
    static TaskBST taskBST = new TaskBST();
    static TaskHeap priorityQueue = new TaskHeap();
    static TaskHashTable taskTable = new TaskHashTable();
    static TaskGraph taskGraph = new TaskGraph();

    static int streak = 0;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n=== CHORE AND BILL SCHEDULER ===");
            System.out.println("1. Add Chore or Bill");
            System.out.println("2. View All Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. View Tasks by Deadline");
            System.out.println("5. View Tasks by Priority");
            System.out.println("6. Search Task");
            System.out.println("7. View Completed Tasks");
            System.out.println("8. View Pending Reminders");
            System.out.println("9. View Task Connections");
            System.out.println("10. View Streak");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addTask();
                    break;

                case 2:
                    taskArray.display();
                    break;

                case 3:
                    completeTask();
                    break;

                case 4:
                    taskBST.display();
                    break;

                case 5:
                    priorityQueue.display();
                    break;

                case 6:
                    searchTask();
                    break;

                case 7:
                    completedTasks.display();
                    break;

                case 8:
                    reminderQueue.display();
                    break;

                case 9:
                    taskGraph.display();
                    break;

                case 10:
                    System.out.println("Current streak: " + streak);
                    break;

                case 0:
                    System.out.println("Program ended.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    public static void addTask() {

        System.out.print("Enter task name: ");
        String name = scanner.nextLine();

        System.out.print("Enter type (Chore/Bill): ");
        String type = scanner.nextLine();

        System.out.print("Enter deadline year: ");
        int year = scanner.nextInt();

        System.out.print("Enter deadline month: ");
        int month = scanner.nextInt();

        System.out.print("Enter deadline day: ");
        int day = scanner.nextInt();

        System.out.print("Enter priority (1-5): ");
        int priority = scanner.nextInt();
        scanner.nextLine();

        LocalDate deadline = LocalDate.of(year, month, day);

        Task task = new Task(name, type, deadline, priority);

        taskArray.add(task);
        taskList.add(task);
        reminderQueue.add(task);
        taskBST.insert(task);
        priorityQueue.add(task);
        taskTable.add(task);
        taskGraph.addTask(name);

        System.out.println("Task added successfully.");
    }

    public static void completeTask() {

        System.out.print("Enter task name to complete: ");
        String name = scanner.nextLine();

        Task task = taskTable.search(name);

        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        if (task.completed) {
            System.out.println("Task is already completed.");
            return;
        }

        task.completed = true;
        completedTasks.push(task);
        streak++;

        System.out.println("Task completed.");
        System.out.println("Current streak: " + streak);
    }

    public static void searchTask() {

        System.out.print("Enter task name: ");
        String name = scanner.nextLine();

        Task task = taskTable.search(name);

        if (task != null) {
            task.display();
        } else {
            System.out.println("Task not found.");
        }
    }
}