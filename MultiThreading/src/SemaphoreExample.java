import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        // Create a semaphore with 2 permits
        Semaphore semaphore = new Semaphore(2);

        // Create 5 worker threads
        for (int i = 1; i <= 5; i++) {
            final int workerId = i;
            Thread newThread= new Thread(() -> {
                try {
                    System.out.println("Worker " + workerId + " is waiting for a permit.");
                    semaphore.acquire(); // Acquire a permit
                    System.out.println("Worker " + workerId + " got a permit and is working.");
                    Thread.sleep(1000); // Simulate work
                    System.out.println("Worker " + workerId + " is releasing the permit.");
                    semaphore.release(); // Release the permit
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            newThread.start();
        }
    }
}
