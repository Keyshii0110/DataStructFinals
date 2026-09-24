package com.mycompany.datastructfinals;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // Program loop
        boolean loop = true;

        while (loop) {

            // Main menu
            System.out.println("=== Chore and Bill Scheduler ===");
            System.out.println("1. Add chore");
            System.out.println("2. Add bill");
            System.out.println("3. View tasks");
            System.out.println("4. Complete task");
            System.out.println("5. View priority tasks");
            System.out.println("6. View streak");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            String choice = in.nextLine();

            // Menu options
            switch (choice) {

                case "1":
                    System.out.println("Add chore");
                    break;

                case "2":
                    System.out.println("Add bill");
                    break;

                case "3":
                    System.out.println("View tasks");
                    break;

                case "4":
                    System.out.println("Complete task");
                    break;

                case "5":
                    System.out.println("View priority tasks");
                    break;

                case "6":
                    System.out.println("View streak");
                    break;

                case "7":
                    loop = false;
                    break;

                default:
                    System.out.println("Invalid input. Please try again.");
            }

            System.out.println();
        }

        in.close();
    }
}