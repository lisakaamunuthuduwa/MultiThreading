class SharedResource {
    private int counter = 0;
    // Synchronized method to increment the counter
    public synchronized void increment() {
        int oldValue = counter;
        System.out.println(Thread.currentThread().getName() + " - Before Increment: " +
                oldValue);
// Simulating some processing time
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counter++;
        int newValue = counter;
        System.out.println(Thread.currentThread().getName() + " - After Increment: " +
                newValue);
    }
}
class SharedResourceUser extends Thread {
    private final SharedResource sharedResource;
    public SharedResourceUser(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            sharedResource.increment();
        }
    }
}
public class ConcurrentProgramWithMonitor {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
// Creating multiple threads that access the shared resource
        Thread thread1 = new SharedResourceUser(sharedResource);
        Thread thread2 = new SharedResourceUser(sharedResource);
// Starting the threads
        thread1.start();
        thread2.start();
    }
}