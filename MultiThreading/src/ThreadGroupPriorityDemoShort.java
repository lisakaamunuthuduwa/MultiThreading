public class ThreadGroupPriorityDemoShort {
    public static void main(String[] args) throws InterruptedException {
        // Create a thread group
        ThreadGroup group = new ThreadGroup("DemoGroup");

        // Create threads in the group with different priorities
        Thread low = new Thread(group, () -> printNumbers("Low", Thread.MIN_PRIORITY), "LowPriorityThread");
        Thread normal = new Thread(group, () -> printNumbers("Normal", Thread.NORM_PRIORITY), "NormalPriorityThread");
        Thread high = new Thread(group, () -> printNumbers("High", Thread.MAX_PRIORITY), "HighPriorityThread");

        // Set priorities
        low.setPriority(Thread.MIN_PRIORITY);      // 1 (lowest)
        normal.setPriority(Thread.NORM_PRIORITY);  // 5 (normal)
        high.setPriority(Thread.MAX_PRIORITY);     // 10 (highest)

        // Start all threads
        low.start();
        normal.start();
        high.start();

        // Display thread group information
        System.out.println("Thread group: " + group.getName());
        System.out.println("Active threads: " + group.activeCount());

        // Wait for all threads to finish
        low.join();
        normal.join();
        high.join();

        System.out.println("All threads finished.");
    }

    // Method for threads to print numbers, showing their priority
    static void printNumbers(String label, int priority) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(label + " Priority Thread (priority " + priority + "): " + i);
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
        }
    }
}

