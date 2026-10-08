package com.mycompany.datastructfinals;

import java.time.LocalDate;
import java.util.Random;

public class Benchmark {

    static final int[] SIZES = {100, 500, 1000, 5000};
    static final int RUNS = 15;
    static final int WARMUP = 5;
    static volatile long sink;

    static Task[] makeTasks(int n, long seed) {
        Random r = new Random(seed);
        Task[] t = new Task[n];
        LocalDate base = LocalDate.of(2026, 1, 1);
        for (int i = 0; i < n; i++) {
            t[i] = new Task("Task" + i, i % 2 == 0 ? "Chore" : "Bill",
                    base.plusDays(r.nextInt(3650)), 1 + r.nextInt(5));
        }
        return t;
    }

    static long countInsertComparisons(Task[] tasks) {
        TaskBST.Node root = null;
        TaskBST b = new TaskBST();
        long cmp = 0;
        for (Task t : tasks) {
            if (b.root == null) { b.root = b.new Node(t); continue; }
            TaskBST.Node cur = b.root;
            while (true) {
                cmp++;
                if (t.deadline.isBefore(cur.task.deadline)) {
                    if (cur.left == null) { cur.left = b.new Node(t); break; }
                    cur = cur.left;
                } else {
                    if (cur.right == null) { cur.right = b.new Node(t); break; }
                    cur = cur.right;
                }
            }
        }
        return cmp;
    }

    static int search(TaskBST.Node n, LocalDate d, long[] cmp) {
        while (n != null) {
            cmp[0]++;
            if (d.equals(n.task.deadline)) return 1;
            n = d.isBefore(n.task.deadline) ? n.left : n.right;
        }
        return 0;
    }

    static long visited;
    static void inorder(TaskBST.Node n) {
        if (n != null) { inorder(n.left); visited++; inorder(n.right); }
    }

    static int height(TaskBST.Node n) {
        return n == null ? 0 : 1 + Math.max(height(n.left), height(n.right));
    }

    static long median(long[] a) { java.util.Arrays.sort(a); return a[a.length / 2]; }

    public static void main(String[] args) {
        System.out.println("TaskBST benchmark (random deadlines, median of " + RUNS + " runs)\n");
        System.out.printf("%-8s %-10s %-14s %-12s %-14s %s%n",
                "Size", "Operation", "Structure", "Time (ns)", "Comparisons", "Observation");

        for (int n : SIZES) {
            Task[] tasks = makeTasks(n, 42);

            long[] times = new long[RUNS];
            for (int i = -WARMUP; i < RUNS; i++) {
                TaskBST bst = new TaskBST();
                long s = System.nanoTime();
                for (Task t : tasks) bst.insert(t);
                long e = System.nanoTime();
                sink += bst.root.hashCode();
                if (i >= 0) times[i] = e - s;
            }
            long insCmp = countInsertComparisons(tasks);

            TaskBST tree = new TaskBST();
            for (Task t : tasks) tree.insert(t);
            int h = height(tree.root);
            double log2n = Math.log(n) / Math.log(2);

            System.out.printf("%-8d %-10s %-14s %-12d %-14d total for %d inserts; %.1f cmp/insert, height %d (log2 n = %.1f)%n",
                    n, "Insert", "BST", median(times), insCmp, n, (double) insCmp / n, h, log2n);

            long[] cmpArr = new long[1];
            for (int i = -WARMUP; i < RUNS; i++) {
                cmpArr[0] = 0;
                long s = System.nanoTime();
                int found = 0;
                for (Task t : tasks) found += search(tree.root, t.deadline, cmpArr);
                long e = System.nanoTime();
                sink += found;
                if (i >= 0) times[i] = e - s;
            }
            System.out.printf("%-8d %-10s %-14s %-12d %-14d total for %d searches; %.1f cmp/search%n",
                    n, "Search", "BST", median(times), cmpArr[0], n, (double) cmpArr[0] / n);

            for (int i = -WARMUP; i < RUNS; i++) {
                visited = 0;
                long s = System.nanoTime();
                inorder(tree.root);
                long e = System.nanoTime();
                sink += visited;
                if (i >= 0) times[i] = e - s;
            }
            System.out.printf("%-8d %-10s %-14s %-12d %-14d visits every node once, O(n)%n",
                    n, "Traverse", "BST", median(times), visited);
        }

        System.out.println("\nWorst case (deadlines inserted in sorted order):");
        for (int n : SIZES) {
            Task[] t = makeTasks(n, 42);
            java.util.Arrays.sort(t, (a, b) -> a.deadline.compareTo(b.deadline));
            long[] times = new long[RUNS];
            for (int i = -WARMUP; i < RUNS; i++) {
                TaskBST bst = new TaskBST();
                long s = System.nanoTime();
                for (Task x : t) bst.insert(x);
                long e = System.nanoTime();
                sink += bst.root.hashCode();
                if (i >= 0) times[i] = e - s;
            }
            TaskBST b = new TaskBST();
            for (Task x : t) b.insert(x);
            System.out.printf("%-8d %-10s %-14s %-12d %-14d height %d (degenerate), %.1f cmp/insert%n",
                    n, "Insert", "BST (sorted)", median(times), countInsertComparisons(t), height(b.root),
                    (double) countInsertComparisons(t) / n);
        }
    }
}