class SharedCounter {
    private int counter = 0;
    // Synchronized method to increment the counter
    public synchronized void increment() {
        counter++;
    }
    // Getter method to retrieve the current value of the counter
    public int getCounter() {
        return counter;
    }
}
class ThreadA extends Thread {
    private final SharedCounter sharedCounter;
    public ThreadA(SharedCounter sharedCounter) {
        this.sharedCounter = sharedCounter;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            sharedCounter.increment();
        }
    }
}
class ThreadB extends Thread {
    private final SharedCounter sharedCounter;
    public ThreadB(SharedCounter sharedCounter) {
        this.sharedCounter = sharedCounter;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            sharedCounter.increment();
        }
    }
}
public class SharedCounterExample {
    public static void main(String[] args) {
        SharedCounter sharedCounter = new SharedCounter();
// Creating two threads, ThreadA and ThreadB, sharing the same counter
        ThreadA threadA = new ThreadA(sharedCounter);
        ThreadB threadB = new ThreadB(sharedCounter);
// Starting both threads
        threadA.start();
        threadB.start();
// Waiting for both threads to complete
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
// Displaying the final value of the counter
        System.out.println("Final Counter Value: " + sharedCounter.getCounter());
    }
}