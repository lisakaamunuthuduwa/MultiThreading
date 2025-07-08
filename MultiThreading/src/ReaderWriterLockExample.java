import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriterLockExample {
    private static final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static int sharedData = 0;

    public static void main(String[] args) {
        // Start writer thread
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                rwLock.writeLock().lock();
                try {
                    sharedData++;
                    System.out.println("Writer updated sharedData to " + sharedData);
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
                finally {
                    rwLock.writeLock().unlock();
                }
            }
        }).start();

        // Start multiple reader threads
        for (int i = 1; i <= 3; i++) {
            final int readerId = i;
            new Thread(() -> {
                for (int j = 1; j <= 3; j++) {
                    rwLock.readLock().lock();
                    try {
                        System.out.println("Reader " + readerId + " reads sharedData: " + sharedData);
                        Thread.sleep(300);
                    } catch (InterruptedException ignored) {}
                    finally {
                        rwLock.readLock().unlock();
                    }
                }
            }).start();
        }
    }
}
