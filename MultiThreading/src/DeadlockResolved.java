// DeadlockDemoFixed.java
public class DeadlockResolved {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        // Thread 1: locks lock1, then lock2
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (lock2) {
                    System.out.println("Thread 1: Acquired lock 2!");
                }
            }
        });

        // Thread 2: also locks lock1, then lock2 (same order as Thread 1)
        Thread t2 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 2: Holding lock 1...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                System.out.println("Thread 2: Waiting for lock 2...");
                synchronized (lock2) {
                    System.out.println("Thread 2: Acquired lock 2!");
                }
            }
        });

        t1.start();
        t2.start();
    }
}

//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class DeadlockResolved {
//    private static final Lock lock1 = new ReentrantLock();
//    private static final Lock lock2 = new ReentrantLock();
//
//    public static void main(String[] args) {
//        // Thread 1 tries to lock lock1 then lock2
//        Thread t1 = new Thread(() -> {
//            while (true) {
//                if (lock1.tryLock()) {
//                    try {
//                        System.out.println("Thread 1: Acquired lock 1");
//                        try { Thread.sleep(100); } catch (InterruptedException ignored) {}
//                        if (lock2.tryLock()) {
//                            try {
//                                System.out.println("Thread 1: Acquired lock 2");
//                                break; // Both locks acquired, do work and exit
//                            } finally {
//                                lock2.unlock();
//                            }
//                        }
//                    } finally {
//                        lock1.unlock();
//                    }
//                }
//                // Back off before retrying
//                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
//            }
//        });
//
//        // Thread 2 tries to lock lock2 then lock1 (opposite order)
//        Thread t2 = new Thread(() -> {
//            while (true) {
//                if (lock2.tryLock()) {
//                    try {
//                        System.out.println("Thread 2: Acquired lock 2");
//                        try { Thread.sleep(100); } catch (InterruptedException ignored) {}
//                        if (lock1.tryLock()) {
//                            try {
//                                System.out.println("Thread 2: Acquired lock 1");
//                                break; // Both locks acquired, do work and exit
//                            } finally {
//                                lock1.unlock();
//                            }
//                        }
//                    } finally {
//                        lock2.unlock();
//                    }
//                }
//                // Back off before retrying
//                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
//            }
//        });
//
//        t1.start();
//        t2.start();
//    }
//}
