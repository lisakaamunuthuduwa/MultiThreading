public class DeadlockResolvedNew {

    // Two shared resources (objects used as locks)
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        // Thread 1: locks resource1, then resource2
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked resource 1");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                System.out.println("Thread 1: Waiting to lock resource 2...");
                synchronized (resource2) {
                    System.out.println("Thread 1: Locked resource 2");
                }
            }
        });

        // Thread 2: also locks resource1, then resource2 (same order as Thread 1)
        Thread t2 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 2: Locked resource 1");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                System.out.println("Thread 2: Waiting to lock resource 2...");
                synchronized (resource2) {
                    System.out.println("Thread 2: Locked resource 2");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
