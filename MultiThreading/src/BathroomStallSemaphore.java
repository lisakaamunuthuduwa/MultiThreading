import java.util.concurrent.Semaphore;

public class BathroomStallSemaphore {
    public static void main(String[] args) {
        // 3 bathroom stalls available
        Semaphore stalls = new Semaphore(3);

        // Simulate 7 people trying to use the bathroom
        for (int i = 1; i <= 7; i++) {
            final int person = i;
            Thread newThread= new Thread(() -> {
                try {
                    System.out.println("Person " + person + " is waiting for a stall.");
                    stalls.acquire(); // Wait for a stall to be free
                    System.out.println("Person " + person + " entered a stall.");
                    Thread.sleep((int)(Math.random() * 2000) + 500); // Using the stall
                    System.out.println("Person " + person + " is leaving the stall.");
                    stalls.release(); // Free up the stall
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            newThread.start();
        }
    }
}
