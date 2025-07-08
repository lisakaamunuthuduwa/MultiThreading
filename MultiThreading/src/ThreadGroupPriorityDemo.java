// ThreadGroupPriorityDemo.java

public class ThreadGroupPriorityDemo {

    // Define a simple task for threads: print numbers with a delay
    static class NumberPrinter implements Runnable {
        private final int id;
        private final int count;

        public NumberPrinter(int id, int count) {
            this.id = id;
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 1; i <= count; i++) {
                System.out.println("Thread " + id + " (priority " + Thread.currentThread().getPriority() + "): " + i);
                try {
                    Thread.sleep(100); // Sleep to make output readable
                } catch (InterruptedException e) {
                    System.out.println("Thread " + id + " interrupted.");
                    break;
                }
            }
            System.out.println("Thread " + id + " finished.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a thread group
        ThreadGroup group = new ThreadGroup("DemoGroup");

        // Create threads with different priorities in the group
        Thread t1 = new Thread(group, new NumberPrinter(1, 5), "LowPriorityThread");
        Thread t2 = new Thread(group, new NumberPrinter(2, 5), "NormalPriorityThread");
        Thread t3 = new Thread(group, new NumberPrinter(3, 5), "HighPriorityThread");

        // Set different priorities
        t1.setPriority(Thread.MIN_PRIORITY);    // 1 (lowest)
        t2.setPriority(Thread.NORM_PRIORITY);   // 5 (default)
        t3.setPriority(Thread.MAX_PRIORITY);    // 10 (highest)

        // Start all threads
        t1.start();
        t2.start();
        t3.start();

        // Print information about the group
        System.out.println("Thread group name: " + group.getName());
        System.out.println("Active threads in group: " + group.activeCount());

        // Wait for all threads to finish
        t1.join();
        t2.join();
        t3.join();

        System.out.println("All threads have finished.");
    }
}
