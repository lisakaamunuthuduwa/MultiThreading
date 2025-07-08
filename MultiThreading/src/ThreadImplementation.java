// Thread implementation by extending Thread class
class MyThread extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread (extended): " + i);
            try {
                Thread.sleep(500); // Adding a small delay for demonstration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
// Thread implementation by implementing Runnable interface
class MyRunnable implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread (implemented): " + i);
            try {
                Thread.sleep(500); // Adding a small delay for demonstration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class ThreadImplementation {
    public static void main(String[] args) {
// Creating threads using both approaches
        MyThread thread1 = new MyThread();
        Thread thread2 = new Thread(new MyRunnable());

        Thread lambdaThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread (lambda): " + i);
                try {
                    Thread.sleep(500); // Adding a small delay for demonstration
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

// Starting threads
        thread1.start();
        thread2.start();
//        Start the lambda thread
        lambdaThread.start();
// Main thread continues its execution
        for (int i = 1; i <= 5; i++) {
            System.out.println("Main Thread: " + i);
            try {
                Thread.sleep(500); // Adding a small delay for demonstration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

