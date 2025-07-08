
public class ThreadLifecycle {
    public static void main(String[] args) {
// NEW state
        Thread newThread = new Thread(() -> {
// RUNNABLE state
            System.out.println("Thread is in RUNNABLE state.");
// Simulating some task
            for (int i = 0; i < 3; i++) {
                System.out.println("Executing task in RUNNABLE state: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
// BLOCKED state (synchronized block)
            synchronized (ThreadLifecycle.class) {
                System.out.println("Thread is in BLOCKED state (synchronized block).");
            }
// WAITING state (waiting for a notification)
            synchronized (ThreadLifecycle.class) {
                try {
                    System.out.println("Thread is in WAITING state (waiting for a notification).");
                    ThreadLifecycle.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
// TIMED_WAITING state (sleeping for a specific time)
            try {
                System.out.println("Thread is in TIMED_WAITING state (sleeping for 3 seconds).");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
// TERMINATED state
            System.out.println("Thread is in TERMINATED state.");
        });
// Starting the thread
        newThread.start();
// Main thread
        for (int i = 0; i < 5; i++) {
            System.out.println("Main thread executing: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
// NOTIFY the waiting thread to move from WAITING to RUNNABLE
        synchronized (ThreadLifecycle.class) {
            System.out.println("Notifying the waiting thread.");
            ThreadLifecycle.class.notify();
        }
// Waiting for the newThread to complete
        try {
            newThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread is in TERMINATED state.");
    }
}