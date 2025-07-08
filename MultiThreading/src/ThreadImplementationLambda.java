public class ThreadImplementationLambda {
    public static void main(String[] args) {
        // Creating a thread using a lambda expression
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

        // Start the thread
        lambdaThread.start();
    }
}

